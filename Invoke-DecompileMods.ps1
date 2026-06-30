param(
    [string]$ModsDir = (Split-Path -Parent $PSScriptRoot),
    [string]$DecompilerJar = (Join-Path $PSScriptRoot "tools\cfr-0.152.jar")
)

$ErrorActionPreference = "Stop"

Add-Type -AssemblyName System.IO.Compression.FileSystem

function Convert-ToSafeName {
    param([string]$Name)
    $safe = $Name -replace '[^\p{L}\p{Nd}._-]+', '_'
    $safe = $safe.Trim("._- ")
    if ([string]::IsNullOrWhiteSpace($safe)) {
        return "unknown-mod"
    }
    return $safe
}

function Read-ZipEntryText {
    param(
        [string]$ZipPath,
        [string]$EntryName
    )
    $zip = [System.IO.Compression.ZipFile]::OpenRead($ZipPath)
    try {
        $entry = $zip.GetEntry($EntryName)
        if ($null -eq $entry) {
            return $null
        }
        $stream = $entry.Open()
        try {
            $reader = New-Object System.IO.StreamReader($stream)
            try {
                return $reader.ReadToEnd()
            } finally {
                $reader.Dispose()
            }
        } finally {
            $stream.Dispose()
        }
    } finally {
        $zip.Dispose()
    }
}

function Get-ModMetadata {
    param([System.IO.FileInfo]$Jar)

    $fallbackName = [System.IO.Path]::GetFileNameWithoutExtension($Jar.Name)
    $result = [ordered]@{
        displayName = $fallbackName
        modid = ""
        version = ""
        source = "jar filename"
    }

    $mcmod = Read-ZipEntryText -ZipPath $Jar.FullName -EntryName "mcmod.info"
    if (-not [string]::IsNullOrWhiteSpace($mcmod)) {
        try {
            $json = $mcmod | ConvertFrom-Json
            $entry = $null
            if ($json -is [System.Array]) {
                $entry = $json[0]
            } elseif ($json.modList) {
                $entry = $json.modList[0]
            } else {
                $entry = $json
            }

            if ($entry.name) {
                $result.displayName = [string]$entry.name
            } elseif ($entry.modid) {
                $result.displayName = [string]$entry.modid
            }
            if ($entry.modid) {
                $result.modid = [string]$entry.modid
            }
            if ($entry.version) {
                $result.version = [string]$entry.version
            }
            $result.source = "mcmod.info"
        } catch {
            $result.source = "jar filename; mcmod.info parse failed: $($_.Exception.Message)"
        }
    }

    return $result
}

function Copy-JarResources {
    param(
        [string]$JarPath,
        [string]$ResourcesDir
    )

    $zip = [System.IO.Compression.ZipFile]::OpenRead($JarPath)
    try {
        foreach ($entry in $zip.Entries) {
            if ($entry.FullName.EndsWith("/")) {
                continue
            }
            if ($entry.FullName -like "*.class") {
                continue
            }
            if ($entry.FullName -match '^META-INF/.*\.(SF|DSA|RSA)$') {
                continue
            }

            $relativePath = $entry.FullName -replace '/', [System.IO.Path]::DirectorySeparatorChar
            $target = Join-Path $ResourcesDir $relativePath
            $targetDir = Split-Path -Parent $target
            if (-not (Test-Path -Path $targetDir)) {
                New-Item -ItemType Directory -Force -Path $targetDir | Out-Null
            }
            [System.IO.Compression.ZipFileExtensions]::ExtractToFile($entry, $target, $true)
        }
    } finally {
        $zip.Dispose()
    }
}

if (-not (Test-Path -Path $DecompilerJar)) {
    throw "Decompiler jar not found: $DecompilerJar"
}

$modsRoot = Join-Path $PSScriptRoot "mods"
$logsRoot = Join-Path $PSScriptRoot "logs"
$tmpRoot = Join-Path $PSScriptRoot ".tmp"
New-Item -ItemType Directory -Force -Path $modsRoot, $logsRoot, $tmpRoot | Out-Null

$jars = Get-ChildItem -Path $ModsDir -File -Filter "*.jar" | Sort-Object Name
$seenNames = @{}
$manifest = @()

foreach ($jar in $jars) {
    $meta = Get-ModMetadata -Jar $jar
    $baseFolder = Convert-ToSafeName -Name $meta.displayName
    if ($seenNames.ContainsKey($baseFolder)) {
        $seenNames[$baseFolder] += 1
        $folderName = "{0}_{1}" -f $baseFolder, $seenNames[$baseFolder]
    } else {
        $seenNames[$baseFolder] = 1
        $folderName = $baseFolder
    }

    $modDir = Join-Path $modsRoot $folderName
    $srcDir = Join-Path $modDir "src\main\java"
    $resourcesDir = Join-Path $modDir "src\main\resources"
    $tmpDecompileDir = Join-Path $tmpRoot $folderName
    $logPath = Join-Path $logsRoot "$folderName.cfr.log"

    if (Test-Path -Path $modDir) {
        Remove-Item -Path $modDir -Recurse -Force
    }
    if (Test-Path -Path $tmpDecompileDir) {
        Remove-Item -Path $tmpDecompileDir -Recurse -Force
    }
    New-Item -ItemType Directory -Force -Path $srcDir, $resourcesDir, $tmpDecompileDir | Out-Null

    Write-Host "Decompiling $($jar.Name) -> mods\$folderName"
    $cfrOutput = & java -jar $DecompilerJar $jar.FullName --outputdir $tmpDecompileDir --silent true --caseinsensitivefs true 2>&1
    $cfrOutput | Set-Content -Path $logPath -Encoding UTF8

    Get-ChildItem -Path $tmpDecompileDir -Force | Move-Item -Destination $srcDir -Force
    Copy-JarResources -JarPath $jar.FullName -ResourcesDir $resourcesDir

    $sha256 = (Get-FileHash -Algorithm SHA256 -LiteralPath $jar.FullName).Hash
    $classCount = (& jar tf $jar.FullName | Where-Object { $_ -like "*.class" }).Count
    $javaCount = (Get-ChildItem -Path $srcDir -Recurse -File -Filter "*.java" -ErrorAction SilentlyContinue).Count
    $resourceCount = (Get-ChildItem -Path $resourcesDir -Recurse -File -ErrorAction SilentlyContinue).Count

    $readme = @"
# $($meta.displayName)

Decompiled from local Minecraft mod jar for inspection and bug research.

| Field | Value |
|---|---|
| Original jar | `$($jar.Name)` |
| Metadata source | `$($meta.source)` |
| Mod ID | `$($meta.modid)` |
| Version | `$($meta.version)` |
| SHA256 | `$sha256` |
| Class files | `$classCount` |
| Decompiled Java files | `$javaCount` |
| Extracted resource files | `$resourceCount` |
| Decompiler | `CFR 0.152` |

Notes:

- Decompiled code is not guaranteed to rebuild without the original build scripts, mappings, and dependencies.
- Resources are copied under `src/main/resources`; signature files from `META-INF` are intentionally omitted.
- Check the original mod license before publishing this directory to a public GitHub repository.
"@
    $readme | Set-Content -Path (Join-Path $modDir "README.md") -Encoding UTF8

    $manifest += [pscustomobject]@{
        folder = "mods/$folderName"
        originalJar = $jar.Name
        displayName = $meta.displayName
        modid = $meta.modid
        version = $meta.version
        sha256 = $sha256
        classFiles = $classCount
        javaFiles = $javaCount
        resourceFiles = $resourceCount
        log = "logs/$folderName.cfr.log"
    }
}

$manifest | ConvertTo-Json -Depth 4 | Set-Content -Path (Join-Path $PSScriptRoot "manifest.json") -Encoding UTF8
Remove-Item -Path $tmpRoot -Recurse -Force

Write-Host "Done. Decompiled $($jars.Count) jar(s)."

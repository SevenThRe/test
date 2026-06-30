param(
    [string]$MappingZip = (Join-Path $PSScriptRoot "tools\mappings\mcp_stable-39-1.12.zip"),
    [string]$SourceRoot = (Join-Path $PSScriptRoot "mods")
)

$ErrorActionPreference = "Stop"

function Read-CsvFromZip {
    param(
        [string]$ZipPath,
        [string]$EntryName
    )

    Add-Type -AssemblyName System.IO.Compression.FileSystem
    $zip = [System.IO.Compression.ZipFile]::OpenRead($ZipPath)
    try {
        $entry = $zip.GetEntry($EntryName)
        if ($null -eq $entry) {
            throw "Missing mapping entry $EntryName in $ZipPath"
        }

        $stream = $entry.Open()
        try {
            $reader = New-Object System.IO.StreamReader($stream)
            try {
                return $reader.ReadToEnd() | ConvertFrom-Csv
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

if (-not (Test-Path -Path $MappingZip)) {
    throw "Mapping zip not found: $MappingZip"
}

$map = @{}

foreach ($row in (Read-CsvFromZip -ZipPath $MappingZip -EntryName "methods.csv")) {
    if ($row.searge -and $row.name -and -not $map.ContainsKey($row.searge)) {
        $map[$row.searge] = $row.name
    }
}

foreach ($row in (Read-CsvFromZip -ZipPath $MappingZip -EntryName "fields.csv")) {
    if ($row.searge -and $row.name -and -not $map.ContainsKey($row.searge)) {
        $map[$row.searge] = $row.name
    }
}

$identifierPattern = [regex]'\b(?:func|field)_[0-9A-Za-z_]+\b'
$changedFiles = 0
$changedIdentifiers = New-Object System.Collections.Generic.HashSet[string]

Get-ChildItem -Path $SourceRoot -Recurse -File -Filter "*.java" | ForEach-Object {
    $path = $_.FullName
    $text = Get-Content -Path $path -Raw
    $changed = $false

    $newText = $identifierPattern.Replace($text, {
        param($match)
        $key = $match.Value
        if ($map.ContainsKey($key)) {
            $script:changed = $true
            [void]$changedIdentifiers.Add($key)
            return $map[$key]
        }
        return $key
    })

    if ($newText -ne $text) {
        Set-Content -Path $path -Value $newText -Encoding UTF8 -NoNewline
        $changedFiles += 1
    }
}

$reportPath = Join-Path $PSScriptRoot "MCP_REMAPPING.md"
$report = @(
    "# MCP Remapping Report",
    "",
    "Applied MCP stable mappings to decompiled Java source.",
    "",
    "| Field | Value |",
    "|---|---:|",
    "| Mapping archive | ``mcp_stable-39-1.12.zip`` |",
    "| Source root | ``mods`` |",
    "| Changed Java files | ``$changedFiles`` |",
    "| Distinct SRG identifiers replaced | ``$($changedIdentifiers.Count)`` |",
    "",
    "This only remaps Minecraft/Forge SRG method and field identifiers such as ``func_191745_a`` and ``field_71466_p``. It cannot recover original names for private obfuscated mod classes such as ``iiiiiiiiii_3``; those require manual or semantic deobfuscation."
)

$report | Set-Content -Path $reportPath -Encoding UTF8

Write-Host "Changed Java files: $changedFiles"
Write-Host "Distinct SRG identifiers replaced: $($changedIdentifiers.Count)"
Write-Host "Wrote $reportPath"

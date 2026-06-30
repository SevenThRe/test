param(
    [string]$ModsDir = (Split-Path -Parent $PSScriptRoot)
)

$ErrorActionPreference = "Stop"

function Convert-ToSafeFileName {
    param([string]$Name)
    $safe = $Name -replace '[^\p{L}\p{Nd}._-]+', '_'
    $safe = $safe.Trim("._- ")
    if ([string]::IsNullOrWhiteSpace($safe)) {
        return "unknown"
    }
    return $safe
}

function Get-CfrIssueClasses {
    param([string]$SummaryPath)

    if (-not (Test-Path -Path $SummaryPath)) {
        return @()
    }

    $lines = Get-Content -Path $SummaryPath
    $issues = New-Object System.Collections.Generic.List[object]
    $currentClass = $null
    $currentMethod = $null

    foreach ($line in $lines) {
        if ($line -match '^[a-zA-Z_$][\w$]*(\.[a-zA-Z_$][\w$]*)+$') {
            $currentClass = $line.Trim()
            $currentMethod = $null
            continue
        }

        if ($line -match '^\S.*\)$') {
            $currentMethod = $line.Trim()
            continue
        }

        if ($line -match '^\s+(Exception :|Unable to fully structure code|Could not resolve type clashes|Loose catch block)') {
            $issues.Add([pscustomobject]@{
                class = $currentClass
                method = $currentMethod
                issue = $line.Trim()
            })
        }
    }

    return $issues
}

function Write-ClassIndex {
    param(
        [string]$JarPath,
        [string]$OutputPath
    )

    $classes = & jar tf $JarPath |
        Where-Object { $_ -like "*.class" } |
        ForEach-Object { $_ -replace '/', '.' -replace '\.class$', '' } |
        Sort-Object

    $classes | Set-Content -Path $OutputPath -Encoding UTF8
    return $classes.Count
}

function Write-JavapFallback {
    param(
        [string]$JarPath,
        [string]$ClassName,
        [string]$OutputPath
    )

    $dir = Split-Path -Parent $OutputPath
    if (-not (Test-Path -Path $dir)) {
        New-Item -ItemType Directory -Force -Path $dir | Out-Null
    }

    $content = & javap -classpath $JarPath -c -p -v $ClassName 2>&1
    $content | Set-Content -Path $OutputPath -Encoding UTF8
}

$manifestPath = Join-Path $PSScriptRoot "manifest.json"
if (-not (Test-Path -Path $manifestPath)) {
    throw "manifest.json not found: $manifestPath"
}

$manifest = Get-Content -Path $manifestPath | ConvertFrom-Json
$qualityRoot = Join-Path $PSScriptRoot "decompilation-quality"
$bytecodeRoot = Join-Path $PSScriptRoot "bytecode-fallback"
New-Item -ItemType Directory -Force -Path $qualityRoot, $bytecodeRoot | Out-Null

$report = @()

foreach ($entry in $manifest) {
    $modFolderName = Split-Path -Leaf $entry.folder
    $jarPath = Join-Path $ModsDir $entry.originalJar
    $summaryPath = Join-Path $PSScriptRoot ($entry.folder + "\src\main\java\summary.txt")
    $classIndexPath = Join-Path $qualityRoot "$modFolderName.classes.txt"
    $issuePath = Join-Path $qualityRoot "$modFolderName.cfr-issues.json"

    $classCount = Write-ClassIndex -JarPath $jarPath -OutputPath $classIndexPath
    $issues = @(Get-CfrIssueClasses -SummaryPath $summaryPath)
    $issues | ConvertTo-Json -Depth 4 | Set-Content -Path $issuePath -Encoding UTF8

    $severeIssues = @($issues | Where-Object { $_.issue -match 'Exception|Unable to fully structure code|Could not resolve type clashes' })
    $issueClasses = @($severeIssues | Where-Object { $_.class } | Select-Object -ExpandProperty class -Unique | Sort-Object)

    foreach ($className in $issueClasses) {
        $safeClassName = Convert-ToSafeFileName -Name $className
        $fallbackPath = Join-Path $bytecodeRoot "$modFolderName\$safeClassName.javap.txt"
        Write-JavapFallback -JarPath $jarPath -ClassName $className -OutputPath $fallbackPath
    }

    $report += [pscustomobject]@{
        folder = $entry.folder
        originalJar = $entry.originalJar
        classFiles = $classCount
        cfrJavaFiles = $entry.javaFiles
        cfrIssueCount = $issues.Count
        severeCfrIssueCount = $severeIssues.Count
        bytecodeFallbackClasses = $issueClasses.Count
        cfrSummary = $summaryPath.Replace($PSScriptRoot + "\", "")
        classIndex = $classIndexPath.Replace($PSScriptRoot + "\", "")
        cfrIssues = $issuePath.Replace($PSScriptRoot + "\", "")
        bytecodeFallbackDir = (Join-Path "bytecode-fallback" $modFolderName)
    }
}

$reportPath = Join-Path $PSScriptRoot "DECOMPILATION_QUALITY.md"
$jsonPath = Join-Path $PSScriptRoot "decompilation-quality\quality-report.json"
$report | ConvertTo-Json -Depth 4 | Set-Content -Path $jsonPath -Encoding UTF8

$markdownLines = New-Object System.Collections.Generic.List[string]
$markdownLines.Add("# Decompilation Quality Report")
$markdownLines.Add("")
$markdownLines.Add("This repository is a reverse-engineering aid, not authoritative source code.")
$markdownLines.Add("")
$markdownLines.Add("Decompiler output can mislead analysis when bytecode is obfuscated, when control flow is intentionally flattened, or when the decompiler reports structured-code failures. Treat Java under ``mods/*/src/main/java`` as a readable approximation. For classes listed in this report, prefer the matching ``bytecode-fallback/*/*.javap.txt`` disassembly when checking exact control flow.")
$markdownLines.Add("")
$markdownLines.Add("## Summary")
$markdownLines.Add("")
$markdownLines.Add("| Mod folder | Class files | CFR Java files | CFR issue notes | Severe CFR issues | Bytecode fallback classes |")
$markdownLines.Add("|---|---:|---:|---:|---:|---:|")

foreach ($item in $report) {
    $markdownLines.Add((
        "| ``{0}`` | ``{1}`` | ``{2}`` | ``{3}`` | ``{4}`` | ``{5}`` |" -f
        $item.folder,
        $item.classFiles,
        $item.cfrJavaFiles,
        $item.cfrIssueCount,
        $item.severeCfrIssueCount,
        $item.bytecodeFallbackClasses
    ))
}

$markdownLines.Add("")
$markdownLines.Add("## Files")
$markdownLines.Add("")
$markdownLines.Add("- ``decompilation-quality/quality-report.json`` - machine-readable quality summary.")
$markdownLines.Add("- ``decompilation-quality/*.classes.txt`` - original class inventory from each jar.")
$markdownLines.Add("- ``decompilation-quality/*.cfr-issues.json`` - parsed CFR summary issues.")
$markdownLines.Add("- ``bytecode-fallback/<mod>/*.javap.txt`` - bytecode disassembly for classes where CFR reported severe structuring failures.")
$markdownLines.Add("")
$markdownLines.Add("## Research Guidance")
$markdownLines.Add("")
$markdownLines.Add("- Do not assume decompiled Java compiles.")
$markdownLines.Add("- Do not infer intent from decompiler-generated local variable names in obfuscated mods.")
$markdownLines.Add("- If a method appears suspicious and its class has a CFR issue, inspect bytecode fallback before concluding it is a bug.")
$markdownLines.Add("- Missing class-to-Java parity is not automatically a failure because inner classes are often merged into outer Java files.")

$markdownLines | Set-Content -Path $reportPath -Encoding UTF8

Write-Host "Wrote $reportPath"
Write-Host "Wrote $jsonPath"

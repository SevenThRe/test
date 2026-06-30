# Decompilation Quality Report

This repository is a reverse-engineering aid, not authoritative source code.

Decompiler output can mislead analysis when bytecode is obfuscated, when control flow is intentionally flattened, or when the decompiler reports structured-code failures. Treat Java under `mods/*/src/main/java` as a readable approximation. For classes listed in this report, prefer the matching `bytecode-fallback/*/*.javap.txt` disassembly when checking exact control flow.

## Summary

| Mod folder | Class files | CFR Java files | CFR issue notes | Severe CFR issues | Bytecode fallback classes |
|---|---:|---:|---:|---:|---:|
| `mods/Dragon_Core` | `2915` | `1843` | `3` | `1` | `1` |
| `mods/AncientDreamMod-1.0_5` | `913` | `574` | `1` | `0` | `0` |
| `mods/Inventory_Tweaks` | `74` | `63` | `0` | `0` | `0` |
| `mods/JourneyMap` | `2198` | `1320` | `27` | `25` | `15` |
| `mods/Armourer_s_Workshops` | `1217` | `945` | `31` | `24` | `19` |
| `mods/龙之导航QQ448780139` | `22` | `18` | `0` | `0` | `0` |
| `mods/OptiFine_1.12.2_HD_U_F5` | `487` | `436` | `0` | `0` | `0` |
| `mods/Shoulder_Surfing` | `38` | `27` | `0` | `0` | `0` |

## Files

- `decompilation-quality/quality-report.json` - machine-readable quality summary.
- `decompilation-quality/*.classes.txt` - original class inventory from each jar.
- `decompilation-quality/*.cfr-issues.json` - parsed CFR summary issues.
- `bytecode-fallback/<mod>/*.javap.txt` - bytecode disassembly for classes where CFR reported severe structuring failures.

## Research Guidance

- Do not assume decompiled Java compiles.
- Do not infer intent from decompiler-generated local variable names in obfuscated mods.
- If a method appears suspicious and its class has a CFR issue, inspect bytecode fallback before concluding it is a bug.
- Missing class-to-Java parity is not automatically a failure because inner classes are often merged into outer Java files.

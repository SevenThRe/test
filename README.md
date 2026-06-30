# Decompiled Minecraft Mods Source Archive

This repository contains decompiled source and extracted resources from the local `mods` directory.

Decompiler output is only an inspection aid. It is not guaranteed to be complete, compilable, or semantically exact. Read `DECOMPILATION_QUALITY.md` before using this repository for automated research.

Layout:

- `mods/<mod-name>/src/main/java` - Java source produced by CFR.
- `mods/<mod-name>/src/main/resources` - non-class files extracted from the jar.
- `mods/<mod-name>/README.md` - original jar name, metadata, checksum, and file counts.
- `manifest.json` - machine-readable index for all processed mods.
- `DECOMPILATION_QUALITY.md` - decompiler warning summary and bytecode fallback guidance.
- `decompilation-quality/` - class inventories and parsed CFR issue data.
- `bytecode-fallback/` - `javap` disassembly for classes where CFR reported severe failures.
- `Invoke-DecompileMods.ps1` - repeatable decompilation script.

The original jar files and the decompiler binary are intentionally ignored by git.

Important: decompiled output may be subject to the original mod authors' licenses. Verify licensing before publishing this repository publicly on GitHub.

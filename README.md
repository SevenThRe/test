# Decompiled Minecraft Mods Source Archive

This repository contains decompiled source and extracted resources from the local `mods` directory.

Layout:

- `mods/<mod-name>/src/main/java` - Java source produced by CFR.
- `mods/<mod-name>/src/main/resources` - non-class files extracted from the jar.
- `mods/<mod-name>/README.md` - original jar name, metadata, checksum, and file counts.
- `manifest.json` - machine-readable index for all processed mods.
- `Invoke-DecompileMods.ps1` - repeatable decompilation script.

The original jar files and the decompiler binary are intentionally ignored by git.

Important: decompiled output may be subject to the original mod authors' licenses. Verify licensing before publishing this repository publicly on GitHub.

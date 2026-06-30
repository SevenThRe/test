# Research Guide

Use this repository as a reverse-engineering workspace, not as original source.

## Read Order

1. Start with `manifest.json` to identify the target mod directory and original jar.
2. Read `MCP_REMAPPING.md` to understand which Minecraft 1.12 SRG names were remapped.
3. Read `DECOMPILATION_QUALITY.md` before drawing conclusions from any suspicious control flow.
4. For classes listed under `bytecode-fallback/`, verify exact behavior with the matching `javap.txt` file instead of relying only on decompiled Java.
5. Then inspect `mods/<mod>/src/main/java` and `mods/<mod>/src/main/resources`.

## Trust Levels

High confidence:

- Resource files under `src/main/resources`.
- Metadata files such as `mcmod.info`.
- Decompiled Java that uses readable MCP names and is not listed in CFR severe issues.
- Simple constants, strings, registry names, GUI text, command strings, resource paths, and network channel names.

Medium confidence:

- Decompiled method bodies in non-obfuscated or lightly obfuscated classes.
- Control flow in classes that have no `ConfusedCFRException` or `Unable to fully structure code` warning.
- Minecraft/Forge API calls after MCP remapping.

Low confidence:

- Methods/classes listed in `decompilation-quality/*.cfr-issues.json`.
- Obfuscated private mod classes such as `iiiiiiiiii_3`, `ao`, `gr`, or very short two-letter classes.
- Decompiled local variable names in obfuscated packages.
- Decompiled try/catch/finally, synchronized blocks, switch-heavy methods, and flattened control flow.

## How To Investigate Bugs

- Anchor findings in observable behavior: strings, commands, packet IDs, resource paths, config keys, and event registrations.
- Cross-check suspicious methods with bytecode fallback when a class appears in `DECOMPILATION_QUALITY.md`.
- Treat repeated bundled libraries, such as shaded Kotlin, Jetty, Mixin, SnakeYAML, or JavaCPP, as dependencies unless the bug points there directly.
- Prefer mod-owned packages for gameplay bugs:
  - `eos.moe.dragoncore`
  - `eos.moe.ancientdream`
  - `eos.moe.armourers`
  - `eos.moe.dragongps`
  - `com.teamderpy.shouldersurfing`
  - `invtweaks`
  - `journeymap`
- Do not report a bug solely because the decompiled Java has invalid-looking local variables or duplicated parameter names. Verify with bytecode or runtime behavior.

## Known Limits

- This repository does not include original source history or original build scripts.
- MCP remapping only restores Minecraft/Forge SRG identifiers. It does not recover private mod symbol names.
- Some classes are bundled third-party libraries and may not belong to the mod's own logic.
- Decompilers can produce Java that is readable but not semantically exact.


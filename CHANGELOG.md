# Changelog

## 1.2.0

### Added

- Added Minecraft 1.21.1 support with NeoForge 21.1.234.
- Maintained separate Forge 1.20.1 and NeoForge 1.21.1 versions.
- Added a dedicated pixel texture for tridents in the inventory.
- Item frames now render tridents with the pixel model for consistent orientation and vanilla-style display.

### Improvements

- Updated SimpleBedrockModel:
  - Forge 1.20.1: `2.5.1-forge-mc1.20.1`
  - NeoForge 1.21.1: `2.5.1.1-neoforge-mc1.21.1`
- Simplified model loading and rendering code by removing duplicated logic.
- Replaced the thrown trident renderer Mixin with an entity renderer registration event.
- Replaced item-frame rendering Mixin logic with Forge/NeoForge client events to reduce compatibility conflicts.
- Reduced the number of Mixins from three to two, retaining only the injections required for inventory model switching and first-person/third-person Bedrock model rendering.

### Display Behavior

- Inventory and item frames: use the pixel texture model.
- First-person, third-person, thrown, and dropped tridents: continue to use the Bedrock 3D model.
- Trident enchantment glint and glow item frame lighting remain supported.

### Build Artifacts

- `better_trident_model-forge1.20.1-1.2.0.jar`
- `better_trident_model-neoforge1.21.1-1.2.0.jar`

Build artifact names now include the mod loader and Minecraft version before the mod version, making platform variants easier to identify.

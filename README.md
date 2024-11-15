# MojangFixStationAPI
A [StationAPI](https://modrinth.com/mod/stationapi) version of [MojangFix](https://modrinth.com/mod/mojangfix) for Minecraft b1.7.3 with [GlassConfigAPI](https://modrinth.com/mod/glass-config-api) configs to increase the mod's compatibility. Fixes skins, authentication, and more.
* For more InventoryTweaks (ex: `left-click + drag`) consider : https://modrinth.com/mod/inventorytweaks

## Features
<details><summary>Skin and cape fix (including 1.8+ outer layers)</summary>

![mintoyatsu standing with a working skin](https://user-images.githubusercontent.com/35262707/158473931-1ae3ea4f-4673-4baa-aa3d-044275d462ea.png)
- Added ability to raise slim skin shoulders with GlassConfigAPI
- Added ability to toggle rendering the player cape with GlassConfigAPI
</details>

<details><summary>Authentication fix</summary>

![server log](https://user-images.githubusercontent.com/35262707/159056534-568087f9-00e2-4830-b66a-5046773cb3b1.png)
Allows the server to verify that the connecting player is logged in
</details>

<details><summary>Multiplayer server list</summary>

![multiplayer screen](https://user-images.githubusercontent.com/35262707/159057966-8c3a6bc2-da0f-4132-a8f3-fb0d0839763a.png)

(server status not implemented yet)
</details>

<details><summary>Scrollable keybinds gui</summary>

![keybinds screen](https://user-images.githubusercontent.com/35262707/159058180-789b936e-0cb8-4ece-8fa2-d225b4812b24.png)
</details>

<details><summary>Better text edition</summary>

![Allow pasting text into chat and onto signs with LCTRL + V](https://user-images.githubusercontent.com/35262707/159060408-8e35a074-0ee1-426a-bc51-1152d6adca34.gif)
</details>

<details><summary>Basic inventory tweaks</summary>

<video controls src="https://user-images.githubusercontent.com/35262707/159063818-e450561d-f13d-435a-b46b-879cc54a8a0f.mp4" />
</details>

<details><summary>Previously unlisted changes</summary>

- Enable Bit Depth Fix
- Enable Death Screen Score Fix
- Enable Debug Graph Hidden By Default
  - Use the new keybind (default: LCtrl) + F3 to open up debug screen with graph
  - Optionally using GCAPI config switch keybind to toggle debug graph whenever pressed
- Enable Displaying World Seed In Debug Menu
- Enable MojangFix Version Text On Title Screen
- Enable Quit Button
- Change Resources Download URL
  - This edition of MojangFix lets you change it yourself as well in the config settings
</details>

## Installation using Prism Launcher

1. Download an instance of Babric for Prism Launcher: https://github.com/Glass-Series/babric-prism-instance
2. Install Java 17, set the instance to use it, and disable compatibility checks on the instance: https://adoptium.net/temurin/releases/
3. Add StationAPI to the mod folder for the instance: https://modrinth.com/mod/stationapi
4. Add Mod Menu to the mod folder for the instance: https://modrinth.com/mod/modmenu-beta
5. Add GlassConfigAPI 3.0.1+ to the mod folder for the instance: https://modrinth.com/mod/glass-config-api
6. Add this mod to the mod folder for the instance: https://github.com/telvarost/MojangFix-StationAPI/releases
7. Run and enjoy! 👍

## FAQ

* Q. Why is minecraft so small and part of my screen white?
  * A. You probably have screen scaling on or are using a high DPI screen. There are 4 possible ways to fix this:
    * You can pass the following string in as a java argument in the settings tab
      * `-Dsun.java2d.uiScale=1.0`
    * You can install ClientsideEssentials mod or UniTweaks and use their screen scaling fix from GCAPI config options (fix may produce small artifacts with this option)
    * You can change the DPI settings for Prism Launcher to system
    * Or, you can change your screen scaling to 100%
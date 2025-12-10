AND101 Project 7 - CYOAPI Part 3: Beautified

Submitted by: Cameron Smith

Time spent: 5 hours spent in total

Summary

Pokédex RecyclerView App is an Android app that displays a scrollable list of the first 151 Pokémon using data from the PokéAPI, each showing name, ID number, and official artwork. Users can tap any Pokémon to see a Toast message with details.

If I had to describe this project in three (3) emojis, they would be: 🎮📱✨

Application Features

The following REQUIRED features are completed:

 App contains a RecyclerView that displays a list of scrollable data

 App displays at least two (2) pieces of data for each RecyclerView item (name + ID + image)

 Use a downloadable font with custom color and size

 Modify the theme of the app in themes.xml

 Define and apply at least one style in either themes.xml or a new file styles.xml

The following STRETCH features are implemented:

 Update the night theme to use different versions of styles when in dark mode

 Use different drawables when in dark mode

The following EXTRA features are implemented:

 Added search bar to filter Pokémon by name

 Added divider lines between RecyclerView items for cleaner UI

 Tap-to-toast showing Pokémon name & ID

 Official artwork loaded dynamically from GitHub sprite repo

Video Demo

Here's a video / GIF that demos all of the app's implemented features:

https://imgur.com/a/k0G3xyn

GIF created with Loom / ScreenToGif / your chosen tool

Notes

I learned how to properly parse API JSON, update RecyclerViews live, work with custom fonts, and fix threading issues when making network calls. This project also taught me how to debug crashes caused by missing permissions and Toast calls happening on non-UI threads.

License

Copyright 2025 Cameron Smith

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.

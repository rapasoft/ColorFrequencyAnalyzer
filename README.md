       ______      __           ______                                            ___                __
      / ____/___  / /___  _____/ ____/_______  ____ ___  _____  ____  _______  __/   |  ____  ____ _/ /_  ______  ___  _____
     / /   / __ \/ / __ \/ ___/ /_  / ___/ _ \/ __ `/ / / / _ \/ __ \/ ___/ / / / /| | / __ \/ __ `/ / / / /_  / / _ \/ ___/
    / /___/ /_/ / / /_/ / /  / __/ / /  /  __/ /_/ / /_/ /  __/ / / / /__/ /_/ / ___ |/ / / / /_/ / / /_/ / / /_/  __/ /
    \____/\____/_/\____/_/  /_/   /_/   \___/\__, /\__,_/\___/_/ /_/\___/\__, /_/  |_/_/ /_/\__,_/_/\__, / /___/\___/_/
                                               /_/                      /____/                     /____/

This tool calculates percentage of area in the image that the specific color occupies. Supported file types: PNG, JPG, JPEG, GIF, BMP.

For now it is really simple, no fuzzy-logic or threshold is supported. The algorithm goes pixel by pixel and stores number of occurences for each of the color in the image (RGB). Then it calculates frequency based on total amount of pixels in the image.

This is basically a test project for Weld & JavaFX cooperation :)
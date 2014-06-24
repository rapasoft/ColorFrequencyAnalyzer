ColorFrequencyAnalyzer
======================

This tool calculates percentage of area in the image that the specific color occupies.

For now it is really simple, no fuzzy-logic or threshold is supported. The algorithm goes pixel by pixel and stores number of occurences for each of the color in the image (RGB). Then it calculates frequency based on total amount of pixels in the image.

Supported file types: PNG, JPG, JPEG, GIF, BMP.

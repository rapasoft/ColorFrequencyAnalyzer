       _____      _            ______                                                               _                    
      / ____|    | |          |  ____|                                            /\               | |                   
     | |     ___ | | ___  _ __| |__ _ __ ___  __ _ _   _  ___ _ __   ___ _   _   /  \   _ __   __ _| |_   _ _______ _ __ 
     | |    / _ \| |/ _ \| '__|  __| '__/ _ \/ _` | | | |/ _ \ '_ \ / __| | | | / /\ \ | '_ \ / _` | | | | |_  / _ \ '__|
     | |___| (_) | | (_) | |  | |  | | |  __/ (_| | |_| |  __/ | | | (__| |_| |/ ____ \| | | | (_| | | |_| |/ /  __/ |   
      \_____\___/|_|\___/|_|  |_|  |_|  \___|\__, |\__,_|\___|_| |_|\___|\__, /_/    \_\_| |_|\__,_|_|\__, /___\___|_|   
                                                | |                       __/ |                        __/ |             
                                                |_|                      |___/                        |___/              
This tool calculates percentage of area in the image that the specific color occupies. Supported file types: PNG, JPG, JPEG, GIF, BMP.

For now it is really simple, no fuzzy-logic or threshold is supported. The algorithm goes pixel by pixel and stores number of occurences for each of the color in the image (RGB). Then it calculates frequency compared to total amount of pixels in the image.

This is basically a project to test Weld & JavaFX cooperation :). And it works!
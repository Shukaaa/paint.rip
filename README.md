# paint.rip

![paint.rip](src/main/resources/images/paintrip.png)

A simple Java application to manipulate images in a variety of ways.

## Features

- Open, Save and Resize images
- Flip Images

### Effects

##### Grayscale

![grayscale](assets/grayscale.png)

Converts the image to grayscale.

Possible options:
- Treshold (0-256) - The value at which the image is converted to black and white.

##### Invert

![invert](assets/invert.png)

Inverts the colors of the image.

Possible options:
- Treshold (0-256) - The value at which the image is going to be inverted.

##### Invert with Treshold

![invert with treshold](assets/invert-treshold.png)

Inverts the colors of the image with a treshold of 100.

##### Melt

![melt](assets/melt-red.png)

Melt a color into the image.
In the example below, the red color is melted into the image.

Possible options:
- Color - The color to melt into the image.
- Treshold (0-256) - The value at which the image is going to be melted.

##### Black and White

![black and white](assets/black-and-white.png)

Distorts the image to black and white.

Possible options:
- Treshold (0-256) - The value at which the image is converted to black and white.

##### Row Slicer

![row slicer](assets/Row Slicer.png)

Fully distorts the image by sorting the rows of the image.

Possible options:
- RowSlicerMode - The mode of the row slicer. (RED, GREEN, BLUE)

##### Cos Sin Shuffler (Low Modulo Settings)

![cos sin shuffler](assets/cossin-modulo-low.png)

Shuffles the image by using the cos and sin functions.

Possible options:
- Modulo - The modulo of the cos and sin functions.

##### Cos Sin Shuffler (High Modulo Settings)

![cos sin shuffler](assets/cossin-modulo-high.png)

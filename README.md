#	Mandala - a functional fractal rendering system

## Introduction
I wrote this software out of frustration with traditional raster based fractal software. In typical software, the fractal function is sampled at regular points and this raster is then processed with filters to create a final image. The issue with this approach is that it leads to aliasing artifacts and other noise in the final image due to the wide bandwidth of the fractal functions, which is exacerbated by the use of nonlinear filters. 

This software takes a different approach. The fractal functions are transformed and filtered directly by other functions and only at the very last stage of processing is the image rasterized. This prevents quality degradation resulting from the use of rasters in intermediate steps.

## Technologies
- Java
- Apache Commons Math library
- JUnit
- Maven
- Git



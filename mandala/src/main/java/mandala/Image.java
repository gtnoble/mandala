package mandala;

import java.io.File;
import java.io.IOException;

import mil.nga.tiff.*;
import mil.nga.tiff.util.TiffConstants;

public class Image {
	double[][] pixels;
	int width;
	int height;
	
	public Image(double[][] pixels, int width, int height) {
		this.pixels = pixels;
		this.width = width;
		this.height = height;
	}
	
	public Image(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new double[width][height];
	}
	
	public double getPixel(int x, int y) {
		return(pixels[x][y]);
	}
	
	public void setPixel(int x, int y, double value) {
		pixels[x][y] = value;
	}
	
	public void writeTIFF(File tiffFile) {
		int samplesPerPixel = 1;
		FieldType pixelFormat = FieldType.FLOAT;
		int bitsPerPixel = pixelFormat.getBits();
		Rasters tiffRaster = new Rasters(width, height, samplesPerPixel, pixelFormat);
		int rowsPerStrip = tiffRaster.calculateRowsPerStrip(TiffConstants.PLANAR_CONFIGURATION_CHUNKY);

		FileDirectory tiffDirectory = new FileDirectory();
		tiffDirectory.setImageWidth(width);
		tiffDirectory.setImageHeight(height);
		tiffDirectory.setBitsPerSample(bitsPerPixel);
		tiffDirectory.setCompression(TiffConstants.COMPRESSION_NO);
		tiffDirectory.setPhotometricInterpretation(TiffConstants.PHOTOMETRIC_INTERPRETATION_BLACK_IS_ZERO);
		tiffDirectory.setSamplesPerPixel(samplesPerPixel);
		tiffDirectory.setRowsPerStrip(rowsPerStrip);
		tiffDirectory.setPlanarConfiguration(TiffConstants.PLANAR_CONFIGURATION_CHUNKY);
		tiffDirectory.setSampleFormat(TiffConstants.SAMPLE_FORMAT_FLOAT);
		tiffDirectory.setWriteRasters(tiffRaster);
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				float pixelValue = (float) pixels[x][y];
				tiffRaster.setFirstPixelSample(x, y, pixelValue);
			}
		}
		
		TIFFImage tiffImage = new TIFFImage();
		tiffImage.add(tiffDirectory);
		
		try {
			TiffWriter.writeTiff(tiffFile, tiffImage);
		} catch (IOException e) {
			System.err.println("ERROR: could not write TIFF image file.");
			e.printStackTrace();
		}
		
	}

}

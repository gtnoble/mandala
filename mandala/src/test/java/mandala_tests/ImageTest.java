package mandala_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mandala.Raster;

class ImageTest {
	
	static Raster whiteSquareImage;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		final double[][] whiteSquarePixels = {{0.0, 0.0, 0.0, 0.0},
										{0.0, 1.0, 1.0, 0.0},
										{0.0, 1.0, 1.0, 0.0},
										{0.0, 0.0, 0.0, 0.0}};
		
		whiteSquareImage = new Raster(whiteSquarePixels, 4, 4);

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testWriteTIFF() {
		Path imagePath = Paths.get("src", "test", "resources", "mandala_tests", "whiteSquare.tiff");
		whiteSquareImage.writeTIFF(imagePath.toFile());
	}

}

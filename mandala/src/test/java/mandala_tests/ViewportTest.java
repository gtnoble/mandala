package mandala_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mandala.Scene;

class SceneTest {
	
	static Complex originCenter;
	static Scene squareScene;
	static Scene horizontalFlatScene;
	static Scene verticalFlatScene;
	
	static Complex bottomLeftPixelSceneCoord;
	static Complex bottomRightPixelSceneCoord;
	static Complex topLeftPixelSceneCoord;
	static Complex topRightPixelSceneCoord;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		originCenter = new Complex(0.0, 0.0);
		horizontalFlatScene = new Scene(originCenter, 5, 1, 1.0);
		verticalFlatScene = new Scene(originCenter, 1, 5, 5.0);
		squareScene = new Scene(originCenter, 5, 5, 5.0);
		
		bottomLeftPixelSceneCoord = originCenter.add(new Complex(-2, -2));
		bottomRightPixelSceneCoord = originCenter.add(new Complex(2, -2));
		topLeftPixelSceneCoord = originCenter.add(new Complex(-2, 2));
		topRightPixelSceneCoord = originCenter.add(new Complex(2, 2));
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
	void testGetScreenWidth() {
		assertAll("Screen width", 
				() -> assertEquals(5, horizontalFlatScene.getScreenWidth(), "5x1 scene shoud have 5 width"),
				() -> assertEquals(1, verticalFlatScene.getScreenWidth(), "1x5 scene should have 1 width"),
				() -> assertEquals(5, squareScene.getScreenWidth(), "5x5 scene shoud have 5 width"));
	}

	@Test
	void testGetScreenHeight() {
		assertAll("Screen height",
				() -> assertEquals(1, horizontalFlatScene.getScreenHeight(), "5x1 scene should have 1 height"),
				() -> assertEquals(5, verticalFlatScene.getScreenHeight(), "1x5 scene should have 5 height"),
				() -> assertEquals(5, squareScene.getScreenHeight(), "5x5 scene should have 5 height"));
	}

	@Test
	void testImageLayout() {
		Complex[][] horizontalLayout = horizontalFlatScene.imageLayout();
		Complex[][] verticalLayout = verticalFlatScene.imageLayout();
		Complex[][] squareLayout = squareScene.imageLayout();
		
		assertAll("Center pixel scene coordinate",
				() -> assertEquals(originCenter, horizontalLayout[2][0], 
						"5x1 scene center pixel should have (0,0) coord"),
				() -> assertEquals(originCenter, verticalLayout[0][2], 
						"1x5 scene center pixel should have (0,0) coord"),
				() -> assertEquals(originCenter, squareLayout[2][2], 
						"5x5 scene center pixel shoud have (0,0) coord"));
		
		assertAll("Corner pixel tests", 
				() -> assertEquals(topRightPixelSceneCoord, squareLayout[4][4]),
				() -> assertEquals(topLeftPixelSceneCoord, squareLayout[0][4]),
				() -> assertEquals(bottomLeftPixelSceneCoord, squareLayout[0][0]),
				() -> assertEquals(bottomRightPixelSceneCoord, squareLayout[4][0]));
		
		
		
	}

}

package mandala_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mandala.CoordinateTransformation;
import mandala.Viewport;
import mandala.VirtualCamera;
import mandala.XYPoint;

class ViewportTest {
	
	static XYPoint<Double> originCenter;
	static Viewport squareViewport,
					horizontalFlatViewport,
					verticalFlatViewport;
	
	static XYPoint<Double> bottomLeftPixelViewportCoord, 
						   bottomRightPixelViewportCoord,
						   topLeftPixelViewportCoord,
							topRightPixelViewportCoord;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		originCenter = new XYPoint<Double>(0.0, 0.0);
		horizontalFlatViewport = new Viewport(5, 1);
		verticalFlatViewport = new Viewport(1, 5);
		squareViewport = new Viewport(5, 5);
		
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
				() -> assertEquals(5, horizontalFlatViewport.getXDimension(), "5x1 scene shoud have 5 width"),
				() -> assertEquals(1, verticalFlatViewport.getXDimension(), "1x5 scene should have 1 width"),
				() -> assertEquals(5, squareViewport.getXDimension(), "5x5 scene shoud have 5 width"));
	}

	@Test
	void testGetScreenHeight() {
		assertAll("Screen height",
				() -> assertEquals(1, horizontalFlatViewport.getYDimension(), "5x1 scene should have 1 height"),
				() -> assertEquals(5, verticalFlatViewport.getYDimension(), "1x5 scene should have 5 height"),
				() -> assertEquals(5, squareViewport.getYDimension(), "5x5 scene should have 5 height"));
	}

	@Test
	void testImageLayout() {
		VirtualCamera dummyCameraHorizontal = new VirtualCamera(originCenter, 1.0);
		VirtualCamera dummyCameraVertical = new VirtualCamera(originCenter, 5.0);
		VirtualCamera dummyCameraSquare = new VirtualCamera(originCenter, 5.0);
		
		CoordinateTransformation horizontalLayout = dummyCameraHorizontal
				.sceneToViewportCoordinateTransformer(horizontalFlatViewport);
		CoordinateTransformation verticalLayout = dummyCameraVertical
				.sceneToViewportCoordinateTransformer(verticalFlatViewport); 
		CoordinateTransformation squareLayout = dummyCameraSquare
				.sceneToViewportCoordinateTransformer(squareViewport); 
		
		assertAll("Center pixel scene coordinate",
				() -> assertEquals(originCenter, horizontalLayout.transform(new XYPoint<Double>(2.0, 0.0)), 
						"5x1 scene center pixel should have (0,0) coord"),
				() -> assertEquals(originCenter, verticalLayout.transform(new XYPoint<Double>(0.0, 2.0)), 
						"1x5 scene center pixel should have (0,0) coord"),
				() -> assertEquals(originCenter, squareLayout.transform(new XYPoint<Double>(2.0, 2.0)), 
						"5x5 scene center pixel shoud have (0,0) coord"));
	//TODO Reimplement these checks more elegantly, using parameterized tests.
		/*
		assertAll("Corner pixel tests", 
				() -> assertEquals(topRightPixelViewportCoord, squareLayout[4][4]),
				() -> assertEquals(topLeftPixelViewportCoord, squareLayout[0][4]),
				() -> assertEquals(bottomLeftPixelViewportCoord, squareLayout[0][0]),
				() -> assertEquals(bottomRightPixelViewportCoord, squareLayout[4][0]));
		*/
		
		
	}

}

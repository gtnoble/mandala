package mandala_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mandala.XYPoint;
import mandala.visualizer.MandelbrotEscapeTime;
import mandala.visualizer.Visualizer;

class MandelbrotEscapeTimeTest {
	
	static Visualizer visualizer;
	final static int maxIterations = 100;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		visualizer = new MandelbrotEscapeTime(maxIterations);
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
	void testValue() {
		XYPoint<Double> neverEscape = new XYPoint<Double>(0.0, 0.0);
		assertEquals((double) maxIterations, visualizer.value(neverEscape), 
				     "point (0,0) should never escape");
		
		XYPoint<Double> immediatelyEscape = new XYPoint<Double>(10.0, 10.0);
		assertEquals(0.0, visualizer.value(immediatelyEscape),
					"point (10, 10) should escape immediately");
		
	}

}

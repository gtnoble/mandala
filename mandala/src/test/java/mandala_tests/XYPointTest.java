package mandala_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mandala.XYPoint;

class XYPointTest {
	
	static XYPoint<Double> point1, point2, point1Duplicate;
	static double point1X, point1Y;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		point1X = 1.0;
		point1Y = 0.0;
		point1 = new XYPoint<Double>(point1X, point1Y);
		point1Duplicate = new XYPoint<Double>(1.0, 0.0);
		point2 = new XYPoint<Double>(1.0, 1.0);
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
	void testHashCode() {
		assertEquals(point1.hashCode(), point1Duplicate.hashCode(), 
				"identically initialized points should have same hashcode.");
		
		assertNotEquals(point1.hashCode(), point2.hashCode(), 
				"differently initialized points should (usually) have different hash codes.");
	}

	@Test
	void testGetX() {
		assertEquals(point1.getX(), point1X);
	}


	@Test
	void testGetY() {
		assertEquals(point1.getY(), point1Y);
	}

	@Test
	void testEqualsXYPointOfT() {
		assertEquals(point1, point1Duplicate, 
				"Identically initialized points should be equal.");
		
		assertNotEquals(point1, point2, "Differently initialized points should not be equal");
		
		assertNotEquals(point1, null, 
				"A valid object should never be equal to a null object.");
	}


	@Test
	void testToComplex() {
		assertEquals(point1.toComplex(), new Complex(point1X, point1Y));
	}

	@Test
	void testToDoublePoint() {
		assertEquals(point1.toDoublePoint(), new XYPoint<Double>(point1X, point1Y));
	}

}

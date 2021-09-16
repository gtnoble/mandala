package mandala_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import mandala.FractintParameters;

class FractintFileReaderTest {
	
	static FractintParameters testParameters;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Path testFilePath = Paths.get("src", "test", "resources", "mandala_tests", "fractint_params.par");
		testParameters = new FractintParameters(testFilePath);
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
	@Disabled
	void testFractintParameters() {
		fail("Not yet implemented");
	}

	@Test
	void testCenter() {
		double real = Double.parseDouble("-1.25716839231694433");
		double imag = Double.parseDouble("0.38008325342230764");
		Complex center = new Complex(real, imag);
		
		assertEquals(center, testParameters.center(), "Unexpected center value");
		
	}

	@Test
	void testZoom() {
		double zoom = 1.0 / Double.parseDouble("105.0408");
		assertEquals(zoom, testParameters.zoom(), "Unexpected zoom value");
	}

	@Test
	@Disabled
	void testGetParameterNames() {
		fail("Not yet implemented");
	}

	@Test
	@Disabled
	void testGetParameterValue() {
		fail("Not yet implemented");
	}

}

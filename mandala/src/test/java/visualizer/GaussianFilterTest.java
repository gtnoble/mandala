package visualizer;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.math3.analysis.integration.TrapezoidIntegrator;
import org.apache.commons.math3.analysis.integration.UnivariateIntegrator;
import org.apache.commons.math3.complex.Complex;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import mandala.visualizer.GaussianFilter;
import mandala.visualizer.Visualizer;

class GaussianFilterTest {
	static final double TOLERABLE_ERROR = 0.001;
	
	final int MAX_EVALUATIONS = 10000;
	Visualizer visualizer = new ConstantVisualizer();
	UnivariateIntegrator integratorX = new TrapezoidIntegrator();
	UnivariateIntegrator integratorY = new TrapezoidIntegrator();
	GaussianFilter filter = new GaussianFilter(visualizer, 1, integratorX, integratorY, MAX_EVALUATIONS, 2);

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
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
		double constantOneIntegral = filter.value(new Complex(0,0));
		assertTrue(Math.abs(constantOneIntegral - 1) <= TOLERABLE_ERROR, 
				   "integration of constant one function failed: expected 1.0, actual " + constantOneIntegral);
	}

}

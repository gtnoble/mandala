package filter;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.math3.analysis.integration.TrapezoidIntegrator;
import org.apache.commons.math3.analysis.integration.UnivariateIntegrator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import mandala.XYPoint;
import mandala.filter.GaussianFilter;
import mandala.visualizer.Visualizer;
import visualizer.ConstantVisualizer;

class GaussianFilterTest {
	
	public static double[] constantFunctionValues() {
		return new double[] {0, 1};
	}

	static final double TOLERABLE_ERROR = 0.001;
	
	static final int MAX_EVALUATIONS = 10000;
	Visualizer oneVisualizer = new ConstantVisualizer(1);
	
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

	@ParameterizedTest
	@MethodSource(value = "constantFunctionValues")
	void testConstantValueFunction(double constantFunctionValue) {
		Visualizer visualizer = new ConstantVisualizer(constantFunctionValue);

		UnivariateIntegrator integratorX = new TrapezoidIntegrator();
		UnivariateIntegrator integratorY = new TrapezoidIntegrator();
		
		XYPoint<Double> testEvaluationPoint = new XYPoint<Double>(0.0, 0.0);

		GaussianFilter filter = 
				new GaussianFilter(1, integratorX, integratorY, MAX_EVALUATIONS, 2);
		double constantIntegral = filter.filter(visualizer).value(testEvaluationPoint);

		assertTrue(Math.abs(constantIntegral - constantFunctionValue) <= TOLERABLE_ERROR, 
				   "integration of constant one function failed: expected " + 
					constantFunctionValue + 
					", actual " + constantIntegral);
	}

}

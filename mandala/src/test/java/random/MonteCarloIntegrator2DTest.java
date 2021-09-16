package random;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import mandala.TwoDimensionalFunction;

class MonteCarloIntegrator2DTest {

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
	void testMonteCarloIntegrator2D() {
		
		assertThrows(IllegalArgumentException.class, () -> {
			RandomGeneratorType randType = RandomGeneratorType.SOBOL;
			new MonteCarloIntegrator2D(1.0, 1, 0, randType, 1);
			new MonteCarloIntegrator2D(1.0, 1 , 5, randType, 1);

		});
		
	}
	
	public static RandomGeneratorType[] randomGeneratorType() {
		return new RandomGeneratorType[] {RandomGeneratorType.SOBOL, 
										  RandomGeneratorType.HALTON, 
										  RandomGeneratorType.RANDOM};
	}

	@ParameterizedTest
	@MethodSource(value = "randomGeneratorType")
	void testIntegrate(RandomGeneratorType randomGeneratorType) {
		double precision = 0.1;
		double actualIntegral = 1.0;
		double acceptableDeviation = 6 * precision;
		double samplerStdDev = 1;
		int randomSeed = 1;
		int minIterations = 10;
		int maxIterations = 10000;

		MonteCarloIntegrator2D integrator;
		integrator = new MonteCarloIntegrator2D(precision, 
												maxIterations,
												minIterations,
												randomGeneratorType,
												randomSeed);

		Sampler sampler = new GaussianSampler2D(samplerStdDev);
		
		TwoDimensionalFunction unitSquareIntegrand = (x, y) -> {
			if(x >= -.5 && x <= .5 && y >= -.5 && y <= .5) {
				return 1;
			}
			else return 0;
		};
		
		double integral = integrator.integrate(unitSquareIntegrand, sampler);
		
		assertTrue(Math.abs(integral - actualIntegral) <= acceptableDeviation);
	}

}

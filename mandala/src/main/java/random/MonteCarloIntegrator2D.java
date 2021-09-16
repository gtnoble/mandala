package random;

import org.apache.commons.math3.random.HaltonSequenceGenerator;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomVectorGenerator;
import org.apache.commons.math3.random.SobolSequenceGenerator;
import org.apache.commons.math3.random.SynchronizedRandomGenerator;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import mandala.TwoDimensionalFunction;

public class MonteCarloIntegrator2D {
	Sampler importanceSampler;
	double precision;
	double maxIterations;
	double minIterations;
	SynchronizedRandomGenerator synchronizedRandomNumberGenerator;
	RandomGeneratorType randomGeneratorType;
	
	public MonteCarloIntegrator2D(double precision,
								  int maxIterations, 
								  int minIterations, 
								  RandomGeneratorType randomGeneratorType, 
								  int seed) {
		
		if(minIterations <= 0) {
			throw new IllegalArgumentException("minIterations must be greater than zero");
		}
		
		if(maxIterations < minIterations) {
			throw new IllegalArgumentException("maxIterations must be greater than or equal to minIterations");
		}
		

		this.precision = Math.abs(precision);
		this.maxIterations = maxIterations;
		this.minIterations = minIterations;
		this.randomGeneratorType = randomGeneratorType;
		
		this.synchronizedRandomNumberGenerator = new SynchronizedRandomGenerator(new MersenneTwister(seed));
	}
	
	public double integrate(TwoDimensionalFunction integrand, Sampler importanceSampler) {
		SummaryStatistics result = new SummaryStatistics();
		RandomVectorGenerator randomVectorGenerator = getRandomVectorGenerator(randomGeneratorType);

		
		int iterationCount = 0;
		while(iterationCount < maxIterations &&
				(iterationCount < minIterations || standardError(result) >= precision)) {
		
			double[] sample = importanceSampler.sample(randomVectorGenerator);
			double sampleX = sample[0];
			double sampleY = sample[1];
			double density = importanceSampler.density(sampleX, sampleY);
			
			result.addValue(integrand.evaluate(sampleX, sampleY) / density);
			iterationCount++;
			
		}
		
		return result.getMean();
	}
	
	private double standardError(SummaryStatistics data) {
		return data.getStandardDeviation() / Math.sqrt(data.getN());
	}
	
	private RandomVectorGenerator getRandomVectorGenerator(RandomGeneratorType type) {
		switch(type) {
		case RANDOM:
			return new StandardUniformVectorGenerator(synchronizedRandomNumberGenerator, 2);
		case SOBOL:
			return new SobolSequenceGenerator(2);
		case HALTON:
			return new HaltonSequenceGenerator(2);
		default:
			throw new IllegalArgumentException("Random vector generator" + type + "is not implemented.");
		}
	}
}

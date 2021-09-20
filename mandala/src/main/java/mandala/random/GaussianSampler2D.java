package mandala.random;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.random.RandomVectorGenerator;

public class GaussianSampler2D implements Sampler {
	
	double standardDeviation;
	NormalDistribution gaussianDistribution;
	
	public GaussianSampler2D(double standardDeviation) {
		this.standardDeviation = standardDeviation;
		this.gaussianDistribution = new NormalDistribution(0.0, standardDeviation);
	}

	@Override
	public double[] sample(RandomVectorGenerator randomVector) {
		double[] randomStandardUniformXY = randomVector.nextVector();
		double x = randomStandardUniformXY[0];
		double y = randomStandardUniformXY[1];
		
		//generated X and Y values of zero or one lead to infinite gaussian transformed coordinates
		while(x == 0.0 || x == 1.0 || y == 0.0 || y == 1.0) {
			randomStandardUniformXY = randomVector.nextVector();
			x = randomStandardUniformXY[0];
			y = randomStandardUniformXY[1];
		}
	
		return new double[] {gaussianDistribution.inverseCumulativeProbability(x),
							 gaussianDistribution.inverseCumulativeProbability(y)};
	}

	@Override
	public double density(double x, double y) {
		return gaussianDistribution.density(x) * gaussianDistribution.density(y);
	}

}

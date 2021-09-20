package mandala.random;

import org.apache.commons.math3.random.RandomVectorGenerator;

public interface Sampler {
	public double[] sample(RandomVectorGenerator randomVector);
	public double density(double x, double y);
}

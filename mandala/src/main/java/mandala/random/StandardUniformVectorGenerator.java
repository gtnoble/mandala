package mandala.random;

import org.apache.commons.math3.random.RandomVectorGenerator;
import org.apache.commons.math3.random.SynchronizedRandomGenerator;

public class StandardUniformVectorGenerator implements RandomVectorGenerator {
	
	SynchronizedRandomGenerator randomNumberGenerator;
	int numberOfDimensions;
	
	public StandardUniformVectorGenerator(SynchronizedRandomGenerator randomNumberGenerator, int numberOfDimensions) {

		this.randomNumberGenerator = randomNumberGenerator;
		this.numberOfDimensions = numberOfDimensions;
		
	}

	@Override
	public double[] nextVector() {
		// TODO Auto-generated method stub
		double[] randomVector = new double[numberOfDimensions];
		
		for(int i = 0; i < numberOfDimensions; i++) {
			randomVector[i] = randomNumberGenerator.nextDouble();
		}
		
		return randomVector;
	}

}

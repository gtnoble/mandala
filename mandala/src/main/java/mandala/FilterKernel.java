package mandala;

public class FilterKernel {
	TwoDimensionalFunction kernelFunction;
	TwoDimensionalFunction samplingDistributionInverseTransform;
	TwoDimensionalFunction samplingDistributionDensity;
	
	public FilterKernel(TwoDimensionalFunction kernelFunction,
						TwoDimensionalFunction samplingDistributionInverseTransform,
						TwoDimensionalFunction samplingDistributionDensity) {

		this.kernelFunction = kernelFunction;
		this.samplingDistributionDensity = samplingDistributionDensity;
		this.samplingDistributionInverseTransform = samplingDistributionInverseTransform;
	}
	
	public double evaluateKernelFunction(double x, double y) {
		return kernelFunction.evaluate(x, y);
	}
	
	public double evaluateSamplingDistributionInverseTransform(double x, double y) {
		return samplingDistributionInverseTransform.evaluate(x, y);
	}
	
	public double evaluateSamplingDistributionDensity(double x, double y) {
		return samplingDistributionDensity.evaluate(x, y);
	}
}

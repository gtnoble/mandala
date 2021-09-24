package mandala.filter;

import java.util.function.Function;

import org.apache.commons.math3.analysis.integration.UnivariateIntegrator;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.distribution.NormalDistribution;

import mandala.TwoDimensionalFunction;
import mandala.visualizer.Visualizer;

public class GaussianFilter extends Filter {
	Function<Visualizer, Visualizer> gaussianFilterTransform;
	
	public GaussianFilter(double kernelStandardDeviationPixels, 
						  UnivariateIntegrator integratorX, 
						  UnivariateIntegrator integratorY,
						  int maxEvaluations,
						  double transformScaleFactor) {

		gaussianFilterTransform = (inputVisualizer) -> this.new GaussianFilterVisualizer(inputVisualizer, 
				kernelStandardDeviationPixels, 
				transformScaleFactor,
				integratorX,
				integratorY,
				maxEvaluations);
		
	}
	
	public Visualizer filter(Visualizer inputVisualizer) {
		return gaussianFilterTransform.apply(inputVisualizer);
	}
	
	public class GaussianFilterVisualizer implements Visualizer{
		
		UnivariateIntegrator integratorX, integratorY;
		double transformStandardDeviation;
		double kernelStandardDeviationScene;
		Visualizer inputVisualizer;
		int maxEvaluations;

		public double getInterPixelDistance() {
			return inputVisualizer.getInterPixelDistance();
		}
		
		public GaussianFilterVisualizer(Visualizer inputVisualizer, 
										double kernelStandardDeviationPixels, 
										double transformScaleFactor,
										UnivariateIntegrator integratorX,
										UnivariateIntegrator integratorY,
										int maxEvaluations) {

			this.kernelStandardDeviationScene = kernelStandardDeviationPixels * inputVisualizer.getInterPixelDistance();
			this.transformStandardDeviation = transformScaleFactor * kernelStandardDeviationScene;
			this.inputVisualizer = inputVisualizer;
			this.integratorX = integratorX;
			this.integratorY = integratorY;
			this.maxEvaluations = maxEvaluations;
		}

		@Override
		public double value(Complex location) {

			NormalDistribution filterKernelX = new NormalDistribution(location.getReal(), kernelStandardDeviationScene);
			NormalDistribution filterKernelY = new NormalDistribution(location.getImaginary(), kernelStandardDeviationScene);
			
			NormalDistribution transformerX = new NormalDistribution(location.getReal(), transformStandardDeviation);
			NormalDistribution transformerY = new NormalDistribution(location.getImaginary(), transformStandardDeviation);
			
			TwoDimensionalFunction integrand = (h, k) -> 
				{ 
					double x = transformerX.inverseCumulativeProbability(h);
					double y = transformerY.inverseCumulativeProbability(k);
					double inf = Double.POSITIVE_INFINITY;
					double negInf = Double.NEGATIVE_INFINITY;
					
					//Integrand vanishes at infinity
					if(x == inf || x == negInf || y == inf || y == negInf) return 0;

					double visualizedValue = inputVisualizer.value(new Complex(x, y));

					double kernelWeight = filterKernelX.density(x) * filterKernelY.density(y);
					double transformWeight = transformerX.density(x) * transformerY.density(y);
					
					return visualizedValue * kernelWeight / transformWeight;
				};
			
			double integral = integratorX.integrate(maxEvaluations, 
												   (h) -> integratorY.integrate(maxEvaluations, 
																				  (k) -> integrand.evaluate(h, k), 
																				  0, 1), 
												   0, 1);
			
			return integral;
		}
	}

}

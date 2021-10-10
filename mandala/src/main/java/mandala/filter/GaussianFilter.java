package mandala.filter;

import java.util.function.Function;

import org.apache.commons.math3.analysis.integration.UnivariateIntegrator;
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

		public GaussianFilterVisualizer(Visualizer inputVisualizer, 
										double kernelStandardDeviationPixels, 
										double transformScaleFactor,
										UnivariateIntegrator integratorX,
										UnivariateIntegrator integratorY,
										int maxEvaluations) {

			this.transformStandardDeviation = transformScaleFactor * kernelStandardDeviationScene;
			this.inputVisualizer = inputVisualizer;
			this.integratorX = integratorX;
			this.integratorY = integratorY;
			this.maxEvaluations = maxEvaluations;
		}

		@Override
		public double value(double[] coordinates) {
			double xCoord = coordinates[0];
			double yCoord = coordinates[1];

			NormalDistribution filterKernelX = new NormalDistribution(xCoord, kernelStandardDeviationScene);
			NormalDistribution filterKernelY = new NormalDistribution(yCoord, kernelStandardDeviationScene);
			
			NormalDistribution transformerX = new NormalDistribution(xCoord, transformStandardDeviation);
			NormalDistribution transformerY = new NormalDistribution(yCoord, transformStandardDeviation);
			
			TwoDimensionalFunction integrand = (h, k) -> 
				{ 
					double x = transformerX.inverseCumulativeProbability(h);
					double y = transformerY.inverseCumulativeProbability(k);
					double inf = Double.POSITIVE_INFINITY;
					double negInf = Double.NEGATIVE_INFINITY;
					
					//Integrand vanishes at infinity
					if(x == inf || x == negInf || y == inf || y == negInf) return 0;

					double visualizedValue = inputVisualizer.value(new double[] {x, y});

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

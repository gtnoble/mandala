package mandala.visualizer;

import org.apache.commons.math3.analysis.integration.UnivariateIntegrator;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.distribution.NormalDistribution;

import mandala.TwoDimensionalFunction;

public class GaussianFilter implements Visualizer {
	Visualizer inputVisualizer;
	double kernelStandardDeviation;
	UnivariateIntegrator integratorX;
	UnivariateIntegrator integratorY;
	double transformStandardDeviation;
	int maxEvaluations;
	
	public GaussianFilter(Visualizer inputVisualizer, 
						  double kernelStandardDeviation, 
						  UnivariateIntegrator integratorX, 
						  UnivariateIntegrator integratorY,
						  int maxEvaluations,
						  double transformScaleFactor) {
		
		this.inputVisualizer = inputVisualizer;
		this.kernelStandardDeviation = kernelStandardDeviation;
		this.integratorX = integratorX;
		this.integratorY = integratorY;
		this.transformStandardDeviation = transformScaleFactor * kernelStandardDeviation;
		this.maxEvaluations = maxEvaluations;
	}

	@Override
	public double value(Complex location) {

		NormalDistribution filterKernelX = new NormalDistribution(location.getReal(), kernelStandardDeviation);
		NormalDistribution filterKernelY = new NormalDistribution(location.getImaginary(), kernelStandardDeviation);
		
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

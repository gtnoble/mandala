package mandala.filter;

import java.util.function.Function;

import mandala.visualizer.Visualizer;

public interface Filter extends Function<Visualizer, Visualizer>{
	public Visualizer apply(Visualizer inputVisualizer);
}

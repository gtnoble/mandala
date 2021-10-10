package mandala_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import mandala.BasicRasterizer;
import mandala.FractintParameters;
import mandala.Image;
import mandala.ParallelBasicRenderer;
import mandala.Rasterizer;
import mandala.Scene;
import mandala.visualizer.MandelbrotEscapeTime;
import mandala.visualizer.Visualizer;

class RendererTest {
	
	public static Rasterizer[] rendererUnderTest() {
		return new Rasterizer[] {new BasicRasterizer(), new ParallelBasicRenderer()};
	}
	
	//static Renderer renderer;
	static Scene scene;
	static Image image;
	static Visualizer visualizer;
	static Path resourcesPath;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		int screenXSize = 256;
		int screenYSize = 256;
		int maxIterations = 100;
		
		//renderer = rendererUnderTest;
		resourcesPath = Paths.get("src", "test", "resources", "mandala_tests");
		Path testFilePath = resourcesPath.resolve("fractint_params.par");
		FractintParameters parameters = new FractintParameters(testFilePath);
		
		scene = new Scene(parameters.center(), screenXSize, screenYSize, parameters.zoom());
		visualizer = new MandelbrotEscapeTime(maxIterations, scene.getInterPixelDistance());
		//image = new Image(screenXSize, screenYSize);
		
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

	@ParameterizedTest
	@MethodSource(value = "rendererUnderTest")
	void testRenderScene(Rasterizer renderer) {
		image = renderer.renderScene(scene, visualizer);
		Path imagePath = resourcesPath.resolve(renderer.getClass().getSimpleName() + "_" + "fractintTestImage.tiff");
		//File imageFile = imagePath.toFile();
		image.writeTIFF(imagePath.toFile());
		//fail();
	}

}

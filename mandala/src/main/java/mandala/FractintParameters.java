package mandala;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.MatchResult;

import org.apache.commons.math3.complex.Complex;

public class FractintParameters {
	
	HashMap<String, String> parameters = new HashMap<String, String>();
	String centerMagnificationParameters = new String();

	public FractintParameters(Path fractintParameterFile) {
		
		
		Pattern namePattern = Pattern.compile("\\S+(?=\\s*\\{)");
		Pattern parametersSectionPattern = Pattern.compile("(?<=\\{).*(?=\\})",
														   Pattern.DOTALL);
		Pattern parameterEntriesPattern = Pattern.compile("(?<=\\s)\\S*=\\S*(?=\\s)",
														  Pattern.DOTALL);
		Pattern parameterKeyPattern = Pattern.compile("^\\S*(?==)");
		Pattern parameterValuePattern = Pattern.compile("(?<==)\\S*$");
		
		String fractintParameterString = null;
		try {
			fractintParameterString = Files.readString(fractintParameterFile);
		} catch (IOException e) {
			System.err.println("ERROR: could not read fractint parameter file.");
			e.printStackTrace();
		}
		
		parameters.put("name", namePattern.split(fractintParameterString)[0]);
		
		//String parameterSection = parametersSectionPattern.split(fractintParameterString)[0];
		String parameterSection = getRegexMatches(parametersSectionPattern,
												  fractintParameterString)[0];
					
		//String[] parameterEntries = parameterEntriesPattern.split(parameterSection);
		String[] parameterEntries = getRegexMatches(parameterEntriesPattern,
													parameterSection);
		
		for(String entry : parameterEntries) {
			//String key = parameterKeyPattern.split(entry)[0];
			String key = getRegexMatches(parameterKeyPattern, entry)[0];
			//String value = parameterValuePattern.split(entry)[0];
			String value = getRegexMatches(parameterValuePattern, entry)[0];
			parameters.put(key, value); 
		}
		
		centerMagnificationParameters = parameters.get("center-mag");
	}
	
	private String[] getRegexMatches(Pattern pattern, String inputString) {
		String[] outputString = pattern.matcher(inputString)
									 .results()
									 .map(MatchResult::group)
									 .toArray(String[]::new);
		return outputString;
	}

	public XYPoint<Double> center() {
		Pattern centerPattern = Pattern.compile("(?>-|\\+)\\d\\.?\\d*");
		//String[] centerCoordinatesStrings = centerPattern.split(centerMagnificationParameters);
		String[] centerCoordinatesStrings = getRegexMatches(centerPattern,
															centerMagnificationParameters);
		double realPart = Double.parseDouble(centerCoordinatesStrings[0]);
		double imagPart = Double.parseDouble(centerCoordinatesStrings[1]);
		return new XYPoint<Double>(realPart, imagPart);
	}
	
	public double zoom() {
		Pattern zoomPattern = Pattern.compile("(?<=\\/)\\d*.?\\d*$");
		//double zoom = 1.0 / Double.parseDouble(zoomPattern.split(centerMagnificationParameters)[0]);
		double zoom = 1.0 / Double
						   .parseDouble(getRegexMatches(zoomPattern, 
								   						centerMagnificationParameters)[0]);
		return zoom;
	}
	
	public String[] getParameterNames() {
		String[] keys = parameters.keySet().toArray(new String[0]);
		return keys;
	}
	
	public String getParameterValue(String parameterName) {
		return parameters.get(parameterName);
	}
}

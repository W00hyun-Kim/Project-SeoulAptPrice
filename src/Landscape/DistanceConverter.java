package Landscape;
public class DistanceConverter {
	
	public static String DistanceConverter(String input) {
		String result = "";
		if(input.contains("km")) {
			Double km = Double.parseDouble(input.split("km")[0]);
			result ="" + (int)(km * 1000);
		} else {
			result =input.split("m")[0];
		}
		return result;

	}

}

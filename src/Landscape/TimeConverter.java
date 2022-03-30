package Landscape;
public class TimeConverter {

	public static String TimeConverter (String input) {
		
		String time=input.replace(" ", "");
		if(time.contains("시간")) {
			String[] str = time.split("시간");
			int sigan=Integer.parseInt(str[0])*60;
			System.out.println(sigan);
					
			int bun = Integer.parseInt(str[1].split("분")[0]);
			System.out.println(bun);
			int timeconverter = sigan + bun;
			time = ""+timeconverter;
		} else {
			String[] str2 = time.split("분");
			time = str2[0];
		}		
		return time;
	}
		
}

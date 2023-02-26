package pp;

import java.util.Random;

public class RandomHexColorGenerator {

	public static void main(String[] args) {
		int counter = 0;
    // loops six times
		while(counter < 6) {
			generateRandomColor();
			counter++;
		}
	}

	public static void generateRandomColor() {
		// array needs to bne six lenght'
		char[] stuffToPickFrom = "012cbaFEDCBA9876543def".toCharArray();
		char[] randomColor = new char[7];
		randomColor[0] = '#';
		
		int randomNum = 0;
		
		Random random = new Random();
		
		for(int i = 1; i < randomColor.length; i++) {
			randomNum = random.nextInt(22);
			randomColor[i] = stuffToPickFrom[randomNum];
		}
		
		for(int i = 0; i < randomColor.length; i++) {
			System.out.print(randomColor[i]);
		}
		
		System.out.println();
	}
	
}

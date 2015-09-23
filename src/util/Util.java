package util;



public class Util {

	public static void waitSeconds(int seconds) {
		System.out.print("Pausing for " + seconds + " seconds: ");
		try {
			Thread.currentThread();		
			int x = 1;
			while(x <= seconds) {
				Thread.sleep(1000);
				System.out.print(" " + x );
				x = x + 1;
			}
			System.out.print('\n');
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}	
	}
	
	
	
	

	
}

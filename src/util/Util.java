package util;

public class Util {

	public static void waitSeconds(int secons) {
		System.out.print("Pausing for " + secons + " seconds: ");
		try {
			Thread.currentThread();		
			int x = 1;
			while(x <= secons) {
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

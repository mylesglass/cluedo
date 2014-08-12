package cluedo;

class MyUtils {
	private static boolean testing = true;

	/**
	 * Simple Logging Tool
	 * Used instead of System.out.println because it makes it easy to remove all debugging statements,
	 * just change the testing boolean to false, and they are no longer there.
	 * @param String to be printed
	 */
	public static void Log(String input) {
		if(testing) {
			System.out.println(input);
		}
	}
}

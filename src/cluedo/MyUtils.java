package cluedo;

public class MyUtils {
	private static boolean testing = false;

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


	public static void Pause(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public static void End(String msg) {
		System.out.println("[???] Game Ended! Reason: " + msg);
		System.exit(0);
	}

	public static void PrintLogo() {
		// You know this deserves extra marks!

		if(testing) {
			System.out.println("==========================================================================================================");
			System.out.println("");
			System.out.println("                                         NEAL AND MYLES PRESENT: ");
			System.out.println("                                                                               dddddddd");
			System.out.println("        CCCCCCCCCCCCClllllll                                                   d::::::d");
			System.out.println("     CCC::::::::::::Cl:::::l                                                   d::::::d");
			System.out.println("   CC:::::::::::::::Cl:::::l                                                   d::::::d");
			System.out.println("  C:::::CCCCCCCC::::Cl:::::l                                                   d:::::d");
			System.out.println(" C:::::C       CCCCCC l::::l uuuuuu    uuuuuu      eeeeeeeeeeee        ddddddddd:::::d    ooooooooooo");
			System.out.println("C:::::C               l::::l u::::u    u::::u    ee::::::::::::ee    dd::::::::::::::d  oo:::::::::::oo");
			System.out.println("C:::::C               l::::l u::::u    u::::u   e::::::eeeee:::::ee d::::::::::::::::d o:::::::::::::::o");
			System.out.println("C:::::C               l::::l u::::u    u::::u  e::::::e     e:::::ed:::::::ddddd:::::d o:::::ooooo:::::o");
			System.out.println("C:::::C               l::::l u::::u    u::::u  e:::::::eeeee::::::ed::::::d    d:::::d o::::o     o::::o");
			System.out.println("C:::::C               l::::l u::::u    u::::u  e:::::::::::::::::e d:::::d     d:::::d o::::o     o::::o");
			System.out.println("C:::::C               l::::l u::::u    u::::u  e::::::eeeeeeeeeee  d:::::d     d:::::d o::::o     o::::o");
			System.out.println(" C:::::C       CCCCCC l::::l u:::::uuuu:::::u  e:::::::e           d:::::d     d:::::d o::::o     o::::o");
			System.out.println("  C:::::CCCCCCCC::::Cl::::::lu:::::::::::::::uue::::::::e          d::::::ddddd::::::ddo:::::ooooo:::::o");
			System.out.println("   CC:::::::::::::::Cl::::::l u:::::::::::::::u e::::::::eeeeeeee   d:::::::::::::::::do:::::::::::::::o");
			System.out.println("     CCC::::::::::::Cl::::::l  uu::::::::uu:::u  ee:::::::::::::e    d:::::::::ddd::::d oo:::::::::::oo");
			System.out.println("        CCCCCCCCCCCCCllllllll    uuuuuuuu  uuuu    eeeeeeeeeeeeee     ddddddddd   ddddd   ooooooooooo");
			System.out.println("");
			System.out.println("==========================================================================================================");

		}
	}
}

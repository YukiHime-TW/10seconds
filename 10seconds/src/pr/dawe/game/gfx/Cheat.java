package pr.dawe.game.gfx;

import java.io.*;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;

public class Cheat {

	public static int cheat = 0;
	private static Formatter output;
	private static Scanner input;

	public static void openFile() throws FileNotFoundException {
		output = new Formatter("Cheat.txt");
	}

	public static void writeIn() {
		output.format("%d", cheat);
		if(output == null) {
			System.out.print("write in success\n");
		}
	}

	public static void closeFile() {
		if (output != null) {
			output.close();
		}
	}

	public static void openFileR() throws IOException {
		input = new Scanner(Paths.get("Cheat.txt"));
	}

	public static void readOut() {
		try {
			cheat = input.nextInt();
		} catch (NullPointerException e) {
			if (input == null) {
				System.out.printf("Empty\n");
			} else {
				System.out.printf("Can't read.\n");
			}
		}

	}

	public static void closeFileR() {
		if (input != null) {
			input.close();
		}
	}
}

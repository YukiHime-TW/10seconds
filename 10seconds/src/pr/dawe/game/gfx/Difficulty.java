package pr.dawe.game.gfx;

import java.io.*;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;

public class Difficulty {

	public static int diff = 0;
	private static Formatter output;
	private static Scanner input;

	public static void openFile() throws FileNotFoundException {
		output = new Formatter("Difficulty.txt");
	}

	public static void writeIn(int diff) {
		output.format("%d", diff);
	}

	public static void closeFile() {
		if (output != null) {
			output.close();
		}
	}

	public static void openFileR() throws IOException {
		input = new Scanner(Paths.get("Difficulty.txt"));
	}

	public static void readOut() {
		diff = input.nextInt();
	}

	public static void closeFileR() {
		if (input != null) {
			input.close();
		}
	}
}

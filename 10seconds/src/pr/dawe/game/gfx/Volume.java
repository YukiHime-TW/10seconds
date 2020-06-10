package pr.dawe.game.gfx;

import java.io.*;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.Scanner;

public class Volume {

	public static int volume;
	private static Formatter output;
	private static Scanner input;

	public static void openFile() throws FileNotFoundException {
		output = new Formatter("Volume.txt");
	}

	public static void writeIn(int volume) {
		output.format("%d", volume);
	}

	public static void closeFile() {
		if (output != null) {
			output.close();
		}
	}

	public static void openFileR() throws IOException {
		input = new Scanner(Paths.get("Volume.txt"));
	}

	public static void readOut() {
		volume = input.nextInt();
	}

	public static void closeFileR() {
		if (input != null) {
			input.close();
		}
	}
}

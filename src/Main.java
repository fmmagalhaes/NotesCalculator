import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

	static Map<Integer, String> node = new HashMap<Integer, String>();
	static Map<String, String> notes2 = new HashMap<String, String>();

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

		fillMaps();
		System.out.println("usage: 'Chord =' or 'Chord + n'");
		while (true) {
			String str = in.nextLine().trim();
			if (str.matches(("[a-zA-Z](#|[a-zA-Z])?( +)?\\+( +)?[0-9]([0-9])?"))) {
				String[] s = str.split("\\+");
				if (s[0].trim().equals("A#"))
					s[0] = "Bb";
				else if (s[0].trim().equals("D#"))
					s[0] = "Eb";
				int chord = (Integer) getKeyFromValue(s[0].trim());
				int n = Integer.parseInt(s[1].trim());
				String newchord = node.get((chord + n) % 12);
				System.out.println("= " + newchord + "\n");
			} else if (str.matches(("[a-zA-Z](#|[a-zA-Z])?( +)?\\=( +)?"))) {
				String[] s = str.split("=");
				String chord = s[0].trim();
				System.out.println("= " + notes2.get(chord) + "\n");
			} else {
				System.out.println("usage: 'Chord =' or 'Chord + n'\n");
			}
		}
	}

	private static void fillMaps() {
		node.put(0, "C");
		node.put(1, "C#");
		node.put(2, "D");
		node.put(3, "Eb");
		node.put(4, "E");
		node.put(5, "F");
		node.put(6, "F#");
		node.put(7, "G");
		node.put(8, "G#");
		node.put(9, "A");
		node.put(10, "Bb");
		node.put(11, "B");

		/*chords2.put("C", "");
		chords2.put("C#", "");
		chords2.put("D", "");
		chords2.put("D#", "");
		chords2.put("E", "");
		chords2.put("F", "");
		chords2.put("F#", "");
		chords2.put("G", "");
		chords2.put("G#", "");
		chords2.put("A", "");
		chords2.put("A#", "");
		chords2.put("B", "");
		*/
		for (int note = 0; note < 12; note++) {
			String str = "";
			for (int n = 0; n < 12; n++) {
				if (n != 1 && n != 3 && n != 6 && n != 8 && n != 10 && n != note)
					str += node.get(n) + (Math.abs(note - n + 12)) % 12 + " ";
			}
			notes2.put(node.get(note), str);
		}
		notes2.put("A#", notes2.get("Bb"));
		notes2.put("D#", notes2.get("Eb"));
	}

	public static int getKeyFromValue(String value) {
		for (int o : node.keySet())
			if (node.get(o).equals(value))
				return o;
		return -1;
	}
}

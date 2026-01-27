import java.util.Scanner;
import java.util.LinkedHashSet;
import java.util.Set;

public class LexicalTwist {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the first word");
		String firstWord = scanner.nextLine();

		// Validate first word
		if (firstWord.contains(" ")) {
			System.out.println(firstWord + " is an invalid word");
			scanner.close();
			return;
		}

		System.out.println("Enter the second word");
		String secondWord = scanner.nextLine();

		// Validate second word
		if (secondWord.contains(" ")) {
			System.out.println(secondWord + " is an invalid word");
			scanner.close();
			return;
		}

		// Check if second word is reverse of first (case insensitive)
		String firstWordReversed = new StringBuilder(firstWord).reverse().toString();
		if (firstWordReversed.equalsIgnoreCase(secondWord)) {
			// Path 1: Second word is reversed version of first
			String reversed = new StringBuilder(firstWord).reverse().toString();
			String lowercase = reversed.toLowerCase();
			String withoutVowels = lowercase.replaceAll("[aeiou]", "@");
			System.out.println(withoutVowels);
		} else {
			// Path 2: Second word is not reversed version
			String combined = firstWord + secondWord;
			String uppercase = combined.toUpperCase();

			int vowelCount = 0;
			int consonantCount = 0;

			for (char c : uppercase.toCharArray()) {
				if (Character.isLetter(c)) {
					if ("AEIOU".indexOf(c) != -1) {
						vowelCount++;
					} else {
						consonantCount++;
					}
				}
			}

			if (vowelCount > consonantCount) {
				// Print first 2 unique vowels
				Set<Character> uniqueVowels = new LinkedHashSet<>();
				for (char c : uppercase.toCharArray()) {
					if ("AEIOU".indexOf(c) != -1) {
						uniqueVowels.add(c);
						if (uniqueVowels.size() == 2) {
							break;
						}
					}
				}
				for (char c : uniqueVowels) {
					System.out.print(c);
				}
				System.out.println();
			} else if (consonantCount > vowelCount) {
				// Print first 2 unique consonants
				Set<Character> uniqueConsonants = new LinkedHashSet<>();
				for (char c : uppercase.toCharArray()) {
					if (Character.isLetter(c) && "AEIOU".indexOf(c) == -1) {
						uniqueConsonants.add(c);
						if (uniqueConsonants.size() == 2) {
							break;
						}
					}
				}
				for (char c : uniqueConsonants) {
					System.out.print(c);
				}
				System.out.println();
			} else {
				System.out.println("Vowels and consonants are equal");
			}
		}

		scanner.close();
	}
}

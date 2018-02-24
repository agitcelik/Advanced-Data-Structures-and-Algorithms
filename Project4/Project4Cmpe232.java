import java.util.ArrayList;

public class Project4Cmpe232 {

	public void finiteAutomaton(char[] txt, char[] pat) {
		
		// PROPERTIES:
		
		int count = 0;
		String shift = "";
		int[][] transitionMatrix;
		int n = txt.length;
		int q = 0;
		String transition = "";
		for (int i = 0; i < pat.length; i++) {
			transition += pat[i];
		}
		transition += " ";
		
		// RESULT: 
		
		transitionMatrix = transition(pat);
		System.out.println();
		System.out.println("Result:");
		for (int i = 0; i < n; i++) {
			// Comparing the state and txt by using char code
			q = transitionMatrix[q][txt[i]]; // t = 116
			if (q != 0) {
				transition += q + " ";
			} else {
				transition += 0 + " ";
			}
			if (q == pat.length) { // If last states is hit, pattern is founded
				count++;
				shift += i - pat.length + 1 + ", ";
			}
		}
		System.out.println(count + " pattern occurrences were detected,");
		System.out.println("shifted positions: " + shift);
		System.out.println();
		for (int i = 0; i < pat.length; i++) {
			System.out.print(" ");
		}
		for (int i = 0; i < txt.length; i++) {
			System.out.print(" " + txt[i]);
		}
		System.out.println();
		System.out.println(transition);
	}

	// TRANSITION FUNCTION
	
	public int[][] transition(char[] pattern) {
		int[][] transitionMatrix = new int[pattern.length + 1][256]; // There is empty string for FSA 
																	 // so, pattern.length + 1
		char[] alphabet = new char[26];
		
		// ALPHABET
		
		int i = 0;
		for (char ch = 'a'; ch <= 'z'; ch++) {
			alphabet[i] = ch;
			i++;
		}
		
		// TRANSITION MATRIX
		
		int m = pattern.length;
		for (int q = 0; q <= m; q++) {
			// Avoiding index complications
			if (q == m) {
				for (int a : alphabet) {
					int k = Math.min(m + 1, q + 2);
					do {
						k--;
					} while (k > 0 && !(pattern[q - 1] + "" + (char) a).endsWith(pattern[k - 1] + "")); // [q-1]
					transitionMatrix[m][a] = k;
				}
				break;
			}
			for (int a : alphabet) { // 'abab' pattern
				int k = Math.min(m + 1, q + 2);
				do {
					k--;
				} while (k > 0 && !(pattern[q] + "" + (char) a).endsWith(pattern[k - 1] + "")); // Until Pqa contains Pk
				transitionMatrix[q][a] = k;
			}
		}
		
		// PRINTING TRANSITION MATRIX
		
		System.out.println();
		System.out.println("The transition matrix as follows:");
		System.out.print(" ");
		for (char ch : alphabet) {
			System.out.print(" " + ch);
		}
		System.out.println();
		String[] matrix = new String[pattern.length];
		for (int a = 0; a < matrix.length; a++) {
			matrix[a] = pattern[a] + "";
		}
		for (int a = 0; a < matrix.length; a++) {
			for (int j = 97; j < 97 + alphabet.length; j++) {
				matrix[a] += " " + transitionMatrix[a][j];
			}
		}
		for (int a = 0; a < matrix.length; a++) {
			System.out.println(matrix[a]);
		}
		return transitionMatrix;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////II
    public int[][] computeTransitionFunction(String pattern, String text) {

        ArrayList<Character> languageOfString = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            if (!languageOfString.contains(text.charAt(i))) {
                languageOfString.add(text.charAt(i));
            }
        }

        int m = pattern.length();
        int[][] matrix = new int[m+1][languageOfString.size()];

        for (int q = 0; q <= m; q++) {
            for (Character a : languageOfString) {
                int k = Math.min(m , q + 1);

                while (k > 0) {
                    String pk = pattern.substring(0, k);
                    String pqa = pattern.substring(0, q) + a;
                    if (pqa.endsWith(pk)) {
                        matrix[q][languageOfString.indexOf(a)] = k;
                        k--;
                        break;
                    }
                    k--;
                    matrix[q][languageOfString.indexOf(a)] = k;
                }
            }
        }
        return matrix;
    }

    public void finiteAutomato(String text, String pattern){//matcher
        int[][] transition = computeTransitionFunction(pattern, text);
        int counter = 0;

        ArrayList<Character> languageOfString = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            if (!languageOfString.contains(text.charAt(i))) {
                languageOfString.add(text.charAt(i));
            }
        }

        int n = text.length();
        int q = 0;

        transitionMatrix(transition, languageOfString);

        System.out.println();

        for (int i = 0; i < n; i++){
            q = transition[q][languageOfString.indexOf(text.charAt(i))];
            if (q == pattern.length()){
                counter ++;
                System.out.println(counter + ". pattern occurence detected, shift position " + (i - (pattern.length() - 1)));
            }
        }
    }

    public void transitionMatrix(int [][]matrix, ArrayList<Character> languageOfString){//printing
        System.out.print("  ");
        for (Character character : languageOfString){
            System.out.print("  " + character);
        }

        System.out.println();
        for (int i = 0; i < matrix.length; i++){
            System.out.print("q" + i);
            for (int j = 0; j < matrix[0].length; j++){
                System.out.print(" q" + matrix[i][j]);
            }
            System.out.println();
        }

    }
	public static void main(String[] args) {
		Project4Cmpe232 test = new Project4Cmpe232();
		String str = "one of the earliest systems of writing, was invented by thhe sumerians";
		String pattern = "the";
		System.out.println("Text:");
		System.out.println(str);
		System.out.println("Pattern:");
		System.out.println(pattern);
		test.finiteAutomaton(str.toCharArray(), pattern.toCharArray());
		
		
		System.out.println("\n");
		
		Project4Cmpe232 FSA = new Project4Cmpe232();


        String str1 = "one of the earliest systems of writing, was invented by the sumerians";
        String pattern1 = "the";

        FSA.finiteAutomato(str1, pattern1);
	}
}

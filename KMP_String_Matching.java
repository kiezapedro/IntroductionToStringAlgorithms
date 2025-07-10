import java.util.Scanner;

class KMP_String_Matching {

    // Method to perform KMP search
    void KMPSearch(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        // Create lps[] that will hold the longest prefix suffix values for the pattern
        int[] lps = new int[M];
        int j = 0; // Index for pat[]

        // Preprocess the pattern (calculate lps[] array)
        computeLPSArray(pat, M, lps);

        int i = 0; // Index for txt[]
        while ((N - i) >= (M - j)) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                System.out.println("Found pattern at index: " + (i - j));
                j = lps[j - 1];
            }

            // Mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters, they will match anyway
                if (j != 0)
                    j = i + 1;
                else
                    i = i + 1;
            }
        }
    }

    // Method to calculate LPS (Longest Prefix Suffix) array
    void computeLPSArray(String pat, int M, int[] lps) {
        int len = 0; // Length of the previous longest prefix suffix
        int i = 1;
        lps[0] = 0; // lps[0] is always 0

        // The loop calculates lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                // If mismatch occurs, update len using the lps array
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        // Create a scanner object to read input from the user
        Scanner sc = new Scanner(System.in);

        // Prompt user for the main text and the pattern
        System.out.print("Enter the main text to search in: ");
        String txt = sc.nextLine();

        System.out.print("Enter the pattern to search for: ");
        String pat = sc.nextLine();

        // Call the KMPSearrch method to search for the pattern in the text
        new KMP_String_Matching().KMPSearch(pat, txt);
    }
}
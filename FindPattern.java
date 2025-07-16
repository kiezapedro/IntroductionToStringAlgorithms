import java.util.Scanner;

class FindPattern {

    // Method for pattern search
    int PatternSearch(String P, String T) {
        int M = P.length();
        int N = T.length();

        int[] lps = new int[M];
        int j = 0; // index for P[]
        int count = 0; // count of pattern matches

        // Calculate lps[] array
        computeLPSArray(P, M, lps);

        int i = 0; // index for T[]
        while ((N - i) >= (M - j)) {
            if (P.charAt(j) == T.charAt(i)) {
                j++;
                i++;
            }

            if (j == M) {
                count++;
                j = lps[j - 1]; // if pattern is found, check for next possible match
            } else if (i < N && P.charAt(j) != T.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }

        return count;
    }

    // Function to build array
    void computeLPSArray(String P, int M, int[] lps) {
        int len = 0;
        lps[0] = 0; // lps[0] is always 0
        int i = 1;

        while (i < M) {
            if (P.charAt(i) == P.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        // Create a scanner object to read input from the user
        Scanner sc = new Scanner(System.in);

        // Read pattern P and text T from input
        String P = sc.nextLine();
        String T = sc.nextLine();

        //Call method and display count
        int result = new FindPattern().PatternSearch(P, T);
        System.out.println(result);
    }
}

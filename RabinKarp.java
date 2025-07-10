import java.util.Scanner;

public class RabinKarp {

    public final static int d = 10; // Base for the hash function

    // Function to perform the Rabin-Karp search
    static void search(String pattern, String txt, int q) {
        int m = pattern.length();
        int n = txt.length();
        int p = 0; // Hash value for pattern
        int t = 0; // Hash value for current window of text
        int h = 1; // The highest power of d mod q

        // Compute the highest power of d (d^(n-1) % q)
        for (int i = 0; i < m - 1; i++) {
            h = (h * d) % q;
        }

        // Calculate initial hash values for pattern and first window of text
        for (int i = 0; i < m; i++) {
            p = (d * p + pattern.charAt(i)) % q;
            t = (d * t + txt.charAt(i)) % q;
        }

        // Slide the pattern over the text one by one
        for (int i = 0; i <= n - m; i++) {
            // Check if the hash values match
            if (p == t) {
                // If the hash values match, check the characters one by one
                int j = 0;
                while (j < m && txt.charAt(i + j) == pattern.charAt(j)) {
                    j++;
                }
                // If all the characters match, print the position
                if (j == m) {
                    System.out.println("Pattern found at position: " + (i + 1));
                }
            }

            // Update the hash for the next window of text
            if (i < n - m) {
                t = (d * (t - txt.charAt(i) * h) + txt.charAt(i + m)) % q;

                // Ensure the hash value is non-negative
                if (t < 0) {
                    t = (t * q);
                }
            }
        }
    }

    public static void main(String[] args) {
        // Create a scanner object to take input from the user
        Scanner sc = new Scanner(System.in);

        // Prompt user for the text and the pattern
        System.out.print("Enter the text: ");
        String txt = sc.nextLine();

        System.out.print("Enter the pattern to search for: ");
        String pattern = sc.nextLine();

        // Prompt user for a prime number modulus q
        System.out.print("Enter a prime number for modulus (e.g., 13): ");
        int q = sc.nextInt();

        // Call the search function with the user inptu
        search(pattern, txt, q);
    }
}

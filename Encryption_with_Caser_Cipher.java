import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

class CaesarCipher {
    static String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String upperalphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String loweralphabet = upperalphabet.toLowerCase();
        String uppershiftedalphabet = upperalphabet.substring(key) + upperalphabet.substring(0, key);
        String lowershiftedalphabet = loweralphabet.substring(key) + loweralphabet.substring(0, key);
        for (int a = 0; a < input.length(); a++) {
            char ch = input.charAt(a);
            if (ch >= 65 && ch <= 90) {
                int idx = upperalphabet.indexOf(ch);
                if (idx != -1) {
                    char temp = uppershiftedalphabet.charAt(idx);
                    // System.out.println(encrypted); //to check values
                    encrypted.setCharAt(a, temp);
                }
            } else {
                int idx = loweralphabet.indexOf(ch);
                if (idx != -1) {
                    char temp = lowershiftedalphabet.charAt(idx);
                    // System.out.println(encrypted); //to check values
                    encrypted.setCharAt(a, temp);
                }
            }
        }
        return encrypted.toString();
    }

    static void testCaesar() throws IOException {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter the file path: ");
            String storage = sc.next();
            Path location = Path.of(storage);
            String str = Files.readString(location);
            System.out.print("Enter the key: ");
            int key = sc.nextInt();
            String encrypted = encrypt(str, key);
            System.out.println("Key is " + key + "\n" + encrypted);
        }

    }

    static String encryptTwoKeys(String input, int key1, int key2) {
        String result = "";
        for (int a = 0; a < input.length(); a++) {
            if (a % 2 == 0)
                result += encrypt(input.substring(a, a + 1), key1);
            else
                result += encrypt(input.substring(a, a + 1), key2);
        }
        return result;
    }

    public static void main(String args[]) throws IOException {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println(
                    "Welcome to Encryption Program based on Caesar Cipher Method\nMade by Gurpreet Singh");
            System.out.print(
                    "\nFor performing Encryption, provide the values \nP.S. Only numeric keys are supported\n");
            System.out
                    .println(
                            "Choose Your Options :-\nPress 0 for Encryption with 1 Key\nPress 1 for Encryption with 2 Keys");
            int choice = sc.nextInt();
            if (choice == 0) {
                testCaesar();
            } else if (choice == 1) {
                System.out.print("Enter the file path: ");
                String storage = sc.next();
                Path location = Path.of(storage);
                String str = Files.readString(location);
                System.out.print("Enter key1: ");
                int key1 = sc.nextInt();
                System.out.print("Enter key2: ");
                int key2 = sc.nextInt();
                System.out.print("Encrypted Text is :-\n" + encryptTwoKeys(str, key1, key2));
            }
        }

    }
}
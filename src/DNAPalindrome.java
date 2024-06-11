import java.util.Scanner;

public class DNAPalindrome {
    public static void main(String[] args) {
        // New list instantiation
        StringDoubleEndedQueueImpl<Character> list = new StringDoubleEndedQueueImpl<Character>();

        // Ask user for input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter sequence: ");
        String dna_sequence = scanner.nextLine().toUpperCase();

        // Each character of the sequence will be complemented
        // Split the list in two halves and compare the first char with the last, second with second to last, etc.
        boolean isDNA = true;
        boolean flag = false;

        // Read String and insert characters to list
        for (int i = 0; i <= dna_sequence.length() - 1; i++) {
            char c = dna_sequence.charAt(i);
            list.addLast(c);
        }

        // Compare first with last, second with second to last, etc.
        for (int i = 0; i <= dna_sequence.length() / 2 - 1; i++) {
            char c1 = list.getFirst();
            char c2 = list.getLast();
            switch(c1) {
                case 'A':
                    c1 = 'T';
                    break;
                case 'T':
                    c1 = 'A';
                    break;
                case 'C':
                    c1 = 'G';
                    break;
                case 'G':
                    c1 = 'C';
                    break;
                default:
                    isDNA = false;
            }

            // If characters are not symmetrical, it's not Watson-Crick complemented palindrome
            if (!String.valueOf(c1).equals(String.valueOf(c2))) {
                flag = true;
                break;
            }

            // Remove first and last item from the list to continue checking
            list.removeFirst();
            list.removeLast();
        }

        // If the input string does not comply with regulations, terminate the program
        if (isDNA == false) System.out.println("Invalid input");
        else {
            if (flag) System.out.println("Not Watson-Crick complemented palindrome");
            else System.out.println("Watson-Crick complemented palindrome");
        }
    }
}

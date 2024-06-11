import java.util.Scanner;

public class PrefixToInfix {
    // Check if what is being read is an operator or operand
    public static boolean isOperator(char c) {
        if (c == '+' || c == '-' || c == '*' || c == '/') {
            return true;
        }
        return false;
    }

    // Check if number is a single digit integer
    public static boolean isDigit(char c) {
        return (c >= '0' && c <= '9');
    }

    // Convert prefix to infix
    public static String convert(String str) {
        // In this case the list takes String inputs
        StringDoubleEndedQueueImpl<String> list = new StringDoubleEndedQueueImpl<>();

        // Take the length of the given string to process each character
        int length = str.length();

        // Read the string in reverse
        for (int i = length - 1; i >= 0; i--) {
            char c = str.charAt(i);

            // If character is not an operator, add the operand to the list
            if (!isOperator(c)) {
                list.addLast(c + "");
            } else {
                // If character is an operator, remove the two last digits from the list and print infix version
                String operand1 = list.removeLast();
                String operand2 = list.removeLast();

                // String builder for infix version
                String str_builder = "(" + operand1 + c + operand2 + ")";
                list.addLast(str_builder);
            }
        }
        // Return the list
        return list.removeLast();
    }

    public static void main(String[] args) {
        // User inputs the prefix form and converts it to infix form
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter expression in prefix form: ");
        String str = scanner.nextLine();
        
        int num_amount = 0;
        int op_amount = 0;
        int length = str.length();
        for (int i = 0; i < length ; i++) {
            char c = str.charAt(i);
            // If the character is a digit, increment the digit sum variable
            if (isDigit(c)) {
                num_amount++;
            // If the character is an operator, increment the operator sum variable
            } else if (isOperator(c)) {
                op_amount++;
            }
            // Checks if first character is not an operator or none of the accepted input characters, in which case, an error is thrown
            if ((!isOperator(c) && i == 0 || (!Character.isDigit(str.charAt(i)) && !isOperator(c))) ) {
                System.out.println("Wrong prefix form");
                break;
            }   
        }

        // SET OF RULES TO BE FOLLOWED FOR INPUT
        // If num_amount == op_amount + 1 check if last two characters are digits
        // Case 1: Last two digits are numbers: num_amount - 2, then check if op_amount > num_amount. If so, proceed with conversion
        // Case 2: Throw error and terminate the program.
        if (num_amount == op_amount + 1) {
            int iterator2 = 0;
            for (int i = 0; length-2+i < length ; i++) {
                char c = str.charAt(length-2+i);
                if (isDigit(c)) iterator2++;
            }
            if (iterator2 == 2) {
                num_amount = num_amount - 2;
                if (op_amount > num_amount) System.out.println("Infix version: " + convert(str));
            } 
        } else System.out.println("Wrong prefix form");
    }
}
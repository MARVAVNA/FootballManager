package custom;

public class CustomNumber {
    public static boolean isInteger(String number) {
        try {
            Integer.parseInt(number);

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

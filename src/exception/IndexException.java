package exception;

public class IndexException extends Exception {
    private final int INDEX;

    public IndexException(int index) {
        INDEX = index;
    }

    @Override
    public String toString() {
        return "Invalid index: " + INDEX;
    }
}

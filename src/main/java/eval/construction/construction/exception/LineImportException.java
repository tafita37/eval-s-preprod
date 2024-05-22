package eval.construction.construction.exception;

public class LineImportException extends Exception {
    int lineNumber;

    public LineImportException(String arg0, int lineNumber) {
        super("Error ("+arg0+") in line "+lineNumber);
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
}

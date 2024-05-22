package eval.construction.construction.exception;

public class ColumnImportException extends Exception {
    int colonneNumber;

    public ColumnImportException(String arg0, int colonneNumber) {
        super("colonne number "+(colonneNumber+1)+" : "+arg0);
        this.colonneNumber = colonneNumber;
    }

    public int getColonneNumber() {
        return colonneNumber;
    }

    public void setColonneNumber(int colonneNumber) {
        this.colonneNumber = colonneNumber;
    }
}

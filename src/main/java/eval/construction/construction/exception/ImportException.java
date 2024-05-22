package eval.construction.construction.exception;

import java.util.ArrayList;

public class ImportException extends Exception {
    String fileName;
    ArrayList<LineImportException> listeLineException=new ArrayList<>();

    public ArrayList<LineImportException> getListeLineException() {
        return listeLineException;
    }

    public void setListeLineException(ArrayList<LineImportException> listeLineException) {
        this.listeLineException = listeLineException;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

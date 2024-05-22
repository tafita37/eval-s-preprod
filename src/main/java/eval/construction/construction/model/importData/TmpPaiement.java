package eval.construction.construction.model.importData;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import eval.construction.construction.Constante;
import eval.construction.construction.exception.ColumnImportException;
import eval.construction.construction.exception.ImportException;
import eval.construction.construction.exception.LineImportException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tmp_paiement")
public class TmpPaiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tmp_paiement")
    int idTmpPaiement;
    @Column(name = "ref_devis")
    String refDevis;
    @Column(name = "ref_paiement")
    String refPaiement;
    @Column(name = "date_paiement")
    Date datePaiement;
    @Column(name = "montant")
    double montant;
    public TmpPaiement() {
    }
    public int getIdTmpPaiement() {
        return idTmpPaiement;
    }
    public void setIdTmpPaiement(int idTmpPaiement) {
        this.idTmpPaiement = idTmpPaiement;
    }
    public String getRefDevis() {
        return refDevis;
    }
    public void setRefDevis(String refDevis)
    throws Exception {
        refDevis=refDevis.trim();
        if(refDevis==null||refDevis.length()==0) {
            throw new Exception("Entrer une reference de devis");
        }
        this.refDevis = refDevis;
    }

    public void setRefDevis(String refDevis, int numColonne)
    throws ColumnImportException {
        try {
            this.setRefDevis(refDevis);
        } catch (Exception e) {
            throw new ColumnImportException(e.getMessage(), numColonne);
        }
    }

    public String getRefPaiement() {
        return refPaiement;
    }
    public void setRefPaiement(String refPaiement)
    throws Exception {
        refPaiement=refPaiement.trim();
        if(refPaiement==null||refPaiement.length()==0) {
            throw new Exception("Entrer une reference de paiement");
        }
        this.refPaiement = refPaiement;
    }

    public void setRefPaiement(String refPaiement, int numColonne)
    throws ColumnImportException {
        try {
            this.setRefPaiement(refPaiement);
        } catch (Exception e) {
            throw new ColumnImportException(e.getMessage(), numColonne);
        }
    }

    public Date getDatePaiement() {
        return datePaiement;
    }
    public void setDatePaiement(Date datePaiement)
    throws Exception {
        if(datePaiement==null) {
            throw new Exception("Entrer une date de paiement");
        }
        this.datePaiement = datePaiement;
    }

    public void setDatePaiement(String datePaiement, int numColonne)
    throws ColumnImportException {
        try {
            datePaiement=datePaiement.trim();
            this.setDatePaiement(Constante.getDateFormatOfFrenchDate(datePaiement));
        } catch (Exception e) {
            throw new ColumnImportException(e.getMessage(), numColonne);
        }
    }

    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant)
    throws Exception {
        if(montant<0) {
            throw new Exception("Quantite trop petite");
        }
        this.montant = montant;
    }

    public void setMontant(String montant, int numColonne)
    throws ColumnImportException {
        String exceptionMessage="";
        try {
            montant=montant.trim();
            this.setMontant(Double.valueOf(montant.replace(",", ".")));
        } catch (NumberFormatException e) {
            exceptionMessage+=e.getMessage();
            e.printStackTrace();
        } catch (Exception e) {
            exceptionMessage+=e.getMessage();
            e.printStackTrace();
        }
        if(exceptionMessage.length()!=0) {
            throw new ColumnImportException(exceptionMessage, numColonne);
        }
    }

    public TmpPaiement(String[] lineCsv, int lineNumber) throws LineImportException {
        String exceptionMessage="";

        try {
            this.setRefDevis(lineCsv[0], 0);
        } catch (ColumnImportException e) {
            exceptionMessage+=e.getMessage();
        }

        try {
            this.setRefPaiement(lineCsv[1], 1);
        } catch (ColumnImportException e) {
            exceptionMessage+=e.getMessage();
        }

        try {
            this.setDatePaiement(lineCsv[2], 2);
        } catch (ColumnImportException e) {
            exceptionMessage+=e.getMessage();
        }
        
        try {
            this.setMontant(lineCsv[3], 3);
        } catch (ColumnImportException e) {
            exceptionMessage+=e.getMessage();
        }
        
        if(exceptionMessage.length()!=0) {
            throw new LineImportException(exceptionMessage, lineNumber);
        }
    }

    public static int countNotNull(TmpPaiement[] listePaiement) {
        int result=0;
        for(int i=0; i<listePaiement.length; i++) {
            if(listePaiement!=null) {
                result++;
            }
        }
        return result;
    }

    public static TmpPaiement[] getListeImportCSV(List<String[]> lineListe) throws ImportException {
        HashMap<String, Boolean> refPaiementExist=new HashMap<>();
        TmpPaiement[] result=new TmpPaiement[lineListe.size()];
        ImportException excelException=new ImportException();
        for(int i=0; i<lineListe.size(); i++) {
            try {
                result[i]=new TmpPaiement(lineListe.get(i), i+1);
                if(!refPaiementExist.containsKey(result[i].getRefPaiement())) {
                    refPaiementExist.put(result[i].getRefPaiement(), true);
                } else {
                    result[i]=null;
                }
            } catch (LineImportException e) {
                excelException.getListeLineException().add(e);
            }
        }
        // TmpPaiement[] realResult=new TmpPaiement[countNotNull(result)];
        // int k=0;
        // for(int i=0; i<result.length; i++) {
        //     if(result[i]!=null) {
        //         realResult[k]=result[i];
        //         System.out.println(realResult[k]);
        //         k++;
        //     }
        // }
        if(excelException.getListeLineException().size()!=0) {
            excelException.setFileName("paiement.csv");
            throw excelException;
        }
        return result;
    }
}

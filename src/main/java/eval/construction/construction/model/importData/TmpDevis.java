package eval.construction.construction.model.importData;

import java.sql.Date;
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
@Table(name = "tmp_devis")
public class TmpDevis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tmp_devis")
    int idTmpDevis;
    @Column(name = "client")
    String client;
    @Column(name = "ref_devis")
    String refDevis;
    @Column(name = "type_maison")
    String typeMaison;
    @Column(name = "finition")
    String finition;
    @Column(name = "taux_finition")
    double tauxFinition;
    @Column(name = "date_devis")
    Date dateDevis;
    @Column(name = "date_debut")
    Date dateDebut;
    @Column(name = "lieu")
    String lieu;
    public TmpDevis() {
    }
    public int getIdTmpDevis() {
        return idTmpDevis;
    }
    public void setIdTmpDevis(int idTmpDevis) {
        this.idTmpDevis = idTmpDevis;
    }
    public String getClient() {
        return client;
    }
    public void setClient(String client)
    throws Exception {
        client=client.trim();
        if(client==null||client.length()==0) {
            throw new Exception("Entrer un numero de client");
        }
        this.client = client;
    }
    public void setClient(String client, int colonneNumber) throws ColumnImportException {
        try {
            this.setClient(client);
        } catch (Exception e) {
            throw new ColumnImportException(e.getMessage(), colonneNumber);
        }
    }
    public String getRefDevis() {
        return refDevis;
    }
    public void setRefDevis(String refDevis) throws Exception {
        refDevis=refDevis.trim();
        if(refDevis==null||refDevis.length()==0) {
            throw new Exception("Entrer une reference de devis");
        }
        this.refDevis = refDevis;
    }
    public void setRefDevis(String refDevis, int colonneNumber) throws ColumnImportException {
        try {
            this.setRefDevis(refDevis);
        } catch (Exception e) {
            throw new ColumnImportException(e.getMessage(), colonneNumber);
        }
    }
    public String getTypeMaison() {
        return typeMaison;
    }
    public void setTypeMaison(String typeMaison)
    throws Exception {
        typeMaison=typeMaison.trim();
        if(typeMaison==null||typeMaison.length()==0) {
            throw new Exception("Entrer un type de maison");
        }
        this.typeMaison = typeMaison;
    }
    public void setTypeMaison(String typeMaison, int colonneNumber) throws ColumnImportException {
        try {
            this.setTypeMaison(typeMaison);
        } catch (Exception e) {
            throw new ColumnImportException(e.getMessage(), colonneNumber);
        }
    }
    public String getFinition() {
        return finition;
    }
    public void setFinition(String finition)
    throws Exception {
        finition=finition.trim();
        if(finition==null||finition.length()==0) {
            throw new Exception("Entrer un type de finition");
        }
        this.finition = finition;
    }

    
    public void setFinition(String finition, int colonneNumber) throws ColumnImportException {
        try {
            this.setFinition(finition);
        } catch (Exception e) {
            throw new ColumnImportException(e.getMessage(), colonneNumber);
        }
    }
    public double getTauxFinition() {
        return tauxFinition;
    }
    public void setTauxFinition(double tauxFinition)
    throws Exception {
        if(tauxFinition<0) {
            throw new Exception("Entrer un taux de finition plus grand");
        }
        this.tauxFinition = tauxFinition;
    }

    public void setTauxFinition(String tauxFinition, int colonneNumber)
    throws ColumnImportException {
        String exceptionMessage="";
        try {
            tauxFinition=tauxFinition.trim();
            tauxFinition=tauxFinition.replace("%", "").replace(",", ".");
            this.setTauxFinition(Double.valueOf(tauxFinition));
        } catch (NumberFormatException e) {
            exceptionMessage+=e.getMessage();
        } catch (Exception e) {
            exceptionMessage+=e.getMessage();
        }
        if(exceptionMessage.length()!=0) {
            throw new ColumnImportException(exceptionMessage, colonneNumber);
        }
    }

    public Date getDateDevis() {
        return dateDevis;
    }
    public void setDateDevis(Date dateDevis)
    throws Exception {
        if(dateDevis==null) {
            throw new Exception("Entrer une date de devis");
        }
        this.dateDevis = dateDevis;
    }

    public void setDateDevis(String dateDevis, int colonneNumber) throws ColumnImportException {
        try {
            dateDevis=dateDevis.trim();
            this.setDateDevis(Constante.getDateFormatOfFrenchDate(dateDevis));
        } catch (Exception e) {
            throw new ColumnImportException(e.getMessage(), colonneNumber);
        }
    }
    public Date getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(Date dateDebut)
    throws Exception {
        if(dateDebut==null) {
            throw new Exception("Entrer une date de debut");
        }
        this.dateDebut = dateDebut;
    }

    public void setDateDebut(String dateDebut, int colonneNumber) throws ColumnImportException {
        try {
            dateDebut=dateDebut.trim();
            this.setDateDebut(Constante.getDateFormatOfFrenchDate(dateDebut));
        } catch (Exception e) {
            throw new ColumnImportException(e.getMessage(), colonneNumber);
        }
    }
    public String getLieu() {
        return lieu;
    }
    public void setLieu(String lieu)
    throws Exception {
        lieu=lieu.trim();
        if(lieu==null||lieu.length()==0) {
            throw new Exception("Veuillez entrer un lieu");
        }
        this.lieu = lieu;
    }

    public void setLieu(String lieu, int colonneNumber)
    throws ColumnImportException {
        try {
            this.setLieu(lieu);
        } catch (Exception e) {
            throw new ColumnImportException(e.getMessage(), colonneNumber);
        }
    }

    public TmpDevis(String[] lineCsv, int lineNumber) throws LineImportException {
        String exceptionMessage="";

        try {
            this.setClient(lineCsv[0], 0);
        } catch (ColumnImportException e) {
            exceptionMessage+=e.getMessage();
        }

        try {
            this.setRefDevis(lineCsv[1], 1);
        } catch (ColumnImportException e) {
            exceptionMessage+=e.getMessage();
        }

        try {
            this.setTypeMaison(lineCsv[2], 2);
        } catch (ColumnImportException e) {
            exceptionMessage+=e.getMessage();
        }
        
        try {
            this.setFinition(lineCsv[3], 3);
        } catch (ColumnImportException e) {
            exceptionMessage+=e.getMessage();
        }

        try {
            this.setTauxFinition(lineCsv[4], 4);
        } catch (ColumnImportException e) {
            exceptionMessage+=e.getMessage();
        }

        try {
            this.setDateDevis(lineCsv[5], 5);
        } catch (ColumnImportException e) {
            exceptionMessage+=e.getMessage();
        }

        try {
            this.setDateDebut(lineCsv[6], 6);
        } catch (ColumnImportException e) {
            exceptionMessage+=e.getMessage();
        }

        try {
            this.setLieu(lineCsv[7], 7);
        } catch (ColumnImportException e) {
            exceptionMessage+=e.getMessage();
        }
        
        if(exceptionMessage.length()!=0) {
            throw new LineImportException(exceptionMessage, lineNumber);
        }
    }

    public static TmpDevis[] getListeImportCSV(List<String[]> lineListe) throws ImportException {
        TmpDevis[] result=new TmpDevis[lineListe.size()];
        ImportException excelException=new ImportException();
        for(int i=0; i<lineListe.size(); i++) {
            try {
                result[i]=new TmpDevis(lineListe.get(i), i+1);
            } catch (LineImportException e) {
                excelException.getListeLineException().add(e);
            }
        }
        if(excelException.getListeLineException().size()!=0) {
            excelException.setFileName("devis.csv");
            throw excelException;
        }
        return result;
    }
}

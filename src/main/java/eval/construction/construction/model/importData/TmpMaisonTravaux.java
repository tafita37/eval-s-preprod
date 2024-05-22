package eval.construction.construction.model.importData;

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
@Table(name = "tmp_maison_travaux")
public class TmpMaisonTravaux {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tmp_maison_travaux")
    int idTmpMaisonTravaux;
    @Column(name = "type_maison")
    String typeMaison;
    @Column(name = "description")
    String description;
    @Column(name = "surface")
    double surface;
    @Column(name = "code_travaux")
    String codeTravaux;
    @Column(name = "type_travaux")
    String typeTravaux;
    @Column(name = "unite")
    String unite;
    @Column(name = "prix_unitaire")
    double prixUnitaire;
    @Column(name = "quantite")
    double quantite;
    @Column(name = "duree_travaux")
    double dureeTravaux;
    public TmpMaisonTravaux() {
    }
    public int getIdTmpMaisonTravaux() {
        return idTmpMaisonTravaux;
    }
    public void setIdTmpMaisonTravaux(int idTmpMaisonTravaux) {
        this.idTmpMaisonTravaux = idTmpMaisonTravaux;
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
    public void setTypeMaison(String typeMaison, int colonneNumber)
    throws ColumnImportException {
        try {
            this.setTypeMaison(typeMaison);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ColumnImportException(e.getMessage(), colonneNumber);
        }
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description)
    throws Exception {
        description=description.trim();
        if(description==null||description.length()==0) {
            throw new Exception("Entrer une description");
        }
        this.description = description;
    }
    public void setDescription(String description, int colonneNumber) 
    throws ColumnImportException {
        try {
            this.setDescription(description);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ColumnImportException(e.getMessage(), colonneNumber);
        }
    }
    public double getSurface() {
        return surface;
    }
    public void setSurface(double surface)
    throws Exception {
        if(surface<=0) {
            throw new Exception("Surface trop petite");
        }
        this.surface = surface;
    }
    public void setSurface(String surface, int colonneNumber) throws ColumnImportException {
        String exceptionMessage="";
        try {
            surface=surface.trim();
            surface=surface.replace(",", ".");
            this.setSurface(Double.valueOf(surface));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            exceptionMessage+=e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            exceptionMessage+=e.getMessage();
        }
        if(exceptionMessage.length()!=0) {
            throw new ColumnImportException(exceptionMessage, colonneNumber);
        }
    }
    public String getCodeTravaux() {
        return codeTravaux;
    }
    public void setCodeTravaux(String codeTravaux)
    throws Exception {
        codeTravaux=codeTravaux.trim();
        if(codeTravaux==null||codeTravaux.length()==0) {
            throw new Exception("Entrer une code de travaux");
        }
        this.codeTravaux = codeTravaux;
    }
    public void setCodeTravaux(String codeTravaux, int colonneNumber) throws ColumnImportException {
        try {
            this.setCodeTravaux(codeTravaux);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ColumnImportException(e.getMessage(), colonneNumber);
        }
    }
    public String getTypeTravaux() {
        return typeTravaux;
    }
    public void setTypeTravaux(String typeTravaux)
    throws Exception {
        typeTravaux=typeTravaux.trim();
        if(typeTravaux==null||typeTravaux.length()==0) {
            throw new Exception("Entrer une type de travaux");
        }
        this.typeTravaux = typeTravaux;
    }

    public void setTypeTravaux(String typeTravaux, int colonneNumber)
    throws ColumnImportException {
        try {
            this.setTypeTravaux(typeTravaux);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ColumnImportException(e.getMessage(), colonneNumber);
        }
    }
    public String getUnite() {
        return unite;
    }
    public void setUnite(String unite) throws Exception {
        unite=unite.trim();
        if(unite==null||unite.length()==0) {
            throw new Exception("Entrer une unite");
        }
        this.unite = unite;
    }

    public void setUnite(String unite, int colonneNumber) throws ColumnImportException {
        try {
            this.setUnite(unite);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ColumnImportException(e.getMessage(), colonneNumber);
        }
    }
    public double getPrixUnitaire() {
        return prixUnitaire;
    }
    public void setPrixUnitaire(double prixUnitaire)
    throws Exception {
        if(prixUnitaire<=0) {
            throw new Exception("Prix unitaire trop petit");
        }
        this.prixUnitaire = prixUnitaire;
    }

    public void setPrixUnitaire(String prixUnitaire, int colonneNumber)
    throws ColumnImportException  {
        String exceptionMessage="";
        try {
            prixUnitaire=prixUnitaire.trim();
            prixUnitaire=prixUnitaire.replace(",", ".");
            this.setPrixUnitaire(Double.valueOf(prixUnitaire));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            exceptionMessage+=e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            exceptionMessage+=e.getMessage();
        }
        if(exceptionMessage.length()!=0) {
            throw new ColumnImportException(exceptionMessage, colonneNumber);
        }
    }

    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite)
    throws Exception {
        if(quantite<=0) {
            throw new Exception("Quantite trop petite");
        }
        this.quantite = quantite;
    }

    public void setQuantite(String quantite, int colonneNumber)
    throws ColumnImportException  {
        String exceptionMessage="";
        try {
            quantite=quantite.trim();
            quantite=quantite.replace(",", ".");
            this.setQuantite(Double.valueOf(quantite));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            exceptionMessage+=e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            exceptionMessage+=e.getMessage();
        }
        if(exceptionMessage.length()!=0) {
            throw new ColumnImportException(exceptionMessage, colonneNumber);
        }
    }

    public double getDureeTravaux() {
        return dureeTravaux;
    }
    public void setDureeTravaux(double dureeTravaux)
    throws Exception {
        if(dureeTravaux<=0) {
            throw new Exception("Duree trop petite");
        }
        this.dureeTravaux = dureeTravaux;
    }

    public void setDureeTravaux(String dureeTravaux, int colonneNumber)
    throws ColumnImportException  {
        String exceptionMessage="";
        try {
            dureeTravaux=dureeTravaux.trim();
            dureeTravaux=dureeTravaux.replace(",", ".");
            this.setDureeTravaux(Double.valueOf(dureeTravaux));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            exceptionMessage+=e.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            exceptionMessage+=e.getMessage();
        }
        if(exceptionMessage.length()!=0) {
            throw new ColumnImportException(exceptionMessage, colonneNumber);
        }
    }

    public TmpMaisonTravaux(String[] lineCsv, int lineNumber) throws LineImportException {
        String exceptionMessage="";

        try {
            this.setTypeMaison(lineCsv[0], 0);
        } catch (ColumnImportException e) {
            exceptionMessage+=e.getMessage();
        }

        try {
            this.setDescription(lineCsv[1], 1);
        } catch (ColumnImportException e) {
            exceptionMessage+=e.getMessage();
        }

        try {
            this.setSurface(lineCsv[2], 2);
        } catch (ColumnImportException e) {
            exceptionMessage+=e.getMessage();
        }
        
        try {
            this.setCodeTravaux(lineCsv[3], 3);
        } catch (ColumnImportException e) {
            exceptionMessage+=e.getMessage();
        }

        try {
            this.setTypeTravaux(lineCsv[4], 4);
        } catch (ColumnImportException e) {
            exceptionMessage+=e.getMessage();
        }

        try {
            this.setUnite(lineCsv[5], 5);
        } catch (ColumnImportException e) {
            exceptionMessage+=e.getMessage();
        }

        try {
            this.setPrixUnitaire(lineCsv[6], 6);
        } catch (ColumnImportException e) {
            exceptionMessage+=e.getMessage();
        }

        try {
            this.setQuantite(lineCsv[7], 7);
        } catch (ColumnImportException e) {
            exceptionMessage+=e.getMessage();
        }

        try {
            this.setDureeTravaux(lineCsv[8], 8);
        } catch (ColumnImportException e) {
            exceptionMessage+=e.getMessage();
        }
        
        if(exceptionMessage.length()!=0) {
            throw new LineImportException(exceptionMessage, lineNumber);
        }
    }

    public static TmpMaisonTravaux[] getListeImportCSV(List<String[]> lineListe) throws ImportException {
        TmpMaisonTravaux[] result=new TmpMaisonTravaux[lineListe.size()];
        ImportException excelException=new ImportException();
        for(int i=0; i<lineListe.size(); i++) {
            try {
                result[i]=new TmpMaisonTravaux(lineListe.get(i), i+1);
            } catch (LineImportException e) {
                excelException.getListeLineException().add(e);
            }
        }
        if(excelException.getListeLineException().size()!=0) {
            excelException.setFileName("maison_travaux.csv");
            throw excelException;
        }
        return result;
    }
}

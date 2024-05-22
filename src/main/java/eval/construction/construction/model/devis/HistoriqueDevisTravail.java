package eval.construction.construction.model.devis;

import eval.construction.construction.model.construction.Travail;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "historique_devis_travail")
public class HistoriqueDevisTravail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historique_devis_travail")
    int idHistoriqueDevisTravail;
    @Column(name = "id_devis")
    int idDevis;
    @ManyToOne
    @JoinColumn(name = "id_travail", nullable = true)
    Travail travail;
    @Column(name = "quantite_travail")
    double quantiteTravail;
    @Column(name = "prix_unitaire_travail")
    double prixUnitaireTravail;
    public HistoriqueDevisTravail(int idDevis, Travail travail, double quantiteTravail)
    throws Exception {
        this.setIdDevis(idDevis);
        this.setTravail(travail);
        this.setQuantiteTravail(quantiteTravail);
        this.setPrixUnitaireTravail(this.getTravail().getPrixUnitaire());
    }
    public HistoriqueDevisTravail() {
    }
    public int getIdHistoriqueDevisTravail() {
        return idHistoriqueDevisTravail;
    }
    public void setIdHistoriqueDevisTravail(int idHistoriqueDevisTravail) {
        this.idHistoriqueDevisTravail = idHistoriqueDevisTravail;
    }
    public Travail getTravail() {
        return travail;
    }
    public void setTravail(Travail travail)
    throws Exception {
        if(travail==null) {
            throw new Exception("Veuillez entrer un travail");
        }
        this.travail = travail;
    }
    public double getQuantiteTravail() {
        return quantiteTravail;
    }
    public void setQuantiteTravail(double quantiteTravail)
    throws Exception {
        if(quantiteTravail<=0) {
            throw new Exception("Veuillez entrer une quantite plus grande");
        }
        this.quantiteTravail = quantiteTravail;
    }
    public double getPrixUnitaireTravail() {
        return prixUnitaireTravail;
    }
    public void setPrixUnitaireTravail(double prixUnitaireTravail)
    throws Exception {
        if(prixUnitaireTravail<=0) {
            throw new Exception("Veuillez entrer un prix unitaire plus grand");
        }
        this.prixUnitaireTravail = prixUnitaireTravail;
    }

    public double getPrixTotal() {
        return this.getPrixUnitaireTravail()*this.getQuantiteTravail();
    }
    public int getIdDevis() {
        return idDevis;
    }
    public void setIdDevis(int idDevis) {
        this.idDevis = idDevis;
    }

    public String stringValue() {
        return this.getTravail().getNomTravail()+
                                            " : "+
                        this.getQuantiteTravail()+
                                            " a "+
                    this.getPrixUnitaireTravail()+
                                                "ar /"+
        this.getTravail().getUnite().getNomUnite();
    }
}

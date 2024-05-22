package eval.construction.construction.model.devis;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "v_montant_p_mois_annee")
public class VMontantPMoisAnnee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "montant_devis")
    double montantDevis;
    @Column(name = "mois")
    int mois;
    @Column(name = "annee")
    int annee;
    public VMontantPMoisAnnee(double montantDevis, int mois, int annee) {
        this.montantDevis = montantDevis;
        this.mois = mois;
        this.annee = annee;
    }
    public VMontantPMoisAnnee() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getMontantDevis() {
        return montantDevis;
    }
    public void setMontantDevis(double montantDevis) {
        this.montantDevis = montantDevis;
    }
    public int getMois() {
        return mois;
    }
    public void setMois(int mois) {
        this.mois = mois;
    }
    public int getAnnee() {
        return annee;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }
}

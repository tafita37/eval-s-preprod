package eval.construction.construction.model.devis;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "paiement")
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paiement")
    int idPaiement;
    @ManyToOne
    @JoinColumn(name = "id_devis")
    Devis devis;
    @Column(name = "montant")
    double montant;
    @Column(name = "date_paiement")
    Date datePaiement;
    @Column(name = "ref_paiement")
    String refPaiement;
    public Paiement(Devis devis, double montant, Date datePaiement)
    throws Exception {
        this.setDevis(devis);
        this.setMontant(montant);
        this.setDatePaiement(datePaiement);
    }
    public Paiement() {
    }
    public int getIdPaiement() {
        return idPaiement;
    }
    public void setIdPaiement(int idPaiement) {
        this.idPaiement = idPaiement;
    }
    public Devis getDevis() {
        return devis;
    }
    public void setDevis(Devis devis)
    throws Exception {
        if(devis==null) {
            throw new Exception("Veuillez entrer un devis");
        }
        this.devis = devis;
    }
    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant)
    throws Exception {
        if(montant<=0) {
            throw new Exception("Veuillez entrer un montant de paiement plus grand");
        }
        this.montant = montant;
    }
    public Date getDatePaiement() {
        return datePaiement;
    }
    public void setDatePaiement(Date datePaiement)
    throws Exception {
        if(datePaiement==null) {
            throw new Exception("Veuillez entrer une date de paiement");
        }
        // if(datePaiement.before(this.getDevis().getDateDebut())) {
        //     throw new Exception("La date de paiement doit etre apres les debut de construction");
        // }
        this.datePaiement = datePaiement;
    }

    public String stringValue() {
        return this.getMontant()+"ar : \t"+this.getDatePaiement();
    }
    public String getRefPaiement() {
        return refPaiement;
    }
    public void setRefPaiement(String refPaiement) {
        this.refPaiement = refPaiement;
    }
}

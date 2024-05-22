package eval.construction.construction.model.construction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "travail")
public class Travail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_travail")
    int idTravail;
    @Column(name = "nom_travail")
    String nomTravail;
    @Column(name = "numero")
    String numero;
    @ManyToOne
    @JoinColumn(name = "id_unite")
    Unite unite;
    @Column(name = "prix_unitaire")
    double prixUnitaire;
    @Transient
    double quantite;
    @Transient
    double prixTotal;
    public Travail(int idTravail, String nomTravail, String numero, Unite unite, double prixUnitaire) {
        this.idTravail = idTravail;
        this.nomTravail = nomTravail;
        this.numero = numero;
        this.unite = unite;
        this.prixUnitaire = prixUnitaire;
    }
    public Travail() {
    }
    public int getIdTravail() {
        return idTravail;
    }
    public void setIdTravail(int idTravail) {
        this.idTravail = idTravail;
    }
    public String getNomTravail() {
        return nomTravail;
    }
    public void setNomTravail(String nomTravail)
    throws Exception {
        if(nomTravail==null||nomTravail.length()==0) {
            throw new Exception("Veuillez entrer un nom de travail");
        }
        this.nomTravail = nomTravail;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero)
    throws Exception {
        if(numero==null||numero.length()==0) {
            throw new Exception("Veuillez entrer un numero de travail");
        }
        this.numero = numero;
    }
    public double getPrixUnitaire() {
        return prixUnitaire;
    }
    public void setPrixUnitaire(double prixUnitaire)
    throws Exception {
        if(prixUnitaire<0) {
            throw new Exception("Veuillez entrer un prix unitaire plus grand");
        }
        this.prixUnitaire = prixUnitaire;
    }
    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }
    public double getPrixTotal() {
        return prixTotal;
    }
    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }
    public Unite getUnite() {
        return unite;
    }
    public void setUnite(Unite unite) {
        this.unite = unite;
    }
}

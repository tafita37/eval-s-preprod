package eval.construction.construction.model.construction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "type_maison_travail")
public class TypeMaisonTravail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_maison_travail")
    int idTypeMaisonTravail;
    @ManyToOne
    @JoinColumn(name = "id_type_maison")
    TypeMaison typeMaison;
    @ManyToOne
    @JoinColumn(name = "id_travail")
    Travail travail;
    @Column(name = "quantite")
    double quantite;
    public TypeMaisonTravail() {
    }
    public int getIdTypeMaisonTravail() {
        return idTypeMaisonTravail;
    }
    public void setIdTypeMaisonTravail(int idTypeMaisonTravail) {
        this.idTypeMaisonTravail = idTypeMaisonTravail;
    }
    public TypeMaison getTypeMaison() {
        return typeMaison;
    }
    public void setTypeMaison(TypeMaison typeMaison)
    throws Exception {
        if(typeMaison==null) {
            throw new Exception("Veuillez entrer un type de maison");
        }
        this.typeMaison = typeMaison;
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
    public double getQuantite() {
        return quantite;
    }
    public void setQuantite(double quantite)
    throws Exception {
        if(quantite<=0) {
            throw new Exception("Veuillez entrer une quantite plus grande");
        }
        this.quantite = quantite;
    }
}

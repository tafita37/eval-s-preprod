package eval.construction.construction.model.construction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "type_maison")
public class TypeMaison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_maison")
    int idTypeMaison;
    @Column(name = "nom_type_maison")
    String nomTypeMaison;
    @Column(name = "duree_jour")
    double dureeJour;
    @Column(name = "description_type_maison")
    String descriptionTypeMaison;
    @Column(name = "surface")
    double surface;
    public TypeMaison() {
    }
    public int getIdTypeMaison() {
        return idTypeMaison;
    }
    public void setIdTypeMaison(int idTypeMaison) {
        this.idTypeMaison = idTypeMaison;
    }
    public String getNomTypeMaison() {
        return nomTypeMaison;
    }
    public void setNomTypeMaison(String nomTypeMaison)
    throws Exception {
        if(nomTypeMaison==null||nomTypeMaison.length()==0) {
            throw new Exception("Veuillez entrer un nom de type de maison");
        }
        this.nomTypeMaison = nomTypeMaison;
    }
    public double getDureeJour() {
        return dureeJour;
    }
    public void setDureeJour(double dureeJour)
    throws Exception {
        if(dureeJour<=0) {
            throw new Exception("Veuillez entrer une duree en jour plus grande");
        }
        this.dureeJour = dureeJour;
    }
    public String getDescriptionTypeMaison() {
        return descriptionTypeMaison;
    }
    public void setDescriptionTypeMaison(String descriptionTypeMaison) {
        this.descriptionTypeMaison = descriptionTypeMaison;
    }
    public double getSurface() {
        return surface;
    }
    public void setSurface(double surface) {
        this.surface = surface;
    }
}

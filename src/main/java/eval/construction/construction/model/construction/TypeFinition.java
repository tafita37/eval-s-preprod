package eval.construction.construction.model.construction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "type_finition")
public class TypeFinition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_type_finition")
    int idTypeFinition;
    @Column(name = "nom_type_finition")
    String nomTypeFinition;
    @Column(name = "pourcentage_augmentation")
    double pourcentageAugmentation;
    public TypeFinition(int idTypeFinition, double pourcentageAugmentation) {
        this.idTypeFinition = idTypeFinition;
        this.pourcentageAugmentation = pourcentageAugmentation;
    }
    public TypeFinition() {
    }
    public int getIdTypeFinition() {
        return idTypeFinition;
    }
    public void setIdTypeFinition(int idTypeFinition) {
        this.idTypeFinition = idTypeFinition;
    }
    public String getNomTypeFinition() {
        return nomTypeFinition;
    }
    public void setNomTypeFinition(String nomTypeFinition)
    throws Exception {
        if(nomTypeFinition==null||nomTypeFinition.length()==0) {
            throw new Exception("Veuillez entrer un nom de type de finition");
        }
        this.nomTypeFinition = nomTypeFinition;
    }
    public double getPourcentageAugmentation() {
        return pourcentageAugmentation;
    }
    public void setPourcentageAugmentation(double pourcentageAugmentation)
    throws Exception {
        if(pourcentageAugmentation<0||pourcentageAugmentation>100) {
            throw new Exception("Le pourcentage d'augmentation doit etre compris entre 0 et 100");
        }
        this.pourcentageAugmentation = pourcentageAugmentation;
    }
}

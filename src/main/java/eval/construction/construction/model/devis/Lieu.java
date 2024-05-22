package eval.construction.construction.model.devis;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lieu")
public class Lieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lieu")
    int idLieu;
    @Column(name = "nom_lieu")
    String nomLieu;
    public Lieu() {
    }
    public int getIdLieu() {
        return idLieu;
    }
    public void setIdLieu(int idLieu) {
        this.idLieu = idLieu;
    }
    public String getNomLieu() {
        return nomLieu;
    }
    public void setNomLieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }
}

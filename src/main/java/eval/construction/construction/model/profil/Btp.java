package eval.construction.construction.model.profil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "btp")
public class Btp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_btp")
    int idBtp;
    @Column(name = "email_btp")
    String emailBtp;
    @Column(name = "mdp_btp")
    String mdpBtp;
    public Btp() {
    }
    public int getIdBtp() {
        return idBtp;
    }
    public void setIdBtp(int idBtp) {
        this.idBtp = idBtp;
    }
    public String getEmailBtp() {
        return emailBtp;
    }
    public void setEmailBtp(String emailBtp)
    throws Exception {
        if(emailBtp==null||emailBtp.length()==0) {
            throw new Exception("Veuillez entrer un email de btp");
        }
        this.emailBtp = emailBtp;
    }
    public String getMdpBtp() {
        return mdpBtp;
    }
    public void setMdpBtp(String mdpBtp)
    throws Exception {
        if(mdpBtp==null||mdpBtp.length()==0) {
            throw new Exception("Veuillez entrer un mot de passe de btp");
        }
        this.mdpBtp = mdpBtp;
    }
}

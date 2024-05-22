package eval.construction.construction.model.profil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    int idClient;
    @Column(name = "numero_client")
    String numeroClient;
    public Client(String numeroClient)
    throws Exception {
        this.setNumeroClient(numeroClient);
    }
    public Client() {
    }
    public int getIdClient() {
        return idClient;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    public String getNumeroClient() {
        return numeroClient;
    }
    public void setNumeroClient(String numeroClient)
    throws Exception {
        if(numeroClient==null||numeroClient.length()==0) {
            throw new Exception("Veuillez entrer un numero de client");
        }
        this.numeroClient = numeroClient;
    }
}

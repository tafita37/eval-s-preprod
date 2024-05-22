package eval.construction.construction.model.devis;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import eval.construction.construction.model.construction.TypeFinition;
import eval.construction.construction.model.construction.TypeMaison;
import eval.construction.construction.model.profil.Client;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "devis")
public class Devis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_devis")
    int idDevis;
    @Column(name = "ref_devis")
    String refDevis;
    @ManyToOne
    @JoinColumn(name = "id_client")
    Client client;
    @ManyToOne
    @JoinColumn(name = "id_type_maison")
    TypeMaison typeMaison;
    @ManyToOne
    @JoinColumn(name = "id_type_finition")
    TypeFinition typeFinition;
    @Column(name = "date_debut")
    Date dateDebut;
    @Column(name = "date_devis")
    Date dateDevis;
    @ManyToOne
    @JoinColumn(name = "id_lieu")
    Lieu lieu;
    @Column(name = "prix_total", columnDefinition = "double precision")
    double prixTotal;
    @Column(name = "montant_payer")
    double montantPayer;
    @Column(name = "duree_type_maison")
    double dureeTypeMaison;
    @Column(name = "pourcentage_augmentation_finition")
    double pourcentageAugmentationFinition;
    @Column(name = "en_cours")
    int enCours;
    @OneToMany(mappedBy = "idDevis")
    List<HistoriqueDevisTravail> listeHistoriqueDevisTravail;
    @Transient
    List<Paiement> listePaiements;
    public Devis(Client client, TypeMaison typeMaison, TypeFinition typeFinition, Date dateDebut, double prixTotal)
    throws Exception {
        this.setClient(client);
        this.setTypeMaison(typeMaison);
        this.setTypeFinition(typeFinition);
        this.setDateDebut(dateDebut);
        this.setDateDevis(Date.valueOf(LocalDate.now()));
        this.setPrixTotal(prixTotal);
        this.setDureeTypeMaison(this.getTypeMaison().getDureeJour());
        this.setPourcentageAugmentationFinition(this.getTypeFinition().getPourcentageAugmentation());
        this.setEnCours(0);
        this.setMontantPayer(0);
    }

    public void afficherInfo() {
        System.out.println("Client : "+client.getNumeroClient());
        System.out.println("Type de maison : "+typeMaison.getNomTypeMaison());
        System.out.println("Type de finition : "+typeFinition.getNomTypeFinition());
        System.out.println("Date de debut : "+this.getDateDebut());
        System.out.println("Prix total : "+this.getPrixTotal());
        System.out.println("Duree type maison "+ this.getDureeTypeMaison());
        System.out.println("Pourcentage : "+this.getPourcentageAugmentationFinition());
        System.out.println("En cours "+this.getEnCours());
    }
    public Devis() {
    }
    public int getIdDevis() {
        return idDevis;
    }
    public void setIdDevis(int idDevis) {
        this.idDevis = idDevis;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client)
    throws Exception {
        if(client==null) {
            throw new Exception("Le devis doit appartenir a un client");
        }
        this.client = client;
    }
    public TypeMaison getTypeMaison() {
        return typeMaison;
    }
    public void setTypeMaison(TypeMaison typeMaison)
    throws Exception {
        if(typeMaison==null) {
            throw new Exception("Veuillez choisir un type de maison");
        }
        this.typeMaison = typeMaison;
    }
    public TypeFinition getTypeFinition() {
        return typeFinition;
    }
    public void setTypeFinition(TypeFinition typeFinition)
    throws Exception {
        if(typeFinition==null) {
            throw new Exception("Veuillez choisir un type de finition");
        }
        this.typeFinition = typeFinition;
    }
    public Date getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(Date dateDebut)
    throws Exception {
        if(dateDebut==null) {
            throw new Exception("Veuillez entrer la date de debut du devis");
        }
        this.dateDebut = dateDebut;
    }
    public double getPrixTotal() {
        return prixTotal;
    }
    public void setPrixTotal(double prixTotal)
    throws Exception {
        if(prixTotal<0) {
            throw new Exception("Veuillez entrer un prix total plus grand");
        }
        this.prixTotal = prixTotal;
    }
    public double getDureeTypeMaison() {
        return dureeTypeMaison;
    }
    public void setDureeTypeMaison(double dureeTypeMaison)
    throws Exception {
        if(dureeTypeMaison<0) {
            throw new Exception("Veuillez entrer une duree de construction de maison plus grande");
        }
        this.dureeTypeMaison = dureeTypeMaison;
    }
    public double getPourcentageAugmentationFinition() {
        return pourcentageAugmentationFinition;
    }
    public void setPourcentageAugmentationFinition(double pourcentageAugmentationFinition)
    throws Exception {
        if(pourcentageAugmentationFinition<0||pourcentageAugmentationFinition>100) {
            throw new Exception("Le pourcentage d'augmentation doit etre entre 1 et 100");
        }
        this.pourcentageAugmentationFinition = pourcentageAugmentationFinition;
    }
    public int getEnCours() {
        return enCours;
    }
    public void setEnCours(int enCours) {
        this.enCours = enCours;
    }

    public Date getDateFin() {
        LocalDate localDate = dateDebut.toLocalDate();
        
        // Ajouter le nombre de jours
        LocalDate nouvelleLocalDate = localDate.plusDays(Long.valueOf(String.valueOf( (int) this.getDureeTypeMaison())));
        
        // Convertir le LocalDate en Date
        Date nouvelleDate = Date.valueOf(nouvelleLocalDate);
        return nouvelleDate;
    }

    public double getFinalPrice() {
        System.out.println(this.getPrixTotal()*(1+(this.getPourcentageAugmentationFinition()/100)));
        return this.getPrixTotal()*(1+(this.getPourcentageAugmentationFinition()/100));
    }

    public double getMontantPayer() {
        return montantPayer;
    }

    public void setMontantPayer(double montantPayer) {
        this.montantPayer = montantPayer;
    }

    public double getResteAPayer() {
        return this.getFinalPrice()-this.getMontantPayer();
    }

    public double getPourcentagePayerDouble() {
        return (this.getMontantPayer()*100)/this.getFinalPrice();
    }

    // public String getPourcentagePayer() {
    //     return String.format("%.2f", (this.getMontantPayer()*100)/this.getFinalPrice())+"%";
    // }
    public String getPourcentagePayer() {
        return ((this.getMontantPayer()*100)/this.getFinalPrice())+"%";
    }

    public List<HistoriqueDevisTravail> getListeHistoriqueDevisTravail() {
        return listeHistoriqueDevisTravail;
    }

    public void setListeHistoriqueDevisTravail(List<HistoriqueDevisTravail> listeHistoriqueDevisTravail) {
        this.listeHistoriqueDevisTravail = listeHistoriqueDevisTravail;
    }

    public List<Paiement> getListePaiements() {
        return listePaiements;
    }

    public void setListePaiements(List<Paiement> listePaiements) {
        this.listePaiements = listePaiements;
    }

    public Date getDateDevis() {
        return dateDevis;
    }

    public void setDateDevis(Date dateDevis) {
        this.dateDevis = dateDevis;
    }

    public String getRefDevis() {
        return refDevis;
    }

    public void setRefDevis(String refDevis) {
        this.refDevis = refDevis;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }
}

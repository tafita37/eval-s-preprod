package eval.construction.construction.repository.devis;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import eval.construction.construction.model.devis.Devis;
import eval.construction.construction.model.profil.Client;
import jakarta.transaction.Transactional;

public interface DevisRepository extends JpaRepository<Devis, Integer> {
    @Query(
        value = "select sum(prix_unitaire*quantite) as prix_total from v_travail_type_maison where id_type_maison=:idTypeMaison", 
        nativeQuery = true
    )
    public double getPrixTotalTypeMaison(@Param("idTypeMaison") int idTypeMaison);

    public List<Devis> findByClient(Client client);

    @Query(
        value = "select*from devis",
        nativeQuery = true
    )
    public List<Devis> getListeDevisEnCours();

    @Query(
        value = "select coalesce(sum(prix_total*(1+pourcentage_augmentation_finition/100)), 0) as prix_final from devis",
        nativeQuery = true
    )
    public double getMontantTotalDevise();

    @Query(
        value = "select coalesce(sum(montant_payer), 0) as montant_payer from devis",
        nativeQuery = true
    )
    public double getMontantPayer();

    @Query(
        value = "insert into devis(ref_devis, id_client, id_type_maison, id_type_finition, date_debut, date_devis, id_lieu, prix_total, duree_type_maison, pourcentage_augmentation_finition) select  ref_devis, id_client, id_type_maison, id_type_finition, date_debut, date_devis, id_lieu, (select sum(prix_unitaire*quantite) as prix_total from v_travail_type_maison where id_type_maison=vtdtmflc.id_type_maison) prix_total, duree_jour, taux_finition from v_tmp_devis_type_maison_finition_lieu_client vtdtmflc group by ref_devis, id_client, id_type_maison, id_type_finition, date_debut, date_devis, id_lieu, duree_jour, taux_finition",
        nativeQuery = true
    )
    @Transactional
    @Modifying
    public int insertDeviseData();

    @Query(
        value = "update devis set montant_payer=:montantPayer where ref_devis=:refDevis",
        nativeQuery = true
    )
    @Transactional
    @Modifying
    public int changeMontantPayerDevis(@Param("montantPayer") double montant, @Param("refDevis") String refDevis);
}

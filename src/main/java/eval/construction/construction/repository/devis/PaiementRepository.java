package eval.construction.construction.repository.devis;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eval.construction.construction.model.devis.Devis;
import eval.construction.construction.model.devis.Paiement;
import jakarta.transaction.Transactional;

public interface PaiementRepository extends JpaRepository<Paiement, Integer> {
    public List<Paiement> findByDevis(Devis devis);

    @Query(
        value = "insert into paiement(id_devis, montant, date_paiement, ref_paiement) select id_devis, montant, date_paiement, ref_paiement from v_devis_tmp_paiement",
        nativeQuery = true
    )
    @Transactional
    @Modifying
    public int insertPaiementData();

    @Query(
        value = "select*from paiement where id_devis=:idDevis",
        nativeQuery = true
    )
    public List<Paiement> getPaiementOfDevis(@Param("idDevis") int idDevis);

    @Query(
        value = "select count(*) as nb from paiement where ref_paiement=:refPaiement",
        nativeQuery = true
    )
    public int paiementAlreadyExist(@Param("refPaiement") String refPaiement);
}

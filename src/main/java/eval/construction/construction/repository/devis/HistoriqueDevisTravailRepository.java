package eval.construction.construction.repository.devis;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import eval.construction.construction.model.devis.HistoriqueDevisTravail;
import jakarta.transaction.Transactional;

public interface HistoriqueDevisTravailRepository extends JpaRepository<HistoriqueDevisTravail, Integer> {
    public List<HistoriqueDevisTravail> findByIdDevis(int idDevis);

    @Query(
        value = "insert into historique_devis_travail(id_devis, id_travail, quantite_travail, prix_unitaire_travail) select id_devis, id_travail, quantite, prix_unitaire from v_devis_travail_maison",
        nativeQuery = true
    )
    @Transactional
    @Modifying
    public int insertHistoriqueDevisData();
}

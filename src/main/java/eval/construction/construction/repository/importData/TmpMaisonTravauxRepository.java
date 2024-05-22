package eval.construction.construction.repository.importData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import eval.construction.construction.model.importData.TmpMaisonTravaux;
import jakarta.transaction.Transactional;

public interface TmpMaisonTravauxRepository extends JpaRepository<TmpMaisonTravaux, Integer> {
    @Transactional
    @Modifying
    @Query(
        value = "insert into type_maison(nom_type_maison, duree_jour, description_type_maison, surface) select type_maison, duree_travaux, description, surface from tmp_maison_travaux group by type_maison, duree_travaux, description, surface",
        nativeQuery = true
    )
    public int inserDataTypeMaison();
}

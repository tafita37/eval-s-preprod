package eval.construction.construction.repository.construction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import eval.construction.construction.model.construction.TypeFinition;
import jakarta.transaction.Transactional;

public interface TypeFinitionRepository extends JpaRepository<TypeFinition, Integer> {
    @Query(
        value = "insert into type_finition(nom_type_finition, pourcentage_augmentation) select finition, taux_finition from tmp_devis group by finition, taux_finition",
        nativeQuery = true
    )
    @Transactional
    @Modifying
    public int insertTypeFinitionData();
}

package eval.construction.construction.repository.construction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import eval.construction.construction.model.construction.Unite;
import jakarta.transaction.Transactional;

public interface UniteRepository extends JpaRepository<Unite, Integer> {
    @Transactional
    @Modifying
    @Query(
        value = "insert into unite(nom_unite) select unite from tmp_maison_travaux group by unite",
        nativeQuery = true
    )
    int insertUniteData();
}

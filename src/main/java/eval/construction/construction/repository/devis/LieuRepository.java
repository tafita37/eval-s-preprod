package eval.construction.construction.repository.devis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import eval.construction.construction.model.devis.Lieu;
import jakarta.transaction.Transactional;

public interface LieuRepository extends JpaRepository<Lieu, Integer> {
    @Query(
        value = "insert into lieu(nom_lieu) select lieu from tmp_devis group by lieu",
        nativeQuery = true
    )
    @Transactional
    @Modifying
    public int insertLieuData();
}

package eval.construction.construction.repository.construction;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import eval.construction.construction.model.construction.TypeMaisonTravail;
import jakarta.transaction.Transactional;

public interface TypeMaisonTravailRepository extends JpaRepository<TypeMaisonTravail, Integer> {
    @Query(value = "select*from type_maison_travail where id_type_maison=:idTypeMaison", nativeQuery = true)
    public List<TypeMaisonTravail> findByTypeMaison(@Param("idTypeMaison") int idTypeMaison);

    @Query(
        value = "insert into type_maison_travail(id_type_maison, id_travail, quantite) select id_type_maison, id_travail, quantite from v_tmp_maison_travaux group by id_type_maison, id_travail, quantite",
        nativeQuery = true
    )
    @Transactional
    @Modifying
    public int insertTypeMaisonTravailData();
}

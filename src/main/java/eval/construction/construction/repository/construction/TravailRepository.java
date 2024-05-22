package eval.construction.construction.repository.construction;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import eval.construction.construction.model.construction.Travail;
import jakarta.transaction.Transactional;

public interface TravailRepository extends JpaRepository<Travail, Integer> {
    @Query(value = "select*from travail where id_travail_parent is null", nativeQuery = true)
    public List<Travail> getTravailParent();

    @Query(value = "select*from travail where id_travail_parent=:idTravail", nativeQuery = true)
    public List<Travail> getZanakaOfTravail(@Param("idTravail") int idTravail);

    @Query(
        value = "select*from travail where id_travail in (select id_travail_parent from travail where id_travail=:idTravail)", 
        nativeQuery = true
    )
    public Optional<Travail> getParentOfTravaiOptional(@Param("idTravail") int idTravail);

    @Query(value = "select*from travail where id_travail=:idTravail", nativeQuery = true)
    public Optional<Travail> getTravailById(@Param("idTravail") int idTravail);

    @Query(
        value = "insert into travail(nom_travail, numero, id_unite, prix_unitaire) select vtmtu.type_travaux, vtmtu.code_travaux, vtmtu.id_unite, vtmtu.prix_unitaire from v_tmp_maison_travaux_unite vtmtu group by vtmtu.type_travaux, vtmtu.code_travaux, vtmtu.id_unite, vtmtu.prix_unitaire",
        nativeQuery = true
    )
    @Transactional
    @Modifying
    public int insertDataTravail();
}

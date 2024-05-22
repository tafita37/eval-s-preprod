package eval.construction.construction.repository.devis;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eval.construction.construction.model.devis.VMontantPMoisAnnee;

public interface VMontantPMoisAnneeRepository extends JpaRepository<VMontantPMoisAnnee, Integer> {
    List<VMontantPMoisAnnee> findByAnnee(int annee);
}

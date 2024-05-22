package eval.construction.construction.repository.importData;

import org.springframework.data.jpa.repository.JpaRepository;
import eval.construction.construction.model.importData.TmpPaiement;

public interface TmpPaiementRepository extends JpaRepository<TmpPaiement, Integer> {
    
}

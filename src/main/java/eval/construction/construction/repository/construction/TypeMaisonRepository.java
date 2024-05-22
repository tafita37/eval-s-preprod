package eval.construction.construction.repository.construction;

import org.springframework.data.jpa.repository.JpaRepository;

import eval.construction.construction.model.construction.TypeMaison;

public interface TypeMaisonRepository extends JpaRepository<TypeMaison, Integer> {
    
}

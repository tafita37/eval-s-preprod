package eval.construction.construction.repository.profil;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import eval.construction.construction.model.profil.Client;
import jakarta.transaction.Transactional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findByNumeroClient(String numeroClient);

    @Query(
        value = "insert into client(numero_client) select client from tmp_devis group by client",
        nativeQuery = true
    )
    @Transactional
    @Modifying
    public int insertClientData();
}

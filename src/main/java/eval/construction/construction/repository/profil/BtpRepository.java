package eval.construction.construction.repository.profil;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import eval.construction.construction.model.profil.Btp;

public interface BtpRepository extends JpaRepository<Btp, Integer> {
    public Optional<Btp> findByEmailBtpAndMdpBtp(String emailBtp, String mdpBtp);
}

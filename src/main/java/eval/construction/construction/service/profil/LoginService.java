package eval.construction.construction.service.profil;

import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eval.construction.construction.exception.LoginAdminException;
import eval.construction.construction.model.profil.Btp;
import eval.construction.construction.model.profil.Client;
import eval.construction.construction.repository.profil.BtpRepository;
import eval.construction.construction.repository.profil.ClientRepository;

@Service
public class LoginService {
    @Autowired
    BtpRepository btpRepository;
    @Autowired
    ClientRepository clientRepository;

    public Btp loginBtp(String emailBtp, String mdpBtp)
    throws LoginAdminException {
        Optional<Btp> admin=btpRepository.findByEmailBtpAndMdpBtp(emailBtp, mdpBtp);
        if(admin.isEmpty()) {
            throw new LoginAdminException("Email ou mot de passe de admin incorrect");
        }
        return admin.get();
    }

    public Client loginClient(String emailClient) 
    throws Exception {
        Optional<Client> correspondingClient=clientRepository.findByNumeroClient(emailClient);
        if(correspondingClient.isEmpty()) {
            return clientRepository.save(new Client(emailClient));
        }
        return correspondingClient.get();
    }
}

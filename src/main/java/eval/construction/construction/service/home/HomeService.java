package eval.construction.construction.service.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eval.construction.construction.repository.construction.TravailRepository;
import eval.construction.construction.repository.construction.TypeFinitionRepository;
import eval.construction.construction.repository.construction.TypeMaisonRepository;
import eval.construction.construction.repository.construction.TypeMaisonTravailRepository;
import eval.construction.construction.repository.construction.UniteRepository;
import eval.construction.construction.repository.devis.DevisRepository;
import eval.construction.construction.repository.devis.HistoriqueDevisTravailRepository;
import eval.construction.construction.repository.devis.LieuRepository;
import eval.construction.construction.repository.devis.PaiementRepository;
import eval.construction.construction.repository.importData.TmpDevisRepository;
import eval.construction.construction.repository.importData.TmpMaisonTravauxRepository;
import eval.construction.construction.repository.importData.TmpPaiementRepository;
import eval.construction.construction.repository.profil.ClientRepository;
import jakarta.transaction.Transactional;

@Service
public class HomeService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    UniteRepository uniteRepository;
    @Autowired
    TmpPaiementRepository tmpPaiementRepository;
    @Autowired 
    TmpDevisRepository tmpDevisRepository;
    @Autowired
    TmpMaisonTravauxRepository tmpMaisonTravauxRepository;
    @Autowired
    PaiementRepository paiementRepository;
    @Autowired
    HistoriqueDevisTravailRepository historiqueDevisTravailRepository;
    @Autowired
    DevisRepository devisRepository;
    @Autowired
    LieuRepository lieuRepository;
    @Autowired
    TypeFinitionRepository typeFinitionRepository;
    @Autowired
    TypeMaisonTravailRepository typeMaisonTravailRepository;
    @Autowired
    TypeMaisonRepository typeMaisonRepository;
    @Autowired
    TravailRepository travailRepository;

    @Transactional(rollbackOn = {Exception.class})
    public void deleteAll() {
        tmpPaiementRepository.deleteAll();
        tmpDevisRepository.deleteAll();
        tmpMaisonTravauxRepository.deleteAll();
        paiementRepository.deleteAll();
        historiqueDevisTravailRepository.deleteAll();
        devisRepository.deleteAll();
        lieuRepository.deleteAll();
        typeFinitionRepository.deleteAll();
        typeMaisonTravailRepository.deleteAll();
        typeMaisonRepository.deleteAll();
        travailRepository.deleteAll();
        uniteRepository.deleteAll();
        clientRepository.deleteAll();
    }
}

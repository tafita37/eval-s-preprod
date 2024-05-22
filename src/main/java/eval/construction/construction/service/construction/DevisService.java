package eval.construction.construction.service.construction;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eval.construction.construction.model.construction.Travail;
import eval.construction.construction.model.construction.TypeFinition;
import eval.construction.construction.model.construction.TypeMaison;
import eval.construction.construction.model.construction.TypeMaisonTravail;
import eval.construction.construction.model.construction.Unite;
import eval.construction.construction.model.devis.Devis;
import eval.construction.construction.model.devis.HistoriqueDevisTravail;
import eval.construction.construction.model.devis.Paiement;
import eval.construction.construction.model.devis.VMontantPMoisAnnee;
import eval.construction.construction.model.importData.TmpDevis;
import eval.construction.construction.model.importData.TmpMaisonTravaux;
import eval.construction.construction.model.importData.TmpPaiement;
import eval.construction.construction.model.profil.Client;
import eval.construction.construction.repository.construction.TravailRepository;
import eval.construction.construction.repository.construction.TypeFinitionRepository;
import eval.construction.construction.repository.construction.TypeMaisonRepository;
import eval.construction.construction.repository.construction.TypeMaisonTravailRepository;
import eval.construction.construction.repository.construction.UniteRepository;
import eval.construction.construction.repository.devis.DevisRepository;
import eval.construction.construction.repository.devis.HistoriqueDevisTravailRepository;
import eval.construction.construction.repository.devis.LieuRepository;
import eval.construction.construction.repository.devis.PaiementRepository;
import eval.construction.construction.repository.devis.VMontantPMoisAnneeRepository;
import eval.construction.construction.repository.importData.TmpDevisRepository;
import eval.construction.construction.repository.importData.TmpMaisonTravauxRepository;
import eval.construction.construction.repository.importData.TmpPaiementRepository;
import eval.construction.construction.repository.profil.ClientRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Service
public class DevisService {
    @Autowired
    TypeMaisonRepository typeMaisonRepository;
    @Autowired
    TypeFinitionRepository typeFinitionRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    DevisRepository devisRepository;
    @Autowired
    TypeMaisonTravailRepository typeMaisonTravailRepository;
    @Autowired
    HistoriqueDevisTravailRepository historiqueDevisTravailRepository;
    @Autowired
    TravailRepository travailRepository;
    @Autowired
    UniteRepository uniteRepository;
    @Autowired 
    PaiementRepository paiementRepository;
    @Autowired
    VMontantPMoisAnneeRepository vMontantPMoisAnneeRepository;
    @Autowired
    TmpDevisRepository tmpDevisRepository;
    @Autowired
    TmpMaisonTravauxRepository tmpMaisonTravauxRepository;
    @Autowired
    LieuRepository lieuRepository;
    @Autowired
    TmpPaiementRepository tmpPaiementRepository;

    public List<TypeMaison> getAllTypeMaison() {
        return typeMaisonRepository.findAll();
    }

    public List<TypeFinition> getAllTypeFinition() {
        return typeFinitionRepository.findAll();
    }

    @Transactional(rollbackOn = {Exception.class})
    public Devis saveDevis(int idClient, int idTypeMaison, int idTypeFinition, Date dateDebut)
    throws Exception {
        Client client=clientRepository.findById(idClient).get();
        TypeMaison typeMaison=typeMaisonRepository.findById(idTypeMaison).get();
        TypeFinition typeFinition=typeFinitionRepository.findById(idTypeFinition).get();
        double prixTotal=devisRepository.getPrixTotalTypeMaison(typeMaison.getIdTypeMaison());
        Devis devis=new Devis(client, typeMaison, typeFinition, dateDebut, prixTotal);
        devis.afficherInfo();
        devis=devisRepository.save(devis);
        System.out.println("Type maisons "+typeMaison.getIdTypeMaison());
        List<TypeMaisonTravail> listeTypeMaisonTravail=typeMaisonTravailRepository.findByTypeMaison(typeMaison.getIdTypeMaison());
        System.out.println("Maison et travails "+listeTypeMaisonTravail);
        List<HistoriqueDevisTravail> listeHistoriqueDevisTravail=new ArrayList<>();
        for(int i=0; i<listeTypeMaisonTravail.size(); i++) {
            if(listeTypeMaisonTravail.get(i).getTravail().getPrixUnitaire()!=0) {
                listeHistoriqueDevisTravail.add(
                    new HistoriqueDevisTravail(
                        devis.getIdDevis(), listeTypeMaisonTravail.get(i).getTravail(), listeTypeMaisonTravail.get(i).getQuantite()
                    )
                );
            }
        }
        historiqueDevisTravailRepository.saveAllAndFlush(listeHistoriqueDevisTravail);
        return devis;
    }

    public List<Devis> getListeDevisOfClient(int idClient) {
        return devisRepository.findByClient(clientRepository.findById(idClient).get());
    }

    public Devis findDevisById(int idDevis) {
        return devisRepository.findById(idDevis).get();
    }

    public List<HistoriqueDevisTravail> getHistoriqueOfDevis(int idDevis)
    throws Exception {
        List<HistoriqueDevisTravail> listeHistoriqueTravail=historiqueDevisTravailRepository.findByIdDevis(
            idDevis
        );
        return listeHistoriqueTravail;
    }

    public Unite findUniteById(int idUnite) {
        return uniteRepository.findById(idUnite).get();
    }

    public Travail findTravailById(int idTravail) {
        return travailRepository.findById(idTravail).get();
    }

    public List<Unite> getAllUnite() {
        return uniteRepository.findAll();
    }

    @Transactional(rollbackOn = {Exception.class})
    public Paiement payer(HttpSession httpSession, int idDevis, double montant, Date datePaiement)
    throws Exception {
        int idClient=(int) httpSession.getAttribute("idClient");
        Devis devis=devisRepository.findById(idDevis).get();
        if(devis.getPrixTotal()<devis.getMontantPayer()+montant) {
            throw new Exception("Le montant que vous aller verser depasse le prix du devis");
        }
        devis.setMontantPayer(devis.getMontantPayer()+montant);
        if(devis.getMontantPayer()!=devis.getPrixTotal()) {
            devis.setEnCours(11);
        } else {
            devis.setEnCours(21);
        }
        devisRepository.save(devis);
        if(devis.getClient().getIdClient()!=idClient) {
            throw new Exception("Vous ne pouvez pas effectuer de paiement car ce n'est pas votre devis");
        }
        Paiement paiement=new Paiement(devis, montant, datePaiement);
        return paiementRepository.save(paiement);
    }

    public List<Devis> getListeDevisEnCours() {
        List<Devis> listDevisEnCours=devisRepository.getListeDevisEnCours();
        for(int i=0; i<listDevisEnCours.size(); i++) {
            listDevisEnCours.get(i).setListePaiements(paiementRepository.findByDevis(listDevisEnCours.get(i)));
        }
        return listDevisEnCours;
    }

    public List<VMontantPMoisAnnee> getStatBMoisAnnee(int annee) {
        List<VMontantPMoisAnnee> result=vMontantPMoisAnneeRepository.findByAnnee(annee);
        if(result.size()==0) {
            for(int i=1; i<=12; i++) {
                result.add(new VMontantPMoisAnnee(0, i, annee));
            }
        }
        return result;
    }

    public double getMontantTotalDevise() {
        return devisRepository.getMontantTotalDevise();
    }

    public double getMontantPayer() {
        return devisRepository.getMontantPayer();
    }

    public List<Travail> getAllTravail() {
        return travailRepository.findAll();
    }

    @Transactional(rollbackOn = {Exception.class})
    public void saveData(TmpMaisonTravaux[] listeTmpMaisonTravaux, TmpDevis[] listeTmpDevis) {
        System.out.println(listeTmpMaisonTravaux.length);
        tmpMaisonTravauxRepository.saveAll(Arrays.asList(listeTmpMaisonTravaux));
        tmpMaisonTravauxRepository.inserDataTypeMaison();
        uniteRepository.insertUniteData();
        travailRepository.insertDataTravail();
        typeMaisonTravailRepository.insertTypeMaisonTravailData();
        tmpDevisRepository.saveAll(Arrays.asList(listeTmpDevis));
        clientRepository.insertClientData();
        typeFinitionRepository.insertTypeFinitionData();
        lieuRepository.insertLieuData();
        devisRepository.insertDeviseData();
        historiqueDevisTravailRepository.insertHistoriqueDevisData();
    }

    @Transactional(rollbackOn = {Exception.class})
    public void savePaiement(TmpPaiement[] listeTmpPaiement) {
        for(int i=0; i<listeTmpPaiement.length; i++) {
            if(listeTmpPaiement[i]!=null&&paiementRepository.paiementAlreadyExist(listeTmpPaiement[i].getRefPaiement())==0) {
                tmpPaiementRepository.save(listeTmpPaiement[i]);
            }
        }
        // tmpPaiementRepository.saveAll(Arrays.asList(listeTmpPaiement));
        paiementRepository.insertPaiementData();
        HashMap<String, Double> refPaiement=new HashMap<>();
        for(int i=0; i<listeTmpPaiement.length; i++) {
            if(listeTmpPaiement[i]!=null) {
                if(refPaiement.containsKey(listeTmpPaiement[i].getRefDevis())) {
                    refPaiement.replace(
                        listeTmpPaiement[i].getRefDevis(), 
                        refPaiement.get(listeTmpPaiement[i].getRefDevis())+listeTmpPaiement[i].getMontant()
                    );
                } else {
                    refPaiement.put(listeTmpPaiement[i].getRefDevis(), listeTmpPaiement[i].getMontant());
                }
            }
        }
        for (Map.Entry<String, Double> entry : refPaiement.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            devisRepository.changeMontantPayerDevis(value, key);
        }
        tmpPaiementRepository.deleteAll();
    }

    public Travail modifierTravail(int idTravail, String nomTravail, String numero, int idUnite, double prixUnitaire) {
        Travail travail=new Travail(idTravail, nomTravail, numero, this.findUniteById(idUnite), prixUnitaire);
        return travailRepository.save(travail);
    }

    public TypeFinition modifierTypeFinition(int idTypeFinition, double pourcentageAugmentation)
    throws Exception {
        TypeFinition typeFinition=typeFinitionRepository.findById(idTypeFinition).get();
        typeFinition.setPourcentageAugmentation(pourcentageAugmentation);
        return typeFinitionRepository.save(typeFinition);
    }

    public TypeFinition findTypeFinitionById(int idTypeFinition) {
        return typeFinitionRepository.findById(idTypeFinition).get();
    }

    public List<Paiement> getPaiementOfDevis(int idDevis) {
        return paiementRepository.getPaiementOfDevis(idDevis);
    }
}

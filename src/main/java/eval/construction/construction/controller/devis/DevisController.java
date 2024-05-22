package eval.construction.construction.controller.devis;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.xhtmlrenderer.pdf.ITextRenderer;
import com.opencsv.exceptions.CsvValidationException;
import eval.construction.construction.Constante;
import eval.construction.construction.config.RequireRole;
import eval.construction.construction.exception.ImportException;
import eval.construction.construction.model.devis.Devis;
import eval.construction.construction.model.devis.HistoriqueDevisTravail;
import eval.construction.construction.model.devis.Paiement;
import eval.construction.construction.model.devis.VMontantPMoisAnnee;
import eval.construction.construction.model.importData.TmpDevis;
import eval.construction.construction.model.importData.TmpMaisonTravaux;
import eval.construction.construction.model.importData.TmpPaiement;
import eval.construction.construction.service.construction.DevisService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/devis")
public class DevisController {
    @Autowired
    DevisService devisService;
    @Value("${app.baseUrl}")
    private String baseUrl;

    @GetMapping("/formDevis")
    @RequireRole("client")
    public ModelAndView formDevise() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/formDevise");
        modelAndView.addObject("listeTypeMaison", devisService.getAllTypeMaison());
        modelAndView.addObject("listeTypeFinition", devisService.getAllTypeFinition());
        return modelAndView;
    }

    @RequireRole("admin")
    @GetMapping("/listeDevisEnCours")
    public ModelAndView getListeDevisEnCours() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/listeDevisEnCours");
        modelAndView.addObject("listeDevis", devisService.getListeDevisEnCours());
        return modelAndView;
    }

    @GetMapping("/tableauBordDevis")
    @RequireRole("admin")
    public ModelAndView getTableauBordDevis() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/tableauBordDevis");
        modelAndView.addObject("montantDevisTotal", devisService.getMontantTotalDevise());
        modelAndView.addObject("montantPayer", devisService.getMontantPayer());
        return modelAndView;
    }

    @PostMapping("/newDevis")
    @RequireRole("client")
    public ModelAndView newDevis(
        HttpSession httpSession, 
        @RequestParam("idTypeMaison") int idTypeMaison, 
        @RequestParam("idTypeFinition") int idTypeFinition,
        @RequestParam("dateDebut") Date dateDebut
    )
    throws Exception {
        int idClient=(int) httpSession.getAttribute("idClient");
        System.out.println(idClient);
        devisService.saveDevis(idClient, idTypeMaison, idTypeFinition, dateDebut);
        ModelAndView modelAndView=new ModelAndView();
        RedirectView redirectView=new RedirectView("/devis/formDevis");
        modelAndView.setView(redirectView);
        return modelAndView;
    }

    @GetMapping("/listeDevisClient")
    @RequireRole("client")
    public ModelAndView getListeDevisClient(HttpSession httpSession) {
        ModelAndView modelAndView=new ModelAndView("dynamique/listeDevis");
        int idClient=(int) httpSession.getAttribute("idClient");
        String[] js={"formPaiement.js"};
        modelAndView.addObject("jssFiles", js);
        modelAndView.addObject("listeDevis", devisService.getListeDevisOfClient(idClient));
        return modelAndView;
    }

    @PostMapping("/payerDevis")
    @RequireRole("client")
    public ModelAndView payerDevis(
        HttpSession httpSession, 
        @RequestParam("idDevis") int idDevis, 
        @RequestParam("montant") double montant,
        @RequestParam("datePaiement") Date datePaiement
    )
    throws Exception {
        devisService.payer(httpSession, idDevis, montant, datePaiement);
        ModelAndView modelAndView=new ModelAndView();
        RedirectView redirectView=new RedirectView("/devis/listeDevisClient");
        modelAndView.setView(redirectView);
        return modelAndView;
    }

    @PostMapping("/payerDevisAjax")
    @RequireRole("client")
    public String payerDevisAjax(
        HttpSession httpSession, 
        @RequestParam("idDevis") int idDevis, 
        @RequestParam("montant") double montant,
        @RequestParam("datePaiement") Date datePaiement
    ) {
        try {
            devisService.payer(httpSession, idDevis, montant, datePaiement);
            return "Mety";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @GetMapping("/statBMois")
    @RequireRole("admin")
    public List<VMontantPMoisAnnee> getStatBMoisAnnee(@RequestParam("annee") int annee) {
        return devisService.getStatBMoisAnnee(annee);
    }

    @GetMapping("/detailDevis")
    @RequireRole("admin")
    public ModelAndView getDetailsDevis(@RequestParam("idDevis") int idDevis) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/detailDevis");
        modelAndView.addObject("idDevis", idDevis);
        modelAndView.addObject("listeDetailsDevis", devisService.findDevisById(idDevis).getListeHistoriqueDevisTravail());
        return modelAndView;
    }

    @GetMapping("/exportPdf")
    @RequireRole("client")
    public void exportPdf(HttpServletResponse response, @RequestParam("idDevis") int idDevis) throws Exception {
        Devis devis=devisService.findDevisById(idDevis);
        Locale locale = new Locale("fr", "FR");
        NumberFormat formatteur = NumberFormat.getInstance(locale);
        List<HistoriqueDevisTravail> listeHistoriqueDevisTravail=devisService.getHistoriqueOfDevis(idDevis);
        List<Paiement> listePaiement=devisService.getPaiementOfDevis(idDevis);
        String htmlContain="";
        String htmlContainPaiement="";
        double sumPrixDevise=0;
        double sumMontantPaiement=0;
        for(int i=0; i<listeHistoriqueDevisTravail.size(); i++) {
            htmlContain+="<tr>";
            htmlContain+="<td>"+listeHistoriqueDevisTravail.get(i).getTravail().getNumero()+"</td>";
            htmlContain+="<td>"+listeHistoriqueDevisTravail.get(i).getTravail().getNomTravail()+"</td>";
            htmlContain+="<td>"+listeHistoriqueDevisTravail.get(i).getTravail().getUnite().getNomUnite()+"</td>";
            htmlContain+="<td>"+listeHistoriqueDevisTravail.get(i).getQuantiteTravail()+"</td>";
            htmlContain+="<td>"+formatteur.format(listeHistoriqueDevisTravail.get(i).getPrixUnitaireTravail())+"</td>";
            htmlContain+="<td>"+formatteur.format(listeHistoriqueDevisTravail.get(i).getPrixTotal())+"</td>";
            htmlContain+="</tr>";
            sumPrixDevise+=listeHistoriqueDevisTravail.get(i).getPrixTotal();
        }
        for(int i=0; i<listePaiement.size(); i++) {
            htmlContainPaiement+="<tr>";
            htmlContainPaiement+="<td>"+listePaiement.get(i).getRefPaiement()+"</td>";
            htmlContainPaiement+="<td>"+listePaiement.get(i).getDatePaiement()+"</td>";
            htmlContainPaiement+="<td>"+formatteur.format(listePaiement.get(i).getMontant())+"</td>";
            htmlContainPaiement+="</tr>";
            sumMontantPaiement+=listePaiement.get(i).getMontant();
        }
        htmlContain+="<tr><td colspan=\"6\"></td></tr>";
        htmlContain+="<tr>";
        htmlContain+="<td colspan=\"5\">Prix sans augmentation</td>";
        htmlContain+="<td>"+formatteur.format(sumPrixDevise)+"</td>";
        htmlContain+="</tr>";
        htmlContain+="<tr>";
        htmlContain+="<td colspan=\"5\">Pourcentage avec finitio</td>";
        htmlContain+="<td>"+formatteur.format(devis.getPourcentageAugmentationFinition())+"%</td>";
        htmlContain+="</tr>";
        htmlContain+="<tr>";
        htmlContain+="<td colspan=\"5\">Prix final</td>";
        htmlContain+="<td>"+formatteur.format(devis.getFinalPrice())+"</td>";
        htmlContain+="</tr>";
        htmlContainPaiement+="<tr><td colspan=\"3\"></td></tr>";
        htmlContainPaiement+="<tr>";
        htmlContainPaiement+="<td colspan=\"2\">Montant total du paiement</td>";
        htmlContainPaiement+="<td>"+formatteur.format(sumMontantPaiement)+"</td>";
        htmlContainPaiement+="</tr>";
        htmlContainPaiement+="<tr>";
        htmlContainPaiement+="<td colspan=\"2\">Reste a payer</td>";
        htmlContainPaiement+="<td>"+formatteur.format(devis.getResteAPayer())+"</td>";
        htmlContainPaiement+="</tr>";
        String contenu="<h2>Devise numero "+devis.getRefDevis()+"</h2>"+
                        "<table class=\"custom-table\">"+
                            "<thead>"+
                                "<tr>"+
                                    "<th>Code</th>"+
                                    "<th>Designation</th>"+
                                    "<th>U</th>"+
                                    "<th>Q</th>"+
                                    "<th>PU</th>"+
                                    "<th>Total</th>"+
                                "</tr>"+
                            "</thead>"+
                            "<tbody>"+
                                htmlContain+
                            "</tbody>"+
                        "</table>"+
                        "<h2> Liste de paiements </h2>"+
                        "<table class=\"custom-table\">"+
                            "<thead>"+
                                "<tr>"+
                                    "<th>Ref</th>"+
                                    "<th>Date</th>"+
                                    "<th>Montant</th>"+
                                "</tr>"+
                            "</thead>"+
                            "<tbody>"+
                                htmlContainPaiement+
                            "</tbody>"+
                        "</table>";
        String htmlContent=Constante.getHTMLPDFTemplate(contenu, baseUrl, 1000);
        System.out.println(htmlContent);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        renderer.createPDF(outputStream);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"export.pdf\"");
        OutputStream responseOutputStream = response.getOutputStream();
        outputStream.writeTo(responseOutputStream);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @RequireRole("admin")
    @GetMapping("/formDataImport")
    public ModelAndView loadFormDataImport() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/formDataImport");
        return modelAndView;
    }

    @RequireRole("admin")
    @GetMapping("/formPaiementImport")
    public ModelAndView formPaiementImport() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/formPaiementImport");
        return modelAndView;
    }

    @RequireRole("admin")
    @PostMapping("/importDataCSV")
    public ModelAndView importCSV(
        @RequestParam("maisonTravauxFile") MultipartFile maisonTravauxFile, 
        @RequestParam("devisFile") MultipartFile devisFile
    ) throws IOException, ImportException, CsvValidationException {
        List<String[]> dataList = Constante.readCSVFile(maisonTravauxFile);
        TmpMaisonTravaux[] listeTmpMaisonTravaux=TmpMaisonTravaux.getListeImportCSV(dataList);
        List<String[]> dataList1 = Constante.readCSVFile(devisFile);
        TmpDevis[] listeTmpDevis=TmpDevis.getListeImportCSV(dataList1);
        devisService.saveData(listeTmpMaisonTravaux, listeTmpDevis);
        ModelAndView modelAndView = new ModelAndView();
        RedirectView redirectView = new RedirectView("/devis/tableauBordDevis");
        modelAndView.setView(redirectView);
        return modelAndView;
    }

    @RequireRole("admin")
    @PostMapping("/importPaiementCSV")
    public ModelAndView importPaiementCSV(
        @RequestParam("paiementFile") MultipartFile paiementFile
    ) throws IOException, ImportException, CsvValidationException {
        List<String[]> dataList = Constante.readCSVFile(paiementFile);
        TmpPaiement[] listeTmpPaiement=TmpPaiement.getListeImportCSV(dataList);
        devisService.savePaiement(listeTmpPaiement);
        ModelAndView modelAndView = new ModelAndView();
        RedirectView redirectView = new RedirectView("/devis/tableauBordDevis");
        modelAndView.setView(redirectView);
        return modelAndView;
    }

    @RequireRole("admin")
    @GetMapping("/listeTravail")
    public ModelAndView getListeTravail() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/listeTravail");
        modelAndView.addObject("listeTravaux", devisService.getAllTravail());
        return modelAndView;
    }

    @RequireRole("admin")
    @GetMapping("/listeTypeFinition")
    public ModelAndView getListeTypeFinition() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/listeTypeFinition");
        modelAndView.addObject("listeTypeFinition", devisService.getAllTypeFinition());
        return modelAndView;
    }

    @RequireRole("admin")
    @GetMapping("/loadFormModifTravail")
    public ModelAndView loadFormModifTravail(@RequestParam("idTravail") int idTravail) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/formModifTravail");
        modelAndView.addObject("travail", devisService.findTravailById(idTravail));
        modelAndView.addObject("listeUnite", devisService.getAllUnite());
        return modelAndView;
    }

    @RequireRole("admin")
    @GetMapping("/loadFormModifTypeFinition")
    public ModelAndView loadFormModifTypeFinition(@RequestParam("idTypeFinition") int idTypeFinition) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/formModifTypeFinition");
        modelAndView.addObject("typeFinition", devisService.findTypeFinitionById(idTypeFinition));
        return modelAndView;
    }

    @RequireRole("admin")
    @PostMapping("/modifierTypeFinition")
    public ModelAndView modifierTypeFinition(
        @RequestParam("idTypeFinition") int idTypeFinition, 
        @RequestParam("pourcentageAugmentation") double pourcentageAugmentation
    )
    throws Exception {
        devisService.modifierTypeFinition(idTypeFinition, pourcentageAugmentation);
        ModelAndView modelAndView=new ModelAndView();
        RedirectView redirectView=new RedirectView("/devis/listeTypeFinition");
        modelAndView.setView(redirectView);
        return modelAndView;
    }

    @RequireRole("admin")
    @PostMapping("/modifierTravail")
    public ModelAndView modifierTravail(
        @RequestParam("idTravail") int idTravail,
        @RequestParam("nomTravail") String nomTravail, 
        @RequestParam("numero") String numero, 
        @RequestParam("idUnite") int idUnite,
        @RequestParam("prixUnitaire") double prixUnitaire
    ) {
        devisService.modifierTravail(idTravail, nomTravail, numero, idUnite, prixUnitaire);
        ModelAndView modelAndView=new ModelAndView();
        RedirectView redirectView=new RedirectView("/devis/listeTravail");
        modelAndView.setView(redirectView);
        return modelAndView;
    }
}

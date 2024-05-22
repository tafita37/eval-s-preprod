package eval.construction.construction.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import eval.construction.construction.model.profil.Btp;
import eval.construction.construction.model.profil.Client;
import eval.construction.construction.service.profil.LoginService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @GetMapping("/formLoginClient")
    public ModelAndView loadFormLoginClient() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/loginClient");
        return modelAndView;
    }

    @GetMapping("/formSignInClient")
    public ModelAndView loadFormSignClient() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/signInClient");
        return modelAndView;
    }

    @GetMapping("/formLoginBtp")
    public ModelAndView loadFormLoginBtp() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/loginBtp");
        return modelAndView;
    }

    @PostMapping("/loginClient")
    public ModelAndView loginClient(
        @RequestParam("numeroClient") String numeroClient, 
        HttpSession httpSession
    )
    throws Exception {
        ModelAndView modelAndView=new ModelAndView();
        Client client=loginService.loginClient(numeroClient);
        httpSession.setAttribute("idClient", client.getIdClient());
        httpSession.setAttribute("role", "client");
        RedirectView redirectView=new RedirectView("/devis/listeDevisClient");
        modelAndView.setView(redirectView);
        System.out.println("Connectee");
        return modelAndView;
    }

    @PostMapping("/loginBtp")
    public ModelAndView loginBtp(
        @RequestParam("emailBtp") String emailBtp, 
        @RequestParam("mdpBtp") String mdpBtp, 
        HttpSession httpSession
    )
    throws Exception {
        ModelAndView modelAndView=new ModelAndView();
        Btp btp=loginService.loginBtp(emailBtp, mdpBtp);
        httpSession.setAttribute("idBtp", btp.getIdBtp());
        httpSession.setAttribute("role", "admin");
        RedirectView redirectView=new RedirectView("/devis/tableauBordDevis");
        modelAndView.setView(redirectView);
        System.out.println("Connectee");
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate(); // Invalider la session
        ModelAndView modelAndView=new ModelAndView();
        RedirectView redirectView=new RedirectView("/");
        modelAndView.setView(redirectView);
        return modelAndView;
    }
}

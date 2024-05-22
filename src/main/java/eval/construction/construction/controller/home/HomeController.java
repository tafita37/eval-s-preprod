package eval.construction.construction.controller.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import eval.construction.construction.config.RequireRole;
import eval.construction.construction.service.home.HomeService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    HomeService homeService;

    @GetMapping("")
    @RequireRole({"client", "admin"})
    public ModelAndView loadHomePage(HttpSession httpSession) {
        System.out.println((int) httpSession.getAttribute("idClient"));
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/home");
        return modelAndView;
    }

    @GetMapping("/devis")
    public ModelAndView staticDevis() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/devis");
        return modelAndView;
    }

    @GetMapping("/deleteAll")
    public ModelAndView deleteAll(HttpSession httpSession) {
        httpSession.invalidate();
        homeService.deleteAll();
        ModelAndView modelAndView=new ModelAndView();
        RedirectView redirectView=new RedirectView("/");
        modelAndView.setView(redirectView);;
        return modelAndView;
    }
}

package eval.construction.construction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpSession;

@Controller
public class BeginController {
    @GetMapping("/")
    public ModelAndView entryPoint(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        RedirectView redirectView = new RedirectView();
        String url="/login/formLoginClient";
        if(httpSession.getAttribute("role")!=null) {
            if(((String) httpSession.getAttribute("role")).compareTo("client")==0) {
                url="/devis/listeDevisClient";
            }
            if(((String) httpSession.getAttribute("role")).compareTo("admin")==0) {
                url="/devis/tableauBordDevis";
            }
        }
        redirectView.setUrl(url);
        modelAndView.setView(redirectView);
        return modelAndView;
    }

    @GetMapping("/test")
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dynamique/test");
        return modelAndView;
    }
}

package eval.construction.construction.controllerAdvice;
// package eval.cinepax.cinepax.controllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import eval.construction.construction.exception.ImportException;
import eval.construction.construction.exception.LoginAdminException;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ImportException.class)
    public ModelAndView getErrorMessage(ImportException excelException, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("excelError", excelException);
        RedirectView redirectView=new RedirectView("/devis/formDataImport");
        ModelAndView modelAndView=new ModelAndView(redirectView);
        modelAndView.addObject("excelError", excelException);
        excelException.printStackTrace();
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView loadErrorMessage(Exception ex) {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("dynamique/error/errorPage");
        modelAndView.addObject("errorMessage", ex.getMessage());
        ex.printStackTrace();
        return modelAndView;
    }

    @ExceptionHandler(LoginAdminException.class)
    public ModelAndView loadLoginErrorMessage(LoginAdminException loginAdminException, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("errorMessage", loginAdminException.getMessage());
        RedirectView redirectView=new RedirectView("/login/formLoginBtp");
        ModelAndView modelAndView=new ModelAndView(redirectView);
        modelAndView.addObject("errorMessage", loginAdminException.getMessage());
        return modelAndView;
    }

}
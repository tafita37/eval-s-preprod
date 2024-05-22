package eval.construction.construction.config;

import java.util.Arrays;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpSession;

@Aspect
@Component
public class RoleAuthorizationAspect {
    @Autowired
    private HttpSession httpSession;

    @Before("@annotation(requireRole)")
    public void checkRole(RequireRole requireRole) {
        String role = (String) httpSession.getAttribute("role");
        if (role == null || !Arrays.asList(requireRole.value()).contains(role)) {
            throw new UnauthorizedAccessException(
                "Access denied. You must have one of those roles :\n ["+ 
                String.join( ",", requireRole.value())+ 
                "] to access this resource."
            );
        }
    }

    @Before("execution(* *(..)) && @within(requireRoleMapping)")
    public void checkRoleClass(RequireRoleMapping requireRoleMapping) {
        String role = (String) httpSession.getAttribute("role");
        if (role == null || !Arrays.asList(requireRoleMapping.value()).contains(role)) {
            throw new UnauthorizedAccessException(
                "Access denied. You must have one of those roles :\n ["+ 
                String.join( ",", requireRoleMapping.value())+ 
                "] to access this resource."
            );
        }
    }
}


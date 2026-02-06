package e_commerce.config;

import jakarta.servlet.http.HttpServletRequest;
import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute("currentUser")
    public String currentUser(Principal principal) {
        return (principal == null) ? null : principal.getName();
    }

    @ModelAttribute("isAdmin")
    public boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) return false;
        return auth.getAuthorities().stream()
                   .anyMatch(a -> "ROLE_ADMIN".equals(a.getAuthority()));
    }

    @ModelAttribute("_csrf")
    public CsrfToken csrfToken(HttpServletRequest request) {
        Object token = request.getAttribute("_csrf");
        if (token instanceof CsrfToken) {
            return (CsrfToken) token;
        }
        return null;
    }
}

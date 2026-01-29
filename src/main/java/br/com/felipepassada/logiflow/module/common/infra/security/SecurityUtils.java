package br.com.felipepassada.logiflow.module.common.infra.security;

import br.com.felipepassada.logiflow.module.common.domain.exception.UnauthenticatedUserException;
import br.com.felipepassada.logiflow.module.identity.application.security.UserPrincipal;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SecurityUtils {

    public static UUID getCurrentUserId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserPrincipal)) {
            throw new UnauthenticatedUserException();
        }
        return ((UserPrincipal) authentication.getPrincipal()).getUserId();
    }
}

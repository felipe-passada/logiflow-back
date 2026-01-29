package br.com.felipepassada.logiflow.module.identity.infra.persistence;

import br.com.felipepassada.logiflow.module.identity.domain.User;
import org.springframework.stereotype.Component;
import br.com.felipepassada.logiflow.module.identity.domain.valueObjects.Password;

import java.time.LocalDateTime;

@Component
public class UserMapper {
    public UserEntityJpa toJpa(User domain) {
        return new UserEntityJpa(
                domain.getId(),
                domain.getEmail(),
                domain.getPassword().getValue(), // Pega o hash do VO
                domain.getRole(),
                domain.isActive(),
                LocalDateTime.now()
        );
    }

    public User toDomain(UserEntityJpa jpa) {
        return new User(
                jpa.getId(),
                jpa.getEmail(),
                new Password(jpa.getPasswordHash()),
                jpa.getRole(),
                jpa.getIsActive()
        );
    }
}
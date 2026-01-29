package br.com.felipepassada.logiflow.module.identity.application;

import br.com.felipepassada.logiflow.module.identity.domain.Role;
import br.com.felipepassada.logiflow.module.identity.domain.User;
import br.com.felipepassada.logiflow.module.identity.domain.services.PasswordHasher;
import br.com.felipepassada.logiflow.module.identity.domain.valueObjects.Password;
import br.com.felipepassada.logiflow.module.identity.infra.persistence.UserMapper;
import br.com.felipepassada.logiflow.module.identity.infra.persistence.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class RegisterUserUseCase {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordHasher passwordHasher;

    public RegisterUserUseCase(UserRepository userRepository, UserMapper userMapper, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordHasher = passwordHasher;
    }

    @Transactional
    public UUID execute(String email, String rawPassword, Role role) {
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("Utilizador já existe com este email");
        }

        // 1. Cifra a senha
        String hashedPassword = passwordHasher.hash(rawPassword);

        // 2. Cria a entidade de Domínio (usando seu Value Object)
        // Aqui você pode adicionar um construtor na sua classe User para facilitar
        User user = new User(
                UUID.randomUUID(),
                email,
                new Password(hashedPassword),
                role,
                true
        );

        // 3. Converte para JPA e guarda
        var jpaEntity = userMapper.toJpa(user);
        userRepository.save(jpaEntity);

        return user.getId();
    }
}

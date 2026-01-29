package br.com.felipepassada.logiflow.module.identity.api;

import br.com.felipepassada.logiflow.module.identity.api.dtos.request.RegisterUserRequestDto;
import br.com.felipepassada.logiflow.module.identity.application.RegisterUserUseCase;
import br.com.felipepassada.logiflow.module.identity.domain.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final RegisterUserUseCase registerUserUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase) {
        this.registerUserUseCase = registerUserUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<UUID> register(@RequestBody RegisterUserRequestDto request) {
        UUID userId = registerUserUseCase.execute(
                request.email(),
                request.password(),
                Role.valueOf(request.role())
        );
        return ResponseEntity.ok(userId);
    }
}

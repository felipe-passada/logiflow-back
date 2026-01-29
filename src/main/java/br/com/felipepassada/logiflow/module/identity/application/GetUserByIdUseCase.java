package br.com.felipepassada.logiflow.module.identity.application;

import br.com.felipepassada.logiflow.module.identity.api.dtos.response.UserResponseDto;
import br.com.felipepassada.logiflow.module.identity.infra.persistence.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GetUserByIdUseCase {
    private final UserRepository userRepository;

    public GetUserByIdUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto execute(java.util.UUID userId) {
        return userRepository.findById(userId)
                .map(user -> new UserResponseDto(
                    user.getId(),
                    user.getEmail(),
                    user.getRole(),
                    user.getIsActive()
                )).
                orElseThrow(() -> new RuntimeException("User not found"));
    }
}

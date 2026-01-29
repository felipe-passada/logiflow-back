package br.com.felipepassada.logiflow.module.fleet.application;

import br.com.felipepassada.logiflow.module.fleet.api.dtos.request.RegisterDriverRequestDto;
import br.com.felipepassada.logiflow.module.fleet.domain.Driver;
import br.com.felipepassada.logiflow.module.fleet.infra.persistence.DriverMapper;
import br.com.felipepassada.logiflow.module.fleet.infra.persistence.DriverRepository;
import br.com.felipepassada.logiflow.module.identity.application.RegisterUserUseCase;
import br.com.felipepassada.logiflow.module.identity.domain.Role;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterDriverUseCase {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;
    private final RegisterUserUseCase registerUserUseCase;

    public RegisterDriverUseCase(DriverRepository driverRepository,
                                 DriverMapper driverMapper,
                                 RegisterUserUseCase registerUserUseCase) {
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
        this.registerUserUseCase = registerUserUseCase;
    }

    @Transactional
    public UUID execute(RegisterDriverRequestDto driverRequestDto) {
        UUID userId = registerUserUseCase.execute(
                driverRequestDto.email(),
                driverRequestDto.password(),
                Role.DRIVER
        );

        var driver = new Driver(
                UUID.randomUUID(),
                userId,
                driverRequestDto.fullName(),
                driverRequestDto.vehiclePlate()
        );

        var driverJpa = driverMapper.toJpa(driver);
        driverRepository.save(driverJpa);

        return driver.getId();
    }
}

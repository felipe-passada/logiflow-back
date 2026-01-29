package br.com.felipepassada.logiflow.module.fleet.application;

import br.com.felipepassada.logiflow.module.fleet.infra.persistence.DriverMapper;
import br.com.felipepassada.logiflow.module.fleet.infra.persistence.DriverRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ToggleDriverAvailabilityUseCase {
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    public ToggleDriverAvailabilityUseCase(DriverRepository driverRepository, DriverMapper driverMapper) {
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
    }

    @Transactional
    public void execute(UUID id, boolean targetAvailability) {
        var driverEntity = driverRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Driver with id " + id + " not found"));

        var driver = driverMapper.toDomain(driverEntity);
        driver.toggleAvailability(targetAvailability);

        driverRepository.save(driverMapper.toJpa(driver));
    }
}

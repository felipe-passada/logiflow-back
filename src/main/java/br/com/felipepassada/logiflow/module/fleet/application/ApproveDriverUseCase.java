package br.com.felipepassada.logiflow.module.fleet.application;

import br.com.felipepassada.logiflow.module.fleet.domain.Driver;
import br.com.felipepassada.logiflow.module.fleet.infra.persistence.DriverMapper;
import br.com.felipepassada.logiflow.module.fleet.infra.persistence.DriverRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ApproveDriverUseCase {
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    public ApproveDriverUseCase(DriverRepository driverRepository, DriverMapper driverMapper) {
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
    }

    @Transactional
    public void execute(UUID driverId) {
        var driverEntity = driverRepository.findById(driverId)
                .orElseThrow(() -> new EntityNotFoundException("Driver with id" + driverId+ " not found"));

       Driver driver = driverMapper.toDomain(driverEntity);
       driver.approve();

        driverRepository.save(driverMapper.toJpa(driver));
    }
}

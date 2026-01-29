package br.com.felipepassada.logiflow.module.fleet.application;

import br.com.felipepassada.logiflow.module.fleet.api.dtos.response.DriverSummaryResponseDto;
import br.com.felipepassada.logiflow.module.fleet.domain.valueObjects.DriverStatus;
import br.com.felipepassada.logiflow.module.fleet.infra.persistence.DriverMapper;
import br.com.felipepassada.logiflow.module.fleet.infra.persistence.DriverRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListPendingDriversUseCase {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    public ListPendingDriversUseCase(DriverRepository driverRepository, DriverMapper driverMapper) {
        this.driverRepository = driverRepository;
        this.driverMapper = driverMapper;
    }

    public Page<DriverSummaryResponseDto> execute(Pageable page) {
        return driverRepository.findAllByStatus(DriverStatus.PENDING_APPROVAL, page)
                .map(jpa -> new DriverSummaryResponseDto(
                        jpa.getId(),
                        jpa.getFullName(),
                        jpa.getVehiclePlate(),
                        jpa.getStatus()
                ));
    }
}

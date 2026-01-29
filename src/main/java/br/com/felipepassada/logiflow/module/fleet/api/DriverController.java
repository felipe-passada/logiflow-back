package br.com.felipepassada.logiflow.module.fleet.api;

import br.com.felipepassada.logiflow.module.fleet.api.dtos.request.RegisterDriverRequestDto;
import br.com.felipepassada.logiflow.module.fleet.api.dtos.response.DriverSummaryResponseDto;
import br.com.felipepassada.logiflow.module.fleet.application.ApproveDriverUseCase;
import br.com.felipepassada.logiflow.module.fleet.application.ListPendingDriversUseCase;
import br.com.felipepassada.logiflow.module.fleet.application.RegisterDriverUseCase;
import br.com.felipepassada.logiflow.module.fleet.application.ToggleDriverAvailabilityUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
    private final RegisterDriverUseCase registerDriverUseCase;
    private final ListPendingDriversUseCase listPendingDriversUseCase;
    private final ApproveDriverUseCase approveDriverUseCase;
    private final ToggleDriverAvailabilityUseCase toggleDriverAvailabilityUseCase;

    public DriverController(RegisterDriverUseCase registerDriverUseCase, ListPendingDriversUseCase listPendingDriversUseCase, ApproveDriverUseCase approveDriverUseCase, ToggleDriverAvailabilityUseCase toggleDriverAvailabilityUseCase) {
        this.registerDriverUseCase = registerDriverUseCase;
        this.listPendingDriversUseCase = listPendingDriversUseCase;
        this.approveDriverUseCase = approveDriverUseCase;
        this.toggleDriverAvailabilityUseCase = toggleDriverAvailabilityUseCase;
    }

    @PostMapping
    public ResponseEntity<UUID> registerDriver(@RequestBody RegisterDriverRequestDto driverRequestDto) {
        UUID customerId = registerDriverUseCase.execute(
                driverRequestDto
        );
        return ResponseEntity.ok(customerId);
    }

    @GetMapping("/pending")
    public ResponseEntity<Page<DriverSummaryResponseDto>> getPending(
            @PageableDefault(size = 10, sort = "fullName")Pageable pageable) {

        return ResponseEntity.ok(listPendingDriversUseCase.execute(pageable));
    }

    @PatchMapping("{id}/approve")
    public ResponseEntity<Void> approveDriver(@PathVariable UUID id) {
        approveDriverUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}/toggle-availability")
    public ResponseEntity<Void> toggleAvailability(@PathVariable UUID id, @RequestParam  boolean targetAvailability) {
        toggleDriverAvailabilityUseCase.execute(id, targetAvailability);
        return ResponseEntity.noContent().build();
    }
}

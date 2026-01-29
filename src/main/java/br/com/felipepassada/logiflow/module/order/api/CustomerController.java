package br.com.felipepassada.logiflow.module.order.api;

import br.com.felipepassada.logiflow.module.order.api.dtos.request.RegisterCustomerRequestDto;
import br.com.felipepassada.logiflow.module.order.api.dtos.response.CustomerResponseDto;
import br.com.felipepassada.logiflow.module.order.application.GetCustomerProfileUseCase;
import br.com.felipepassada.logiflow.module.order.application.RegisterCustomerUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final RegisterCustomerUseCase registerCustomerUseCase;
    private final GetCustomerProfileUseCase getCustomerProfileUseCase;

    public CustomerController(RegisterCustomerUseCase registerCustomerUseCase, GetCustomerProfileUseCase getCustomerProfileUseCase) {
        this.registerCustomerUseCase = registerCustomerUseCase;
        this.getCustomerProfileUseCase = getCustomerProfileUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> registerCustomer(@RequestBody RegisterCustomerRequestDto customerRequestDto) {
        UUID customerId = registerCustomerUseCase.execute(customerRequestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customerId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable UUID id) {
        var response = getCustomerProfileUseCase.execute(id);
        return ResponseEntity.ok(response);
    }
}

package br.com.felipepassada.logiflow.module.order.application;

import br.com.felipepassada.logiflow.module.identity.application.GetUserByIdUseCase;
import br.com.felipepassada.logiflow.module.order.api.dtos.AddressDto;
import br.com.felipepassada.logiflow.module.order.api.dtos.response.CustomerResponseDto;
import br.com.felipepassada.logiflow.module.order.infra.persistence.CustomerMapper;
import br.com.felipepassada.logiflow.module.order.infra.persistence.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetCustomerProfileUseCase {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final GetUserByIdUseCase getUserByIdUseCase;

    public GetCustomerProfileUseCase(CustomerRepository customerRepository, CustomerMapper customerMapper, GetUserByIdUseCase getUserByIdUseCase) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.getUserByIdUseCase = getUserByIdUseCase;
    }

    public CustomerResponseDto execute(UUID customerId) {
        var customerEntityJpa = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        var customer = customerMapper.toDomain(customerEntityJpa);

        var userDto = getUserByIdUseCase.execute(customer.getUserId());

        return new CustomerResponseDto(
                customer.getId(),
                customer.getUserId(),
                userDto.email(),
                customer.getFullName(),
                customer.getDocument(),
                customer.getPhone(),
                userDto.isActive(),
                AddressDto.fromDomain(customer.getAddress())
        );
    }
}

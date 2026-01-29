package br.com.felipepassada.logiflow.module.order.application;

import br.com.felipepassada.logiflow.module.identity.application.RegisterUserUseCase;
import br.com.felipepassada.logiflow.module.identity.domain.Role;
import br.com.felipepassada.logiflow.module.order.api.dtos.AddressDto;
import br.com.felipepassada.logiflow.module.order.api.dtos.request.RegisterCustomerRequestDto;
import br.com.felipepassada.logiflow.module.order.domain.Customer;
import br.com.felipepassada.logiflow.module.order.infra.persistence.CustomerMapper;
import br.com.felipepassada.logiflow.module.order.infra.persistence.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RegisterCustomerUseCase {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final RegisterUserUseCase registerUserUseCase;

    public RegisterCustomerUseCase(CustomerRepository customerRepository,
                                   CustomerMapper customerMapper,
                                   RegisterUserUseCase registerUserUseCase) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.registerUserUseCase = registerUserUseCase;
    }

    @Transactional
    public UUID execute(RegisterCustomerRequestDto customerDto) {
        UUID userId = registerUserUseCase.execute(
                customerDto.email(),
                customerDto.password(),
                Role.CUSTOMER
        );

        var customer = new Customer(
                UUID.randomUUID(),
                userId,
                customerDto.fullName(),
                customerDto.document(),
                customerDto.phone(),
                AddressDto.toDomain(customerDto.address())
        );
        var customerJpa = customerMapper.toJpa(customer);
        customerRepository.save(customerJpa);

        return customer.getId();
    }
}

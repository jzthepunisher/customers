package pe.interbank.customers.service.impl;

import org.springframework.stereotype.Service;
import pe.interbank.customers.util.api.exceptions.InvalidInputException;
import pe.interbank.customers.controller.web.dto.request.CustomerWebDto;
import pe.interbank.customers.controller.web.dto.response.CustomerResponseWebDto;
import pe.interbank.customers.domain.Customer;
import pe.interbank.customers.mapper.CustomerMapper;
import pe.interbank.customers.repository.CustomersRepository;
import pe.interbank.customers.service.CustomersService;
import pe.interbank.customers.util.api.exceptions.NotFoundException;

@Service
public class CustomersServiceImpl implements CustomersService {

    private final CustomersRepository customersRepository;

    public CustomersServiceImpl(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    @Override
    public CustomerResponseWebDto save(CustomerWebDto customerWebDto) {
        Customer customer = this.customersRepository.find(customerWebDto.getUniqueCode());
        if(null == customer) {
            Customer customerDomain = this.customersRepository.save(CustomerMapper.requestToDomain(customerWebDto));
            CustomerResponseWebDto response = CustomerMapper.domainToResponse(customerDomain);
            return response;
        } else {
            throw new InvalidInputException("Customer already exists");
        }
    }

    @Override
    public CustomerResponseWebDto find(String uniqueCode) {
        Customer customerDomain = this.customersRepository.find(uniqueCode);
        if(null == customerDomain) {
            throw new NotFoundException("Customer not found");
        }
        return CustomerMapper.domainToResponse(customerDomain);
    }

}

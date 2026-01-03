package pe.interbank.customers.repository.impl;

import org.springframework.stereotype.Repository;
import pe.interbank.customers.domain.Customer;
import pe.interbank.customers.repository.CustomersRepository;
import pe.interbank.customers.repository.dao.entity.CustomerEntity;
import pe.interbank.customers.repository.dao.repository.CustomerRepository;
import pe.interbank.customers.repository.mapper.CustomerMapper;

import java.util.Optional;

@Repository
public class CustomersRepositoryImpl implements CustomersRepository {

    private final CustomerRepository customerRepository;

    public CustomersRepositoryImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity entitySaved = customerRepository.save(CustomerMapper.domainToEntity(customer));
        Customer domain = CustomerMapper.entityToDomain(entitySaved);
        return domain;
    }

    @Override
    public Customer find(String uniqueCode) {
        Optional<CustomerEntity> entityFinded = customerRepository.findByUniqueCode(uniqueCode);
        Customer customerDomain = CustomerMapper.entityToDomain(entityFinded.isPresent() ? entityFinded.get() : null);
        return customerDomain;
    }
}

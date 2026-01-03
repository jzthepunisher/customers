package pe.interbank.customers.repository;

import pe.interbank.customers.domain.Customer;
import pe.interbank.customers.repository.dao.entity.CustomerEntity;
import pe.interbank.customers.repository.dao.repository.CustomerRepository;
import pe.interbank.customers.repository.impl.CustomersRepositoryImpl;
import pe.interbank.customers.repository.mapper.CustomerMapper;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomersRepositoryImplTest {

    @Mock
    CustomerRepository customerRepository;

    @Test
    void save_should_map_entity_and_return_domain() {
        CustomersRepositoryImpl sut = new CustomersRepositoryImpl(customerRepository);

        Customer domainInput = mock(Customer.class);
        CustomerEntity entityMapped = mock(CustomerEntity.class);
        Customer domainResult = mock(Customer.class);
        CustomerEntity entitySaved = mock(CustomerEntity.class);

        try (MockedStatic<CustomerMapper> mocked = Mockito.mockStatic(CustomerMapper.class)) {
            mocked.when(() -> CustomerMapper.domainToEntity(any(Customer.class))).thenReturn(entityMapped);
            when(customerRepository.save(entityMapped)).thenReturn(entitySaved);
            mocked.when(() -> CustomerMapper.entityToDomain(entitySaved)).thenReturn(domainResult);

            Customer result = sut.save(domainInput);

            assertSame(domainResult, result);
            mocked.verify(() -> CustomerMapper.domainToEntity(domainInput));
            verify(customerRepository).save(entityMapped);
            mocked.verify(() -> CustomerMapper.entityToDomain(entitySaved));
        }
    }

    @Test
    void find_should_return_domain_when_entity_present() {
        CustomersRepositoryImpl sut = new CustomersRepositoryImpl(customerRepository);

        String uniqueCode = "unique-123";
        CustomerEntity foundEntity = mock(CustomerEntity.class);
        Customer domainResult = mock(Customer.class);

        try (MockedStatic<CustomerMapper> mocked = Mockito.mockStatic(CustomerMapper.class)) {
            when(customerRepository.findByUniqueCode(uniqueCode)).thenReturn(Optional.of(foundEntity));
            mocked.when(() -> CustomerMapper.entityToDomain(foundEntity)).thenReturn(domainResult);

            Customer result = sut.find(uniqueCode);

            assertSame(domainResult, result);
            verify(customerRepository).findByUniqueCode(uniqueCode);
            mocked.verify(() -> CustomerMapper.entityToDomain(foundEntity));
        }
    }

    @Test
    void find_should_return_null_when_entity_not_found() {
        CustomersRepositoryImpl sut = new CustomersRepositoryImpl(customerRepository);

        String uniqueCode = "not-found";

        try (MockedStatic<CustomerMapper> mocked = Mockito.mockStatic(CustomerMapper.class)) {
            when(customerRepository.findByUniqueCode(uniqueCode)).thenReturn(Optional.empty());
            mocked.when(() -> CustomerMapper.entityToDomain((CustomerEntity) null)).thenReturn(null);

            Customer result = sut.find(uniqueCode);

            assertNull(result);
            verify(customerRepository).findByUniqueCode(uniqueCode);
            mocked.verify(() -> CustomerMapper.entityToDomain((CustomerEntity) null));
        }
    }
}
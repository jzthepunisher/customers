package pe.interbank.customers.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.interbank.customers.controller.web.dto.request.CustomerWebDto;
import pe.interbank.customers.controller.web.dto.response.CustomerResponseWebDto;
import pe.interbank.customers.domain.Customer;
import pe.interbank.customers.mapper.CustomerMapper;
import pe.interbank.customers.repository.CustomersRepository;
import pe.interbank.customers.util.api.exceptions.InvalidInputException;
import pe.interbank.customers.util.api.exceptions.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomersServiceImplTest {

    @Mock
    CustomersRepository customersRepository;

    @Test
    void save_should_save_and_return_response_when_not_exists() {
        CustomersServiceImpl sut = new CustomersServiceImpl(customersRepository);

        String uniqueCode = "unique-1";
        CustomerWebDto request = mock(CustomerWebDto.class);
        when(request.getUniqueCode()).thenReturn(uniqueCode);

        Customer domainMapped = mock(Customer.class);
        Customer savedDomain = mock(Customer.class);
        CustomerResponseWebDto responseDto = mock(CustomerResponseWebDto.class);

        when(customersRepository.find(uniqueCode)).thenReturn(null);

        try (MockedStatic<CustomerMapper> mocked = Mockito.mockStatic(CustomerMapper.class)) {
            mocked.when(() -> CustomerMapper.requestToDomain(request)).thenReturn(domainMapped);
            when(customersRepository.save(domainMapped)).thenReturn(savedDomain);
            mocked.when(() -> CustomerMapper.domainToResponse(savedDomain)).thenReturn(responseDto);

            CustomerResponseWebDto result = sut.save(request);

            assertSame(responseDto, result);
            mocked.verify(() -> CustomerMapper.requestToDomain(request));
            verify(customersRepository).save(domainMapped);
            mocked.verify(() -> CustomerMapper.domainToResponse(savedDomain));
        }
    }

    @Test
    void save_should_throw_InvalidInputException_when_already_exists() {
        CustomersServiceImpl sut = new CustomersServiceImpl(customersRepository);

        String uniqueCode = "exists-1";
        CustomerWebDto request = mock(CustomerWebDto.class);
        when(request.getUniqueCode()).thenReturn(uniqueCode);

        Customer existing = mock(Customer.class);
        when(customersRepository.find(uniqueCode)).thenReturn(existing);

        try (MockedStatic<CustomerMapper> mocked = Mockito.mockStatic(CustomerMapper.class)) {
            assertThrows(InvalidInputException.class, () -> sut.save(request));
            verify(customersRepository).find(uniqueCode);
            // asegurarse de que no se intentó mapear ni guardar
            mocked.verifyNoInteractions();
            verify(customersRepository, never()).save(any());
        }
    }

    @Test
    void find_should_return_response_when_found() {
        CustomersServiceImpl sut = new CustomersServiceImpl(customersRepository);

        String uniqueCode = "find-1";
        Customer foundDomain = mock(Customer.class);
        CustomerResponseWebDto responseDto = mock(CustomerResponseWebDto.class);

        when(customersRepository.find(uniqueCode)).thenReturn(foundDomain);

        try (MockedStatic<CustomerMapper> mocked = Mockito.mockStatic(CustomerMapper.class)) {
            mocked.when(() -> CustomerMapper.domainToResponse(foundDomain)).thenReturn(responseDto);

            CustomerResponseWebDto result = sut.find(uniqueCode);

            assertSame(responseDto, result);
            verify(customersRepository).find(uniqueCode);
            mocked.verify(() -> CustomerMapper.domainToResponse(foundDomain));
        }
    }

    @Test
    void find_should_throw_NotFoundException_when_not_found() {
        CustomersServiceImpl sut = new CustomersServiceImpl(customersRepository);

        String uniqueCode = "missing-1";
        when(customersRepository.find(uniqueCode)).thenReturn(null);

        try (MockedStatic<CustomerMapper> mocked = Mockito.mockStatic(CustomerMapper.class)) {
            assertThrows(NotFoundException.class, () -> sut.find(uniqueCode));
            verify(customersRepository).find(uniqueCode);
            mocked.verifyNoInteractions();
        }
    }
}

package pe.interbank.customers.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.interbank.customers.controller.web.dto.request.TransactionWebDto;
import pe.interbank.customers.controller.web.dto.response.TransactionResponseWebDto;
import pe.interbank.customers.domain.Customer;
import pe.interbank.customers.domain.Transaction;
import pe.interbank.customers.mapper.TransactionMapper;
import pe.interbank.customers.repository.CustomersRepository;
import pe.interbank.customers.repository.TransactionRepository;
import pe.interbank.customers.util.api.exceptions.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock
    CustomersRepository customersRepository;

    @Mock
    TransactionRepository transactionRepository;

    @Test
    void save_should_save_and_return_response_when_customer_exists() {
        TransactionServiceImpl sut = new TransactionServiceImpl(customersRepository, transactionRepository);

        String uniqueCode = "unique-1";
        TransactionWebDto request = mock(TransactionWebDto.class);
        when(request.getUniqueCode()).thenReturn(uniqueCode);

        Customer existingCustomer = mock(Customer.class);
        when(customersRepository.find(uniqueCode)).thenReturn(existingCustomer);

        Transaction domainMapped = mock(Transaction.class);
        Transaction savedDomain = mock(Transaction.class);
        TransactionResponseWebDto responseDto = mock(TransactionResponseWebDto.class);

        try (MockedStatic<TransactionMapper> mocked = Mockito.mockStatic(TransactionMapper.class)) {
            mocked.when(() -> TransactionMapper.requestToDomain(request)).thenReturn(domainMapped);
            when(transactionRepository.save(domainMapped)).thenReturn(savedDomain);
            mocked.when(() -> TransactionMapper.domainToResponse(savedDomain)).thenReturn(responseDto);

            TransactionResponseWebDto result = sut.save(request);

            assertSame(responseDto, result);
            verify(customersRepository).find(uniqueCode);
            mocked.verify(() -> TransactionMapper.requestToDomain(request));
            verify(transactionRepository).save(domainMapped);
            mocked.verify(() -> TransactionMapper.domainToResponse(savedDomain));
        }
    }

    @Test
    void save_should_throw_NotFoundException_when_customer_not_found() {
        TransactionServiceImpl sut = new TransactionServiceImpl(customersRepository, transactionRepository);

        String uniqueCode = "missing-1";
        TransactionWebDto request = mock(TransactionWebDto.class);
        when(request.getUniqueCode()).thenReturn(uniqueCode);

        when(customersRepository.find(uniqueCode)).thenReturn(null);

        try (MockedStatic<TransactionMapper> mocked = Mockito.mockStatic(TransactionMapper.class)) {
            assertThrows(NotFoundException.class, () -> sut.save(request));
            verify(customersRepository).find(uniqueCode);
            mocked.verifyNoInteractions();
            verify(transactionRepository, never()).save(any());
        }
    }
}

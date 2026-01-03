package pe.interbank.customers.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.interbank.customers.domain.Transaction;
import pe.interbank.customers.repository.dao.entity.TransactionEntity;
import pe.interbank.customers.repository.dao.repository.TransactionDaoRepository;
import pe.interbank.customers.repository.impl.TransactionRepositoryImpl;
import pe.interbank.customers.repository.mapper.TransactionMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionRepositoryImplTest {

    @Mock
    TransactionDaoRepository transactionDaoRepository;

    @Mock
    TransactionRepository transactionRepository;

    @Test
    void save_should_map_entity_and_return_domain() {
        TransactionRepositoryImpl sut = new TransactionRepositoryImpl(transactionDaoRepository);

        Transaction domainInput = mock(Transaction.class);
        TransactionEntity entityMapped = mock(TransactionEntity.class);
        Transaction domainResult = mock(Transaction.class);
        TransactionEntity entitySaved = mock(TransactionEntity.class);

        try (MockedStatic<TransactionMapper> mocked = Mockito.mockStatic(TransactionMapper.class)) {
            mocked.when(() -> TransactionMapper.domainToEntity(any(Transaction.class))).thenReturn(entityMapped);
            when(transactionDaoRepository.save(entityMapped)).thenReturn(entitySaved);
            mocked.when(() -> TransactionMapper.entityToDomain(entitySaved)).thenReturn(domainResult);

            Transaction result = sut.save(domainInput);

            assertSame(domainResult, result);
            mocked.verify(() -> TransactionMapper.domainToEntity(domainInput));
            verify(transactionDaoRepository).save(entityMapped);
            mocked.verify(() -> TransactionMapper.entityToDomain(entitySaved));
        }
    }
}

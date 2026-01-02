package pe.interbank.customers.repository.impl;

import org.springframework.stereotype.Repository;
import pe.interbank.customers.domain.Customer;
import pe.interbank.customers.domain.Transaction;

import pe.interbank.customers.repository.TransactionRepository;
import pe.interbank.customers.repository.dao.entity.TransactionEntity;
import pe.interbank.customers.repository.dao.repository.TransactionDaoRepository;
import pe.interbank.customers.repository.mapper.CustomerMapper;
import pe.interbank.customers.repository.mapper.TransactionMapper;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

    private final TransactionDaoRepository transactionDaoRepository;

    public TransactionRepositoryImpl(TransactionDaoRepository transactionDaoRepository) {
        this.transactionDaoRepository = transactionDaoRepository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity entitySaved = this.transactionDaoRepository.save(TransactionMapper.domainToEntity(transaction));
        Transaction domain = TransactionMapper.entityToDomain(entitySaved);
        return domain;
    }

}

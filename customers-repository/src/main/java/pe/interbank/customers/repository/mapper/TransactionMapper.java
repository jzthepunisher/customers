package pe.interbank.customers.repository.mapper;

import pe.interbank.customers.domain.Customer;
import pe.interbank.customers.domain.Transaction;
import pe.interbank.customers.repository.dao.entity.CustomerEntity;
import pe.interbank.customers.repository.dao.entity.TransactionEntity;

import java.time.LocalDateTime;

public class TransactionMapper {
    public static TransactionEntity domainToEntity(Transaction domain) {
        TransactionEntity entity = new TransactionEntity();
        entity.setId(domain.getId());
        entity.setUniqueCode(domain.getUniqueCode());
        entity.setPaymentBehavior(domain.getPaymentBehavior());
        entity.setAmount(domain.getAmount());
        entity.setTransactionDate(LocalDateTime.now());
        return entity;
    }

    public static Transaction entityToDomain(TransactionEntity entity) {
        if(null != entity) {
            Transaction domain = new Transaction();
            domain.setId(entity.getId());
            domain.setUniqueCode(entity.getUniqueCode());
            domain.setPaymentBehavior(entity.getPaymentBehavior());
            domain.setAmount(entity.getAmount());
            domain.setTransactionDate(entity.getTransactionDate());
            return domain;
        } else {
            return null;
        }
    }
}

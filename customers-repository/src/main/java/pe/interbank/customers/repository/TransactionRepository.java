package pe.interbank.customers.repository;

import pe.interbank.customers.domain.Transaction;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
}

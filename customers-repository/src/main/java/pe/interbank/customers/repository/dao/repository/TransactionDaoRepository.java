package pe.interbank.customers.repository.dao.repository;

import org.springframework.data.repository.CrudRepository;
import pe.interbank.customers.repository.dao.entity.TransactionEntity;

public interface TransactionDaoRepository extends CrudRepository<TransactionEntity, Long> {
}

package pe.interbank.customers.repository.dao.repository;

import org.springframework.data.repository.CrudRepository;
import pe.interbank.customers.repository.dao.entity.CustomerEntity;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    CustomerEntity findByUniqueCode(String uniqueCode); //
}

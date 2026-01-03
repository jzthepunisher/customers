package pe.interbank.customers.repository.dao.repository;

import org.springframework.data.repository.CrudRepository;
import pe.interbank.customers.repository.dao.entity.CustomerEntity;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByUniqueCode(String uniqueCode); //
}

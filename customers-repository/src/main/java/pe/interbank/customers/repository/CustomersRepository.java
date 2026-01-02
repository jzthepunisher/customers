package pe.interbank.customers.repository;

import pe.interbank.customers.domain.Customer;

public interface CustomersRepository {
     Customer save(Customer customer);
     Customer find(String uniqueCode);
}

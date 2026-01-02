package pe.interbank.customers.repository.mapper;

import pe.interbank.customers.domain.Customer;
import pe.interbank.customers.repository.dao.entity.CustomerEntity;

public class CustomerMapper {
    public static CustomerEntity domainToEntity(Customer domain) {
        CustomerEntity entity = new CustomerEntity();
        entity.setUniqueCode(domain.getUniqueCode());
        entity.setNames(domain.getNames());
        entity.setLastNames(domain.getLastNames());
        entity.setDocumentType(domain.getDocumentType());
        entity.setDocumentNumber(domain.getDocumentNumber());
        return entity;
    }

    public static Customer entityToDomain(CustomerEntity entity) {
        if(null != entity) {
            Customer domain = new Customer();
            domain.setId(entity.getId());
            domain.setUniqueCode(entity.getUniqueCode());
            domain.setNames(entity.getNames());
            domain.setLastNames(entity.getLastNames());
            domain.setDocumentType(entity.getDocumentType());
            domain.setDocumentNumber(entity.getDocumentNumber());
            return domain;
        } else {
            return null;
        }
    }
}

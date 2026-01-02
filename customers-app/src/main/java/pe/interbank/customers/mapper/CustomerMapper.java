package pe.interbank.customers.mapper;

import pe.interbank.customers.controller.web.dto.request.CustomerWebDto;
import pe.interbank.customers.controller.web.dto.response.CustomerResponseWebDto;
import pe.interbank.customers.domain.Customer;

public class CustomerMapper {
    public static Customer requestToDomain(CustomerWebDto request) {
        Customer customer = new Customer();
        customer.setUniqueCode(request.getUniqueCode());
        customer.setNames(request.getNames());
        customer.setLastNames(request.getLastNames());
        customer.setDocumentType(request.getDocumentType());
        customer.setDocumentNumber(request.getDocumentNumber());
        return customer;
    }

    public static CustomerResponseWebDto domainToResponse(Customer domain) {
        CustomerResponseWebDto response = new CustomerResponseWebDto();
        response.setId(domain.getId());
        response.setUniqueCode(domain.getUniqueCode());
        response.setNames(domain.getNames());
        response.setLastNames(domain.getLastNames());
        response.setDocumentType(domain.getDocumentType());
        response.setDocumentNumber(domain.getDocumentNumber());
        return response;
    }
}

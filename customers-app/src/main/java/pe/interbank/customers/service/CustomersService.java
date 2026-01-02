package pe.interbank.customers.service;

import pe.interbank.customers.controller.web.dto.request.CustomerWebDto;
import pe.interbank.customers.controller.web.dto.response.CustomerResponseWebDto;

public interface CustomersService {
    CustomerResponseWebDto save(CustomerWebDto customerWebDto);
    CustomerResponseWebDto find(String uniqueCode);
}

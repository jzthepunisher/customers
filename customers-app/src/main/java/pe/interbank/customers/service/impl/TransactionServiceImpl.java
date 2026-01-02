package pe.interbank.customers.service.impl;

import org.springframework.stereotype.Service;
import pe.interbank.customers.controller.web.dto.request.TransactionWebDto;
import pe.interbank.customers.controller.web.dto.response.CustomerResponseWebDto;
import pe.interbank.customers.controller.web.dto.response.TransactionResponseWebDto;
import pe.interbank.customers.domain.Customer;
import pe.interbank.customers.domain.Transaction;
import pe.interbank.customers.mapper.CustomerMapper;
import pe.interbank.customers.mapper.TransactionMapper;
import pe.interbank.customers.repository.CustomersRepository;
import pe.interbank.customers.repository.TransactionRepository;
import pe.interbank.customers.service.TransactionService;
import pe.interbank.customers.util.api.exceptions.InvalidInputException;
import pe.interbank.customers.util.api.exceptions.NotFoundException;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final CustomersRepository customersRepository;
    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(CustomersRepository customersRepository, TransactionRepository transactionRepository) {
        this.customersRepository = customersRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionResponseWebDto save(TransactionWebDto transactionWebDto) {
        Customer customer = this.customersRepository.find(transactionWebDto.getUniqueCode());
        if(null != customer) {
            Transaction transactionDomain = this.transactionRepository.save(TransactionMapper.requestToDomain(transactionWebDto));
            TransactionResponseWebDto response = TransactionMapper.domainToResponse(transactionDomain);
            return response;
        } else {
            throw new NotFoundException("Customer does not exist");
        }
    }

}

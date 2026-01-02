package pe.interbank.customers.service;

import pe.interbank.customers.controller.web.dto.request.TransactionWebDto;
import pe.interbank.customers.controller.web.dto.response.TransactionResponseWebDto;

public interface TransactionService {
    TransactionResponseWebDto save(TransactionWebDto transactionWebDto);

}

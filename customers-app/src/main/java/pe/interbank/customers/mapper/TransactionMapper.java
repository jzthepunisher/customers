package pe.interbank.customers.mapper;

import pe.interbank.customers.controller.web.dto.request.TransactionWebDto;
import pe.interbank.customers.controller.web.dto.response.CustomerResponseWebDto;
import pe.interbank.customers.controller.web.dto.response.TransactionResponseWebDto;
import pe.interbank.customers.domain.Customer;
import pe.interbank.customers.domain.Transaction;

public class TransactionMapper {
    public static Transaction requestToDomain(TransactionWebDto request) {
        Transaction transaction = new Transaction();
        transaction.setUniqueCode(request.getUniqueCode());
        transaction.setPaymentBehavior(request.getPaymentBehavior().getName());
        transaction.setAmount(request.getAmount());
        return transaction;
    }

    public static TransactionResponseWebDto domainToResponse(Transaction domain) {
        TransactionResponseWebDto response = new TransactionResponseWebDto();
        response.setId(domain.getId());
        response.setUniqueCode(domain.getUniqueCode());
        response.setPaymentBehavior(domain.getPaymentBehavior());
        response.setAmount(domain.getAmount());
        response.setTransactionDate(domain.getTransactionDate().toString());
        return response;
    }
}

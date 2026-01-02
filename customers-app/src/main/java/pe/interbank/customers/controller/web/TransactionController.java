package pe.interbank.customers.controller.web;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.interbank.customers.controller.web.dto.request.TransactionWebDto;
import pe.interbank.customers.controller.web.dto.response.TransactionResponseWebDto;
import pe.interbank.customers.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(path = "/")
    public HttpEntity<TransactionResponseWebDto> doPost(@RequestBody @Validated TransactionWebDto transactionWebDto) {
        TransactionResponseWebDto bodyResponse = this.transactionService.save(transactionWebDto);
        return new ResponseEntity<>(bodyResponse, HttpStatus.OK);
    }
}

package pe.interbank.customers.controller.web;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.interbank.customers.controller.web.dto.request.CustomerWebDto;
import pe.interbank.customers.controller.web.dto.response.CustomerResponseWebDto;
import pe.interbank.customers.service.CustomersService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/customers")
@Validated
public class CustomersController {
    private static final String PARAM_UNIQUE_CODE = "uniqueCode";

    private final CustomersService customersService;

    public CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @PostMapping(path = "/")
    public HttpEntity<CustomerResponseWebDto> doPost(@RequestBody @Validated CustomerWebDto customerWebDto) {
        CustomerResponseWebDto bodyResponse = this.customersService.save(customerWebDto);
        return new ResponseEntity<>(bodyResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/{uniqueCode}")
    public HttpEntity<CustomerResponseWebDto> doGet(@PathVariable(name = PARAM_UNIQUE_CODE) @NotNull String uniqueCode) {
        CustomerResponseWebDto response = customersService.find(uniqueCode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
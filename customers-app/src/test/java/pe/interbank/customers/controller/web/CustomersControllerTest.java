package pe.interbank.customers.controller.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pe.interbank.customers.controller.web.dto.request.CustomerWebDto;
import pe.interbank.customers.controller.web.dto.response.CustomerResponseWebDto;
import pe.interbank.customers.service.CustomersService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomersControllerTest {

    @Mock
    CustomersService customersService;

    @Test
    void doPost_should_return_response_and_status_ok() {
        CustomersController sut = new CustomersController(customersService);

        CustomerWebDto request = mock(CustomerWebDto.class);
        CustomerResponseWebDto responseDto = mock(CustomerResponseWebDto.class);

        when(customersService.save(request)).thenReturn(responseDto);

        HttpEntity<CustomerResponseWebDto> result = sut.doPost(request);

        assertSame(responseDto, result.getBody());
        assertEquals(HttpStatus.OK, ((ResponseEntity<?>) result).getStatusCode());
        verify(customersService).save(request);
    }

    @Test
    void doGet_should_return_response_and_status_ok() {
        CustomersController sut = new CustomersController(customersService);

        String uniqueCode = "unique-123";
        CustomerResponseWebDto responseDto = mock(CustomerResponseWebDto.class);

        when(customersService.find(uniqueCode)).thenReturn(responseDto);

        HttpEntity<CustomerResponseWebDto> result = sut.doGet(uniqueCode);

        assertSame(responseDto, result.getBody());
        assertEquals(HttpStatus.OK, ((ResponseEntity<?>) result).getStatusCode());
        verify(customersService).find(uniqueCode);
    }
}
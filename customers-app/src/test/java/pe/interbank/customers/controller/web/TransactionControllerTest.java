package pe.interbank.customers.controller.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pe.interbank.customers.controller.web.dto.request.TransactionWebDto;
import pe.interbank.customers.controller.web.dto.response.TransactionResponseWebDto;
import pe.interbank.customers.service.TransactionService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @Mock
    TransactionService transactionService;

    @Test
    void doPost_should_return_response_and_status_ok() {
        TransactionController sut = new TransactionController(transactionService);

        TransactionWebDto request = mock(TransactionWebDto.class);
        TransactionResponseWebDto responseDto = mock(TransactionResponseWebDto.class);

        when(transactionService.save(request)).thenReturn(responseDto);

        HttpEntity<TransactionResponseWebDto> result = sut.doPost(request);

        assertSame(responseDto, result.getBody());
        assertEquals(HttpStatus.OK, ((ResponseEntity<?>) result).getStatusCode());
        verify(transactionService).save(request);
    }
}
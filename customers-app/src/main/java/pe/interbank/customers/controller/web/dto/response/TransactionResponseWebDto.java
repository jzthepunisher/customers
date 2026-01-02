package pe.interbank.customers.controller.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.interbank.customers.controller.web.dto.request.CustomerPaymentBehavior;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseWebDto implements Serializable {
    private static final long serialVersionUID = -7106158726425481442L;
    private Long id;
    private String uniqueCode;
    private String paymentBehavior;
    private Double amount;
    private String transactionDate;
}

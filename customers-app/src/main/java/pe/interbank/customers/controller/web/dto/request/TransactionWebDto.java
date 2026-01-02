package pe.interbank.customers.controller.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionWebDto implements Serializable {
    private static final long serialVersionUID = -7106158726425481442L;
    @NotNull
    @Size(min = 10, max = 10, message = "Unique code must be 10 digits")
    private String uniqueCode;
    @NotNull
    private CustomerPaymentBehavior paymentBehavior;
    @NotNull
    private double amount;
}


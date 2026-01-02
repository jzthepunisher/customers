package pe.interbank.customers.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Transaction implements Serializable {
    private static final long serialVersionUID = -6742104428183327682L;

    private Long id;
    private String uniqueCode;
    private String paymentBehavior;
    private Double amount;
    private LocalDateTime transactionDate;
}

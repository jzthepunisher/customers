package pe.interbank.customers.repository.dao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String uniqueCode;
    private String paymentBehavior;
    private Double amount;
    private LocalDateTime transactionDate;
}

package pe.interbank.customers.repository.dao.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String uniqueCode;
    private String names;
    private String lastNames;
    private String documentType;
    private String documentNumber;
}

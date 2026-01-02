package pe.interbank.customers.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class Customer implements Serializable {
    private static final long serialVersionUID = -6742104428183327682L;

    private Long id;
    private String uniqueCode;
    private String names;
    private String lastNames;
    private String documentType;
    private String documentNumber;
}

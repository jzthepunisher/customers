package pe.interbank.customers.controller.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponseWebDto implements Serializable {
    private static final long serialVersionUID = -7106158726425481442L;
    private Long id;
    private String uniqueCode;
    private String names;
    private String lastNames;
    private String documentType;
    private String documentNumber;
}

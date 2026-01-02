package pe.interbank.customers.controller.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerWebDto implements Serializable {
    private static final long serialVersionUID = -7106158726425481442L;
    @NotNull
    @Size(min = 10, max = 10, message = "Unique code must be 10 digits")
    private String uniqueCode;
    @NotNull
    private String names;
    @NotNull
    private String lastNames;
    @NotNull
    private CustomerDocumentType documentType;
    @NotNull
    private String documentNumber;
}

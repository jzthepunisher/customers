package pe.interbank.customers.controller.web.dto.request;

public enum CustomerDocumentType {
    DNI("DNI"),
    PASSPORT("PASSPORT");

    private final String name;

    CustomerDocumentType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

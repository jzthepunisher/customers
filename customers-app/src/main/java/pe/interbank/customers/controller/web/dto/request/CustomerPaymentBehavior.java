package pe.interbank.customers.controller.web.dto.request;

public enum CustomerPaymentBehavior {
    CASH("CASH"),
    CREDIT_CARD_DIRECT("CREDIT_CARD_DIRECT"),
    CREDIT_CARD_QUOTAS("CREDIT_CARD_QUOTAS");

    private final String name;

    CustomerPaymentBehavior(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

package main.model;

public enum OfferState {
    APERTA("APERTA"),
    RITIRATA("RITIRATA"),
    ACCOPPIATA("ACCOPPIATA"),
    SELEZIONATA("SELEZIONATA"),
    IN_SCAMBIO("IN SCAMBIO"),
    CHIUSA("CHIUSA");

    private String state;

    OfferState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}

package testeos.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client {
    private final StringProperty payeeAddress;

    public Client(String pAddress) {
        this.payeeAddress = new SimpleStringProperty(this, "Payee Address", pAddress);
    }

    public StringProperty pAddressProperty() {
        return payeeAddress;
    }

}

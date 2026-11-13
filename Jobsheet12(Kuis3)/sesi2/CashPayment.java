package sesi2;

public class CashPayment implements PaymentMethod {
    @Override
    public String getName() {
        return "Tunai";
    }

    @Override
    public int charge(int amount) {
        return amount;
    }
}


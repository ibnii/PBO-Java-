package sesi2;

public class BankTransferPayment implements PaymentMethod {
    @Override
    public String getName() {
        return "Transfer Bank";
    }

    @Override
    public int charge(int amount) {
        return amount + 2500;
    }
}


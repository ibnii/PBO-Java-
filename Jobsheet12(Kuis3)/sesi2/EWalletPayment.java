package sesi2;

public class EWalletPayment implements PaymentMethod {
    @Override
    public String getName() {
        return "E-Wallet";
    }

    @Override
    public int charge(int amount) {
        return amount + 1000;
    }
}


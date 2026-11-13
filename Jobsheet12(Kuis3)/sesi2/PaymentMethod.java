package sesi2;

public interface PaymentMethod {
    String getName();
    int charge(int amount);
}


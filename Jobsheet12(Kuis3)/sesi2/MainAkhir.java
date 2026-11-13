package sesi2;

public class MainAkhir {
    public static void main(String[] args) {
        Vehicle[] items = {
                new Motorcycle("N 1001 AA", 3),
                new Car("N 2002 BB", 2, 200000000),
                new Bus("N 3003 CC", 1),
                new Minibus("N 4005 DD", 2)
        };

        PaymentMethod[] methods = {
                new CashPayment(), new EWalletPayment(), new BankTransferPayment()
        };

        RentalCounter first = new RentalCounter(methods[0]);
        for (Vehicle item : items) {
            first.addVehicle(item);
        }
        new ReceiptPrinter().print(first);
        for (Vehicle item : items) {
            System.out.println("Diskon member " + item.getPlate() + ": Rp" + first.getMemberDiscount(item));
        }

        for (PaymentMethod m : methods) {
            RentalCounter counter = new RentalCounter(m);
            for (Vehicle item : items) {
                counter.addVehicle(item);
            }
            System.out.println(m.getName() + ": Rp" + counter.checkout());
        }
    }
}

package sesi2;

public class ReceiptPrinter {
    public void print(RentalCounter counter) {
        System.out.println("=== Struk Rental ===");
        for (int i = 0; i < counter.getCount(); i++) {
            System.out.println(counter.getItem(i).getInfo());
        }
        System.out.println("Total: Rp" + counter.getTotalCost());
    }
}


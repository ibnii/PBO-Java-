package sesi2;

public class RentalCounter {
    private Vehicle[] items = new Vehicle[10];
    private int count = 0;
    private PaymentMethod paymentMethod;

    public RentalCounter() {
    }

    public RentalCounter(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void addVehicle(Vehicle item) {
        items[count] = item;
        count++;
    }

    public int getCount() {
        return count;
    }

    public Vehicle getItem(int index) {
        return items[index];
    }

    public int getTotalCost() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += items[i].calculateCost();
        }
        return total;
    }

    public int checkout() {
        return paymentMethod.charge(getTotalCost());
    }

    public int getMemberDiscount(Vehicle item) {
        return item.calculateCost() * item.getDiscountPercentage() / 100;
    }
}

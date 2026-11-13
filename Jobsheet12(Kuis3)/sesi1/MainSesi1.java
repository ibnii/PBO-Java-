package sesi1;

public class MainSesi1 {
    public static void main(String[] args) {
        RentalCounter counter = new RentalCounter();
        counter.addVehicle(new Motorcycle("N 1001 AA", 3));
        counter.addVehicle(new Car("N 2002 BB", 2, 200000000));
        counter.addVehicle(new Bus("N 3003 CC", 1));
        counter.addVehicle(new Bus("N 3004 CC", 4));

        counter.printAll();
        System.out.println("Total biaya sewa: Rp" + counter.getTotalCost());
        counter.printLocationReport();
        System.out.println("Total biaya asuransi: Rp" + counter.getTotalInsurance());
    }
}

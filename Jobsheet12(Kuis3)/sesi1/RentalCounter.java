package sesi1;

public class RentalCounter {
    private Vehicle[] items = new Vehicle[10];
    private int count = 0;

    public void addVehicle(Vehicle item) {
        items[count] = item;
        count++;
    }

    public int getTotalCost() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += items[i].calculateCost();
        }
        return total;
    }

    public void printAll() {
        System.out.println("=== Daftar Kendaraan ===");
        for (int i = 0; i < count; i++) {
            System.out.println(items[i].getInfo());
        }
    }

    public void printLocationReport() {
        System.out.println("=== Laporan Posisi GPS ===");
        for (int i = 0; i < count; i++) {
            if (items[i] instanceof GpsTracked) {
                GpsTracked tracked = (GpsTracked) items[i];
                System.out.println(tracked.getLocationStatus());
            }
        }
    }

    public int getTotalInsurance() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            if (items[i] instanceof Insurable) {
                Insurable insurable = (Insurable) items[i];
                total += insurable.getInsuranceFee();
            }
        }
        return total;
    }
}

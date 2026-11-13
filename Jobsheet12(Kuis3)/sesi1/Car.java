package sesi1;

public class Car extends Vehicle implements GpsTracked, Insurable {
    private int vehicleValue;

    public Car(String plate, int days, int vehicleValue) {
        super(plate, days);
        this.vehicleValue = vehicleValue;
    }

    @Override
    public int calculateCost() {
        return days * 350000 + 50000;
    }

    @Override
    public String getLocationStatus() {
        return plate + ": posisi terpantau GPS (mobil)";
    }

    @Override
    public int getInsuranceFee() {
        return vehicleValue / 100; // asuransi 1% dari nilai
    }

    @Override
    public String getInfo() {
        return "[Mobil] " + super.getInfo();
    }
}

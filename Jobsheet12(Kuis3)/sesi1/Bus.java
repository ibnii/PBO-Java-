package sesi1;

public class Bus extends Vehicle implements GpsTracked {
    public Bus(String plate, int days) {
        super(plate, days);
    }

    @Override
    public int calculateCost() {
        int fee = days * 900000;
        return Math.max(fee, 1800000); // ada biaya minimum
    }

    @Override
    public String getLocationStatus() {
        return plate + ": posisi terpantau GPS (bus)";
    }

    @Override
    public String getInfo() {
        return "[Bus] " + super.getInfo();
    }
}

package sesi2;

public class Minibus extends Vehicle implements GpsTracked {
    public Minibus(String plate, int days) {
        super(plate, days);
    }

    @Override
    public int calculateCost() {
        return days * 600000 + 100000;
    }

    @Override
    public String getLocationStatus() {
        return plate + ": posisi terpantau GPS (minibus)";
    }

    @Override
    public int getDiscountPercentage() {
        return 15;
    }

    @Override
    public String getInfo() {
        return "[Minibus] " + super.getInfo();
    }
}


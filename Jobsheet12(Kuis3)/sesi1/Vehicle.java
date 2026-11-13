package sesi1;

public abstract class Vehicle {
    protected String plate;
    protected int days;

    public Vehicle(String plate, int days) {
        this.plate = plate;
        this.days = days;
    }

    public String getPlate() {
        return plate;
    }

    public int getDays() {
        return days;
    }

    public abstract int calculateCost();

    public String getInfo() {
        return plate + " | " + days + " hari | Biaya: Rp" + calculateCost();
    }
}

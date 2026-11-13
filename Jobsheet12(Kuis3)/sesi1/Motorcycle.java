package sesi1;

public class Motorcycle extends Vehicle {
    public Motorcycle(String plate, int days) {
        super(plate, days);
    }

    @Override
    public int calculateCost() {
        return days * 60000;
    }

    @Override
    public String getInfo() {
        return "[Motor] " + super.getInfo();
    }
}

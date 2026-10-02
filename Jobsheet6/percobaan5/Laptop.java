package percobaan5;

public class Laptop extends Komputer {
    protected int resolusiLayar;

    public Laptop(String merk, int memory, int cpu, int resolusi) {
        super(merk, memory, cpu);
        this.resolusiLayar = resolusi;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Resolusi Layar : " + resolusiLayar + "p");
    }
}

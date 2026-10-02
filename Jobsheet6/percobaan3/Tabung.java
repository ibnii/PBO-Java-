package percobaan3;

public class Tabung extends Bangun {
    protected int t;
    protected int r = 5;

    public void setSuperPhi(double phi) {
        super.phi = phi;
    }

    public void setSuperR(int r) {
        super.r = r;
    }

    public void setT(int t) {
        this.t = t;
    }

    public void volume() {
        System.out.println("Volume Tabung adalah: "
                + (super.phi * super.r * super.r * this.t));
    }

    public void cekR() {
        System.out.println("r = " + r);
        System.out.println("this.r = " + this.r);
        System.out.println("super.r = " + super.r);
    }
}

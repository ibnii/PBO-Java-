public class Penyihir extends Karakter {
    private int mana;

    public Penyihir(String nama, int hp, int dayaDasar, int mana) {
        super(nama, hp, dayaDasar);
        this.mana = mana;
    }

    @Override
    public int hitungDamage() {
        int damage = super.hitungDamage();
        if (this.mana >= 10) {
            this.mana = this.mana - 10;
            return damage * 2;
        } else {
            return damage / 2;
        }
    }

    public int getMana() {
        return this.mana;
    }

    @Override
    public String info() {
        return super.info() + " [Penyihir, mana=" + this.mana + "]";
    }
}

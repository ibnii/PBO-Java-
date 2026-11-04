public class Karakter {
    private String nama;
    private int hp;
    private int hpMaks;
    private int dayaDasar;
    private Senjata senjata;

    public Karakter(String nama, int hp, int dayaDasar) {
        this.nama = nama;
        this.hp = hp;
        this.hpMaks = hp;
        this.dayaDasar = dayaDasar;
    }

    public void pasangSenjata(Senjata senjata) {
        this.senjata = senjata;
    }

    public int hitungDamage() {
        int damage = this.dayaDasar;
        if (this.senjata != null) {
            damage = damage + this.senjata.getBonusDamage();
        }
        return damage;
    }

    public void terimaDamage(int damage) {
        this.hp = this.hp - damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    public boolean isHidup() {
        if (this.hp > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getNama() {
        return this.nama;
    }

    public int getHp() {
        return this.hp;
    }

    public int getHpMaks() {
        return this.hpMaks;
    }

    public String info() {
        return this.nama + " (" + this.hp + "/" + this.hpMaks + ")";
    }
}

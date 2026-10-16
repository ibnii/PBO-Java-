public class Prajurit extends Karakter {

    public Prajurit(String nama, int hp, int dayaDasar) {
        super(nama, hp, dayaDasar);
    }

    @Override
    public int hitungDamage() {
        //Amarah
        int damage = super.hitungDamage();
        if (getHp() * 2 < getHpMaks()) {
            damage = damage + 5;
        }
        return damage;
    }

    @Override
    public String info() {
        return super.info() + " [Prajurit]";
    }
}

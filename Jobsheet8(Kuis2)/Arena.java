public class Arena {

    public String duel(Prajurit prajurit, Penyihir penyihir) {
        int ronde = 1;

        while (prajurit.isHidup() && penyihir.isHidup()) {
            System.out.println("Ronde " + ronde);

            int damagePrajurit = prajurit.hitungDamage();
            penyihir.terimaDamage(damagePrajurit);
            System.out.println(prajurit.getNama() + " menyerang " + penyihir.getNama()
                    + ": -" + damagePrajurit + " -> sisa HP " + penyihir.getHp());

            if (penyihir.isHidup()) {
                int damagePenyihir = penyihir.hitungDamage();
                prajurit.terimaDamage(damagePenyihir);
                System.out.println(penyihir.getNama() + " menyerang " + prajurit.getNama()
                        + ": -" + damagePenyihir + " -> sisa HP " + prajurit.getHp());
            }

            ronde = ronde + 1;
        }

        if (prajurit.isHidup()) {
            return prajurit.info();
        } else {
            return penyihir.info();
        }
    }
}

package zombie;

public class MainTugas2 {
    public static void main(String[] args) {
        Destroyable[] destroyables = {
                new WalkingZombie(100, 1),
                new JumpingZombie(100, 2),
                new Barrier(100)
        };
        Plant p = new Plant();

        for (int i = 0; i < 4; i++) {
            for (Destroyable d : destroyables) {
                p.doDestroy(d);
            }
        }

        for (Destroyable d : destroyables) {
            if (d instanceof Zombie) {
                Zombie z = (Zombie) d;
                z.heal();
                System.out.println(z.getZombieInfo());
            } else if (d instanceof Barrier) {
                Barrier b = (Barrier) d;
                System.out.println(b.getBarrierInfo());
            }
        }
    }
}

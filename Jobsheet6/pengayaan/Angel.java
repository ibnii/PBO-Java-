package pengayaan;

public class Angel extends Character {
    protected int potion;

    public Angel(String name, int level, int health, int potion) {
        super(name, level, health);
        this.potion = potion;
    }

    public void cure(Character target) {
        target.health = 100;
        this.potion--;
    }
}

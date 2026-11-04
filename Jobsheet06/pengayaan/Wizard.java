package pengayaan;

public class Wizard extends Character {
    protected int spell;

    public Wizard(String name, int level, int health, int spell) {
        super(name, level, health);
        this.spell = spell;
    }

    public void magic(Character target) {
        target.health -= 50;
        this.spell--;
    }
}

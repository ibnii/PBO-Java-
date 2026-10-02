package pengayaan;

public class Human extends Character {
    protected int strength;

    public Human(String name, int level, int health, int strength) {
        super(name, level, health);
        this.strength = strength;
    }

    public void specialAttack(Character target) {
        target.health -= (10 + strength);
    }
}

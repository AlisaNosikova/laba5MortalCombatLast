package mortalkombatversion.models;

import mortalkombatversion.WeakAbility;

public class Character {
    
    private int level;
    private int health;
    private int maxHealth;
    private int damage;
    private int attack;
    private final String image;

    private int weakInMovements = 0;

    public Character(int level, int health, int damage, int attack, String image){
        this.level = level;
        this.health = health;
        this.damage = damage;
        this.attack = attack;
        this.maxHealth = health;
        this.image = image;
    }
   
    public void setLevel(){
        this.level++;
    }

    public void setHealth(int h){
        if (h < 0 && weakInMovements > 0) {
            h = h + (int) (h * WeakAbility.INJURY_PERCENT_EFFECT);
        }

        this.health+=h;

        if (health > maxHealth) {
            health = maxHealth;
        }
    }

    public void setNewHealth(int h){
        if (h > maxHealth) {
            health = maxHealth;
            return;
        }
        health = h;
    }

    public void setDamage(int d){
        this.damage+=d;
    }

    public void setAttack(int a){
        this.attack=a;
    }

    public void setMaxHealth(int h){
        this.maxHealth +=h;
    }
    
    public int getLevel(){
        return this.level;
    }

    public int getHealth(){
        return this.health;
    }

    public int getDamage(){
        if (weakInMovements > 0) {
            return damage - (int) (damage * WeakAbility.DAMAGE_PERCENT_EFFECT);
        }
        return damage;
    }

    public int getAttack(){
        return this.attack;
    }

    public int getMaxHealth(){
        return this.maxHealth;
    }
    
    public String getName(){
        return "";
    }

    public String getImage() {
        return image;
    }

    public int getWeakInMovements() {
        return weakInMovements;
    }

    public void setWeakInMovements(int weakInMovements) {
        this.weakInMovements = weakInMovements;
    }
}

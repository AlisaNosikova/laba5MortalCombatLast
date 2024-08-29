package mortalkombatversion.models;

public class Baraka extends Character {
    
    public Baraka() {
        super(1, 100, 12, 1, "sprites/Baraka.jpg");
    }
    
    @Override
    public String getName() {
        return "Baraka (танк)";
    }
}

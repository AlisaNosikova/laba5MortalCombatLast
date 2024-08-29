package mortalkombatversion.models;

public class SubZero extends Character {
    
    public SubZero() {
        super(1, 60, 16, 1, "sprites/Sub-Zero.jpg");
    }
    
    @Override
    public String getName(){
        return "Sub-Zero (маг)";
    }
}

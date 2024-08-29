package mortalkombatversion.models;

public class SonyaBlade extends Character {
    
    public SonyaBlade () {
        super(1, 80, 16, 1, "sprites/Sonya.Blade.jpg");
    }
    
    @Override
    public String getName(){
        return "Sonya (солдат)";
    }
}

package mortalkombatversion.models;

public class ShaoKahn extends Character {
    
    public ShaoKahn() {
        super(3, 80, 20, 1, "sprites/Shao.Kahn.png");
    }
    
    @Override
    public String getName(){
        return "Shao Kahn (босс)";
    }
}

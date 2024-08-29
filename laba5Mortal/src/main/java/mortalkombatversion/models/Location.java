package mortalkombatversion.models;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private final List<Character> enemies = new ArrayList<>();

    public Location(int enemyCount, Character[] enemies, Character[] bosses) {
        for (int i = 0; i < enemyCount; i++) {
            this.enemies.add(enemies[(int) (Math.random() * enemies.length)]);
        }

        this.enemies.add(bosses[(int) (Math.random() * bosses.length)]);
    }

    public int getCount() {
        return enemies.size();
    }

    public Character getEnemy() {
        return enemies.getFirst();
    }

    public void removeEnemy(Character enemy) {
        enemies.remove(enemy);
    }
}

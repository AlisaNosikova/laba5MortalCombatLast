
package mortalkombatversion;

import mortalkombatversion.models.*;
import mortalkombatversion.models.Character;
import mortalkombatversion.ui.screens.ItemsScreen;

public class CharacterAction {
    public interface OnLevelUpListener {
        void levelUp();
    }

    private final int[] experience_for_next_level = {40, 90, 180, 260, 410, 1000};

    public Character[] getEnemies() {
        return new Character[] {
                new Baraka(),
                new SubZero(),
                new LiuKang(),
                new SonyaBlade(),
        };
    }

    public Character[] getBosses() {
        return new Character[] {
                new ShaoKahn()
        };
    }

    public void addPoints(Human human, OnLevelUpListener onLevelUpListener) {
        switch (human.getLevel()) {
            case 0:
                human.setExperience(20);
                human.setPoints(25 + human.getHealth() / 4);
                break;
            case 1:
                human.setExperience(25);
                human.setPoints(30 + human.getHealth() / 4);
                break;
            case 2:
                human.setExperience(30);
                human.setPoints(35 + human.getHealth() / 4);
                break;
            case 3:
                human.setExperience(40);
                human.setPoints(45 + human.getHealth() / 4);
                break;
            case 4:
                human.setExperience(50);
                human.setPoints(55 + human.getHealth() / 4);
                break;
            default:
                human.setExperience(60);
                human.setPoints(55 + human.getHealth() / 4);
        }

        for (int i = 0; i < experience_for_next_level.length; i++) {
            if (human.getExperience() >= experience_for_next_level[i] && human.getNextExperience() <= experience_for_next_level[i]) {
                human.setLevel();
                human.setNextExperience(experience_for_next_level[i + 1]);
                onLevelUpListener.levelUp();
            }
        }
    }

    public void addPointsBoss(Human human, OnLevelUpListener onLevelUpListener) {
        switch (human.getLevel()) {
            case 2:
                human.setExperience(30);
                human.setPoints(45 + human.getHealth() / 2);
                break;
            case 4:
                human.setExperience(50);
                human.setPoints(65 + human.getHealth() / 2);
                break;
        }
        for (int i = 0; i < experience_for_next_level.length; i++) {
            if (human.getExperience() >= experience_for_next_level[i] && human.getNextExperience() <= experience_for_next_level[i]) {
                human.setLevel();
                human.setNextExperience(experience_for_next_level[i + 1]);
                onLevelUpListener.levelUp();
            }
        }
    }

    public void addItems(int k1, int k2, int k3, Items[] items) {
        double i = Math.random();
        if (i < k1 * 0.01) {
            items[0].setCount(1);
        }
        if (i >= k1 * 0.01 & i < (k1 + k2) * 0.01) {
            items[1].setCount(1);
        }
        if (i >= (k1 + k2) * 0.01 & i < (k1 + k2 + k3) * 0.01) {
            items[2].setCount(1);
        }
    }

    public boolean useItem(Character human, Items[] items, ItemsScreen.Items selectedItem) {
        switch (selectedItem) {
            case SMALL_POTION:
                if (items[0].getCount() > 0) {
                    human.setHealth((int) (human.getMaxHealth() * 0.25));
                    items[0].setCount(-1);
                    return true;
                }
                break;
            case LARGE_POTION:
                if (items[1].getCount() > 0) {
                    human.setHealth((int) (human.getMaxHealth() * 0.5));
                    items[1].setCount(-1);
                    return true;
                }
                break;
        }
        return false;
    }
}

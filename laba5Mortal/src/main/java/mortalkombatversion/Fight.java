
package mortalkombatversion;

import mortalkombatversion.models.*;
import mortalkombatversion.models.Character;

import java.util.ArrayList;

public class Fight {
    int[] kind_attack = {0};
    int i = 1;
    int k = -1;
    int stun = 0;
    double v = 0.0;

    public interface OnHitListener {
        void hit(String info1, String info2);
    }

    public interface OnEndRoundListener {
        void end(String info);
    }

    public interface OnEndGameListener {
        void end(String info, boolean top);
    }

    public void hit(Character p1, Character p2, OnHitListener onHitListener) {
        if (stun == 1) {
            p1.setAttack(-1);
        }

        boolean isBossHealing = Math.random() >= 0.9;

        switch (p1.getAttack() + Integer.toString(p2.getAttack())) {
            case "10":
                v = Math.random();

                if (isBossHealing && p1.getClass() == ShaoKahn.class) {
                    p1.setHealth((p1.getMaxHealth() - p1.getHealth()) / 2);
                    onHitListener.hit("Boss healed 50% of all damage", "");
                    return;
                }

                if (p1.getClass() == ShaoKahn.class & v < 0.15) {
                    p2.setHealth(-(int) (p1.getDamage() * 0.5));
                    onHitListener.hit("Your block is broken", "");
                } else {
                    p1.setHealth(-(int) (p2.getDamage() * 0.5));
                    onHitListener.hit(p2.getName() + " counterattacked", "");
                }
                break;
            case "11":
                if (p2.getClass() == ShaoKahn.class && isBossHealing) {
                    p2.setHealth(-p1.getDamage() * 2);
                    onHitListener.hit("Boss was healing but attacked", "");
                    return;
                }

                p2.setHealth(-p1.getDamage());
                onHitListener.hit(p1.getName() + " attacked", "");
                break;
            case "00":
                v = Math.random();
                if (v <= 0.5) {
                    stun = 1;
                }
                onHitListener.hit("Both defended themselves", "");
                break;
            case "01":
                onHitListener.hit(p1.getName() + " didn't attacked", "");
                break;
            case "-10":
                stun = 0;
                onHitListener.hit(p2.getName() + " didn't attacked", p1.getName() + " was stunned");
                break;
            case "-11":
                p1.setHealth(-p2.getDamage());
                stun = 0;
                onHitListener.hit(p2.getName() + " attacked", p1.getName() + " was stunned");
                break;
            case "60":
                if (Math.random() <= 0.75) {
                    p2.setWeakInMovements(p1.getLevel() == 0 ? 0 : p1.getLevel() + 1);
                    onHitListener.hit(p2.getName() + " was weaked", "");
                } else {
                    onHitListener.hit(p2.getName() + " counterattacked", "");
                }
                break;
            case "61":
                p1.setHealth(-p2.getDamage() - (int) (p2.getDamage() * 0.15));
                onHitListener.hit(p2.getName() + " interrupted weak", "");
                break;
            case "66":
                p1.setWeakInMovements(p2.getLevel() == 0 ? 0 : p2.getLevel() + 1);
                p2.setWeakInMovements(p1.getLevel() == 0 ? 0 : p1.getLevel() + 1);
                onHitListener.hit("Both are weaked", "");
        }
    }

    public int hit(Character human, Character enemy, CharacterActions attack, CharacterAction action, Items[] items, CharacterAction.OnLevelUpListener onLevelUpListener, OnHitListener onHitListener, OnEndRoundListener onEndRoundListener) {
        human.setAttack(attack.getCode());
        enemy.setAttack(getAdaptiveAttack(attack.getCode(), enemy));

        if (i % 2 == 1) {
            hit(human, enemy, onHitListener);
        } else {
            hit(enemy, human, onHitListener);
        }

        enemy.setWeakInMovements(enemy.getWeakInMovements() - 1);
        human.setWeakInMovements(human.getWeakInMovements() - 1);
        i++;
        if (human.getHealth() <= 0 & items[2].getCount() > 0) {
            human.setNewHealth((int) (human.getMaxHealth() * 0.05));
            items[2].setCount(-1);
            onHitListener.hit("", "Вы воскресли");
        }

        if (human.getHealth() <= 0 | enemy.getHealth() <= 0) {
            endRound(human, enemy, action, items, onLevelUpListener, onEndRoundListener);
        }

        return enemy.getAttack();
    }

    public void endRound(Character human, Character enemy,
                         CharacterAction action, Items[] items, CharacterAction.OnLevelUpListener onLevelUpListener, OnEndRoundListener onEndRoundListener) {
        if (human.getHealth() > 0) {
            onEndRoundListener.end("You win");
            ((Human) human).setWin();

            if (enemy.getClass() == ShaoKahn.class) {
                action.addItems(38, 23, 8, items);
                action.addPointsBoss(((Human) human), onLevelUpListener);
            } else {
                action.addItems(25, 15, 5, items);
                action.addPoints(((Human) human), onLevelUpListener);
            }
        } else {
            onEndRoundListener.end(enemy.getName() + " win");
        }

        i = 1;
        k = -1;
        kind_attack = resetAttack();
    }

    public void endFinalRound(Human human, CharacterAction action, ArrayList<Result> results, CharacterAction.OnLevelUpListener onLevelUpListener, OnEndGameListener onEndGameListener) {
        String text = "Победа не на вашей стороне";
        if (human.getHealth() > 0) {
            human.setWin();
            action.addPoints(human, onLevelUpListener);
            text = "Победа на вашей стороне";
        }
        boolean top = false;
        if (results == null) {
            top = true;
        } else {
            int i = 0;
            for (Result result : results) {
                if (human.getPoints() < result.points()) {
                    i++;
                }
            }
            if (i < 10) {
                top = true;
            }
        }
        onEndGameListener.end(text, top);
    }

    public int[] resetAttack() {
        return new int[]{0};
    }

    private int getAdaptiveAttack(int attack, Character enemy) {
        return switch (attack) {
            case 0 -> {
                if (enemy.getClass() == SubZero.class) {
                    yield 6;
                }
                yield (int) (Math.random() * 2);
            }
            case 1, 6 -> (int) (Math.random() * 2);
            default -> throw new IllegalStateException("Unexpected value: " + attack);
        };
    }
}

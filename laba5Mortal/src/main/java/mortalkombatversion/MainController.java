package mortalkombatversion;

import mortalkombatversion.models.Character;
import mortalkombatversion.models.Human;
import mortalkombatversion.models.Location;
import mortalkombatversion.models.ShaoKahn;
import mortalkombatversion.ui.screens.*;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainController implements Runnable {

    private final Game game = new Game();

    private final MainScreen mainScreen = new MainScreen();
    private final BattleScreen battleScreen = new BattleScreen();
    private final ResultsScreen resultsScreen = new ResultsScreen();
    private final EndRoundScreen endRoundScreen = new EndRoundScreen();
    private final LocationChooserScreen locationChooserScreen = new LocationChooserScreen();
    private final ImprovingAbilitiesScreen improvingAbilitiesScreen = new ImprovingAbilitiesScreen();
    private final SuccessEndGameScreen successEndGameScreen = new SuccessEndGameScreen();
    private final FailureEndGameScreen failureEndGameScreen = new FailureEndGameScreen();
    private final ItemsScreen itemsScreen = new ItemsScreen();

    private Human player;

    private Character enemy;

    private final Items[] items = {
            new Items("Малое зелье лечение",0),
            new Items("Большое зелье лечение",0),
            new Items("Крест возрождения",0),
    };

    private int locationsCount, locationNumber = 0;
    private Location currentLocation;

    @Override
    public void run() {
        mainScreen.addOnStartClickListener(component -> locationChooserScreen.show());
        mainScreen.addOnResultsClickListener(component -> onResults());

        locationChooserScreen.addOnSubmitClickListener(this::onLocationSubmit);
        locationChooserScreen.addOnCancelClickListener(component -> locationChooserScreen.hide());

        endRoundScreen.addOnNextClickListener(component -> {
            onNextRound();
            endRoundScreen.hide();
        });

        battleScreen.addOnItemsClickListener(component -> onItems());
        battleScreen.addOnAttackClickListener(component -> onAttack());
        battleScreen.addOnDefendClickListener(component -> onDefend());
        battleScreen.addOnWeakClickListener(component -> onWeak());

        improvingAbilitiesScreen.addOnDamageListener(component -> onDamageAbility());
        improvingAbilitiesScreen.addOnHealthListener(component -> onHealthAbility());

        endRoundScreen.addOnNextClickListener(component -> endRoundScreen.hide());

        successEndGameScreen.addOnEndClickListener(this::onEndGame);
        failureEndGameScreen.addOnEndClickListener(component -> {
            battleScreen.hide();
            failureEndGameScreen.hide();
        });

        itemsScreen.addOnSubmitListener(this::onItem);

        mainScreen.show();
    }

    private void onResults() {
        resultsScreen.update(game.getResults());
        resultsScreen.show();
    }

    private void updateTurn() {
        itemsScreen.update(items);

        battleScreen.updatePlayerStats(player);
        battleScreen.updateEnemyStats(enemy);
        battleScreen.updateBallsStats(player);

        if (game.fight.i % 2 == 1) {
            battleScreen.setTurnText("Your turn");
        } else {
            battleScreen.setTurnText(enemy.getName() + "'s turn");
        }
        battleScreen.setLocationAndEnemiesNumberText(locationNumber, currentLocation.getCount());
    }

    private void onEndGame(String name) {
        try {
            game.endGameTop(player, name);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        successEndGameScreen.hide();
        itemsScreen.hide();
    }

    private void onNextRound() {
        battleScreen.setEnableInput(true);
        battleScreen.setHitResultText("");
        battleScreen.setStunText("");
        battleScreen.setHitEnemyResultText("");
        battleScreen.setHitPlayerResultText("");

        if (enemy.getClass().equals(ShaoKahn.class) && enemy.getHealth() > 0) {
            battleScreen.setEnableInput(false);
            failureEndGameScreen.show();
            return;
        }

        if (enemy.getHealth() <= 0) {
            currentLocation.removeEnemy(enemy);
        }

        try {
            enemy = currentLocation.getEnemy();
        } catch (NoSuchElementException exception) {
            locationsCount--;
            locationNumber++;
            if (locationsCount < 0) {
                battleScreen.hide();
                game.fight.endFinalRound(player, game.action, game.getResults(), improvingAbilitiesScreen::show, (info, top) -> {
                    successEndGameScreen.show();
                    battleScreen.hide();
                    itemsScreen.hide();
                    failureEndGameScreen.hide();
                });
                improvingAbilitiesScreen.hide();
            } else {
                currentLocation = new Location(player.getLevel() + (int) (Math.random() * 3) + 1, game.action.getEnemies(), game.action.getBosses());
                enemy = currentLocation.getEnemy();
            }
        }

        enemy.setNewHealth(enemy.getMaxHealth());
        player.setNewHealth(player.getMaxHealth());
        updateTurn();
    }

    private void onHit(String hitResult, String stun) {
        battleScreen.setHitResultText(hitResult);
        battleScreen.setStunText(stun);
        updateTurn();
    }

    private void onEndRound(String winText) {
        battleScreen.setEnableInput(false);
        endRoundScreen.show();
        endRoundScreen.setWinText(winText);
    }

    private void onItems() {
        itemsScreen.update(items);
        itemsScreen.show();
    }

    private void onItem(ItemsScreen.Items selectedItem) {
        if (!game.action.useItem(player, items, selectedItem)) {
            itemsScreen.hide();
        }

        updateTurn();
    }

    private void onLevelUp() {
        endRoundScreen.setEnabledInput(false);
        improvingAbilitiesScreen.show();
    }

    private void onAttack() {
        game.fight.hit(player, enemy, CharacterActions.ATTACK, game.action, items, this::onLevelUp, this::onHit, this::onEndRound);
    }

    private void onDefend() {
        game.fight.hit(player, enemy, CharacterActions.BLOCK_ATTACK, game.action, items, this::onLevelUp, this::onHit, this::onEndRound);
    }

    private void onWeak() {
        game.fight.hit(player, enemy, CharacterActions.WEAK_ATTACK, game.action, items, this::onLevelUp, this::onHit, this::onEndRound);
    }

    private void onDamageAbility() {
        player.setDamage(2);
        improvingAbilitiesScreen.hide();
        endRoundScreen.setEnabledInput(true);
    }

    private void onHealthAbility() {
        player.setMaxHealth(20);
        improvingAbilitiesScreen.hide();
        endRoundScreen.setEnabledInput(true);
    }

    private void onLocationSubmit(int count) {
        locationChooserScreen.hide();

        locationsCount = count;
        player = new Human(0,80,16,1, "sprites/Kitana.jpg");
        currentLocation = new Location(player.getLevel() + (int) (Math.random() * 3) + 1, game.action.getEnemies(), game.action.getBosses());

        enemy = currentLocation.getEnemy();

        updateTurn();
        battleScreen.show();
        battleScreen.setEnableInput(true);
    }
}

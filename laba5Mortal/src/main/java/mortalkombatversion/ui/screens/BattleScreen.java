package mortalkombatversion.ui.screens;

import mortalkombatversion.CharacterActions;
import mortalkombatversion.models.Character;
import mortalkombatversion.models.Human;
import mortalkombatversion.ui.views.BallsView;
import mortalkombatversion.ui.views.CharacterView;
import mortalkombatversion.ui.OnClickListener;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class BattleScreen extends Screen {

    private CharacterView enemyView, playerView;

    private BallsView ballsView;

    private JButton itemsButton, attackButton, defendButton, weakButton;

    private JLabel turnLabel, hitResultLabel, hitPlayerResultLabel, hitEnemyResultLabel , stunLabel, titleLabel;

    @Override
    protected void onCreate() {
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.add(initLeftSection(), BorderLayout.WEST);
        contentPane.add(initTopSection(), BorderLayout.NORTH);
        contentPane.add(initRightSection(), BorderLayout.EAST);
        contentPane.add(initCenterSection(), BorderLayout.CENTER);
        contentPane.add(initBottomSection(), BorderLayout.SOUTH);
        frame.setPreferredSize(new Dimension(800, 640));
    }

    public void setEnableInput(boolean enable) {
        itemsButton.setEnabled(enable);
        attackButton.setEnabled(enable);
        defendButton.setEnabled(enable);
        weakButton.setEnabled(enable);
    }

    public void addOnItemsClickListener(OnClickListener<JButton> listener) {
        itemsButton.addActionListener(event -> listener.click(itemsButton));
    }

    public void addOnAttackClickListener(OnClickListener<JButton>  listener) {
        attackButton.addActionListener(event -> listener.click(attackButton));
    }

    public void addOnDefendClickListener(OnClickListener<JButton>  listener) {
        defendButton.addActionListener(event -> listener.click(defendButton));
    }

    public void addOnWeakClickListener(OnClickListener<JButton>  listener) {
        weakButton.addActionListener(event -> listener.click(weakButton));
    }

    public void updatePlayerStats(Character player) {
        playerView.update(player);
        setHitPlayerResultText(player.getName() + " " + CharacterActions.from(player.getAttack()));
    }

    public void updateEnemyStats(Character enemy) {
        enemyView.update(enemy);
        setHitEnemyResultText(enemy.getName() + " " + CharacterActions.from(enemy.getAttack()));
    }

    public void updateBallsStats(Human player) {
        ballsView.update(player);
    }

    public void setTurnText(String text) {
        turnLabel.setText(text);
    }

    public void setHitResultText(String text) {
        hitResultLabel.setText(text);
    }

    public void setHitPlayerResultText(String text) {
        hitPlayerResultLabel.setText(text);
    }

    public void setHitEnemyResultText(String text) {
        hitEnemyResultLabel.setText(text);
    }

    public void setStunText(String text) {
        stunLabel.setText(text);
    }

    public void setLocationAndEnemiesNumberText(int locations, int enemies) {
        titleLabel.setText("Location " + locations + " Enemies " + enemies);
    }

    private JComponent initRightSection() {
        playerView = new CharacterView(true);
        return playerView;
    }

    private JComponent initLeftSection() {
        enemyView = new CharacterView(false);
        return enemyView;
    }

    private JComponent initTopSection() {
        titleLabel = new JLabel("Fight", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        return titleLabel;
    }

    private JComponent initCenterSection() {
        ballsView = new BallsView();
        ballsView.setPoints(20);
        ballsView.setExperience(30);

        hitResultLabel = new JLabel("", JLabel.CENTER);
        hitResultLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        hitResultLabel.setAlignmentY(JLabel.CENTER_ALIGNMENT);

        hitEnemyResultLabel = new JLabel("", JLabel.CENTER);
        hitEnemyResultLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        hitEnemyResultLabel.setAlignmentY(JLabel.CENTER_ALIGNMENT);
        hitEnemyResultLabel.setBorder(new EmptyBorder(20, 0, 0, 0));

        hitPlayerResultLabel = new JLabel("", JLabel.CENTER);
        hitPlayerResultLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        hitPlayerResultLabel.setAlignmentY(JLabel.CENTER_ALIGNMENT);
        hitPlayerResultLabel.setBorder(new EmptyBorder(0, 0, 20, 0));

        turnLabel = new JLabel("", JLabel.CENTER);
        turnLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        turnLabel.setAlignmentY(JLabel.CENTER_ALIGNMENT);

        stunLabel = new JLabel("", JLabel.CENTER);
        stunLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        stunLabel.setAlignmentY(JLabel.CENTER_ALIGNMENT);

        Box box = new Box(BoxLayout.Y_AXIS);
        box.add(ballsView);
        box.add(Box.createVerticalGlue());
        box.add(hitEnemyResultLabel);
        box.add(hitPlayerResultLabel);
        box.add(hitResultLabel);
        box.add(Box.createVerticalGlue());
        box.add(turnLabel);
        box.add(stunLabel);
        box.add(Box.createVerticalGlue());
        return box;
    }

    private JComponent initBottomSection() {
        Box actionsBox = new Box(BoxLayout.X_AXIS);
        itemsButton = new JButton("Items");
        attackButton = new JButton("Attack");
        defendButton = new JButton("Defend");
        weakButton = new JButton("Weak");

        actionsBox.setBorder(new EmptyBorder(10, 10, 0, 10));
        actionsBox.add(Box.createHorizontalGlue());
        actionsBox.add(itemsButton);
        actionsBox.add(Box.createHorizontalStrut(10));
        actionsBox.add(attackButton);
        actionsBox.add(Box.createHorizontalStrut(10));
        actionsBox.add(defendButton);
        actionsBox.add(Box.createHorizontalStrut(10));
        actionsBox.add(weakButton);
        contentPane.add(actionsBox, BorderLayout.SOUTH);
        return actionsBox;
    }
}

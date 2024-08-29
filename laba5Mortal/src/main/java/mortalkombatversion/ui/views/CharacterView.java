package mortalkombatversion.ui.views;

import mortalkombatversion.models.Character;
import mortalkombatversion.resources.ResourcesManagerImpl;
import mortalkombatversion.ui.HealthComponent;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class CharacterView extends JComponent implements View<Character> {

    private final HealthComponent healthComponent = new HealthComponent();

    private final JLabel damageLabel = new JLabel();
    private final JLabel levelLabel = new JLabel();
    private final JLabel iconLabel = new JLabel();
    private final JLabel nameLabel = new JLabel();

    public CharacterView(boolean reverse) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(new EmptyBorder(80, 0, 0, 0));

        damageLabel.setBorder(new EmptyBorder(10, 0, 0, 0));

        nameLabel.setBorder(new EmptyBorder(10, 0, 0, 0));

        levelLabel.setBorder(new EmptyBorder(0, 10, 0, 10));
        levelLabel.setAlignmentY(JLabel.TOP_ALIGNMENT);

        iconLabel.setAlignmentY(JLabel.TOP_ALIGNMENT);
        iconLabel.setPreferredSize(new Dimension(200, 320));

        if (reverse) {
            panel.add(iconLabel);
            panel.add(levelLabel);
        } else {
            panel.add(levelLabel);
            panel.add(iconLabel);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(healthComponent);
        add(damageLabel);
        add(Box.createVerticalGlue());
        add(panel);
        add(nameLabel);
    }

    @Override
    public void update(Character model) {
        setHealthText(model.getHealth(), model.getMaxHealth());
        setDamageText(model.getDamage());
        setLevelText(model.getLevel());
        setIcon(new ImageIcon(new ResourcesManagerImpl().getResource(model.getImage())));
        setName(model.getName());
    }

    public void setHealthText(int health, int maxHealth) {
        healthComponent.setHealthValues(Math.max(health, 0), maxHealth);
    }

    public void setDamageText(int damage) {
        damageLabel.setText("Damage " + Math.max(damage, 0));
    }

    public void setLevelText(int level) {
        levelLabel.setText(level + " Level");
    }

    public void setIcon(ImageIcon icon) {
        iconLabel.setIcon(icon);
    }

    public void setName(String name) {
        nameLabel.setText(name);
    }
}

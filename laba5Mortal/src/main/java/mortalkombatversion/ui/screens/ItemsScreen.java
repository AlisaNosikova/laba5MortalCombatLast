package mortalkombatversion.ui.screens;

import mortalkombatversion.Items;
import mortalkombatversion.ui.OnClickListener;
import mortalkombatversion.ui.views.View;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ItemsScreen extends Screen implements View<Items[]> {

    public enum Items {
        SMALL_POTION,
        LARGE_POTION,
        REVIVAL;
    }

    private JButton submitButton;

    private JRadioButton smallPotionButton, largePotionButton, revivalButton;

    private JLabel smallPotionLabel, largePotionLabel, revivalLabel;

    @Override
    protected void onCreate() {
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.add(initTopSection(), BorderLayout.NORTH);
        contentPane.add(initCenterSection(), BorderLayout.CENTER);
        contentPane.add(initBottomSection(), BorderLayout.SOUTH);
    }

    @Override
    public void update(mortalkombatversion.Items[] model) {
        smallPotionLabel.setText("Small Potion, count " + model[0].getCount());
        largePotionLabel.setText("Large Potion, count " + model[1].getCount());
        revivalLabel.setText("Revival, count " + model[2].getCount());
    }

    public void addOnSubmitListener(OnClickListener<Items> listener) {
        submitButton.addActionListener(event -> listener.click(getSelectedItem()));
    }

    private JComponent initTopSection() {
        JLabel titleLabel = new JLabel("Items bag", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        titleLabel.setBorder(new EmptyBorder(0, 0, 20, 0));
        return titleLabel;
    }

    private JComponent initCenterSection() {

        smallPotionLabel = new JLabel("Small Potion, count 0");
        smallPotionButton = new JRadioButton();
        smallPotionButton.setSelected(true);

        largePotionLabel = new JLabel("Large Potion, count 0");
        largePotionButton = new JRadioButton();

        revivalLabel = new JLabel("Revival, count 0");
        revivalButton = new JRadioButton();

        ButtonGroup radiosGroup = new ButtonGroup();
        radiosGroup.add(smallPotionButton);
        radiosGroup.add(largePotionButton);
        radiosGroup.add(revivalButton);

        JPanel contentPanel = new JPanel(new GridLayout(3, 2));
        contentPanel.setBorder(new EmptyBorder(0, 0, 20, 0));

        contentPanel.add(smallPotionButton);
        contentPanel.add(smallPotionLabel);

        contentPanel.add(largePotionButton);
        contentPanel.add(largePotionLabel);

        contentPanel.add(revivalButton);
        contentPanel.add(revivalLabel);
        return contentPanel;
    }

    private JComponent initBottomSection() {
        submitButton = new JButton("Submit");
        return submitButton;
    }

    private Items getSelectedItem() {
        if (smallPotionButton.isSelected()) {
            return Items.SMALL_POTION;
        }

        if (largePotionButton.isSelected()) {
            return Items.LARGE_POTION;
        }

        if (revivalButton.isSelected()) {
            return Items.REVIVAL;
        }

        return null;
    }
}

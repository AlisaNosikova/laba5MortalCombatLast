package mortalkombatversion.ui.views;

import mortalkombatversion.models.Human;

import javax.swing.*;
import javax.swing.border.*;

public class BallsView extends JComponent implements View<Human> {

    private final JLabel pointsLabel = new JLabel("", JLabel.CENTER);
    private final JLabel experienceLabel = new JLabel("", JLabel.CENTER);

    public BallsView() {
        pointsLabel.setHorizontalAlignment(JLabel.CENTER);
        experienceLabel.setBorder(new EmptyBorder(0, 10, 0, 0));

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentX(JComponent.CENTER_ALIGNMENT);
        add(pointsLabel);
        add(experienceLabel);
    }

    @Override
    public void update(Human model) {
        setPoints(model.getPoints());
        setExperience(model.getExperience());
    }

    public void setPoints(int points) {
        pointsLabel.setText(
            "<html><p style=\"text-align: center;\">points<br>" +
            points +
            "</p><html>"
        );
    }

    public void setExperience(int exp) {
        experienceLabel.setText(
            "<html><p style=\"text-align: center;\">experience<br>" +
            exp +
            "</p><html>"
        );
    }
}

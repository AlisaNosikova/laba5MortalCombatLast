package mortalkombatversion.ui.screens;

import mortalkombatversion.models.Result;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class ResultsScreen extends Screen {

    private JTable table;

    @Override
    protected void onCreate() {
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.add(initTopSection(), BorderLayout.NORTH);
        contentPane.add(initCenterSection(), BorderLayout.CENTER);
    }

    private JComponent initTopSection() {
        JLabel titleLabel = new JLabel("Results Table", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 32));
        titleLabel.setBorder(new EmptyBorder(0, 0, 60, 0));
        return titleLabel;
    }

    private JComponent initCenterSection() {
        table = new JTable();
        table.setModel(
            new javax.swing.table.DefaultTableModel(
                new Object[][] {
                    { null, null },
                    { null, null },
                    { null, null },
                    { null, null },
                    { null, null },
                    { null, null },
                    { null, null },
                    { null, null },
                    { null, null },
                    { null, null },
                },
                new String[] { "Name", "Count" }
            ) {
                final Class<?>[] types = new Class<?>[] {
                    String.class,
                    Integer.class,
                };

                public Class<?> getColumnClass(int columnIndex) {
                    return types[columnIndex];
                }
            }
        );
        return table;
    }

    public void update(java.util.List<Result> results) {
        table.getModel().setValueAt("Name", 0, 0);
        table.getModel().setValueAt("Count", 0, 1);
        for (int i = 0; i < results.size(); i++) {
            table.getModel().setValueAt(results.get(i).name(), i+1, 0);
            table.getModel().setValueAt(results.get(i).points(), i+1, 1);
        }
    }
}

package mortalkombatversion;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NumberKeyAdapter extends KeyAdapter {
    @Override
    public void keyTyped(KeyEvent event) {
        char symbol = event.getKeyChar();
        if ((symbol < '0' || symbol > '9') && event.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
            event.consume();
        }
    }
}

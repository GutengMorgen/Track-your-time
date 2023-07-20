package src;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class ShortcutManager {

    public void add(JTextArea textArea, Popup popup) {

        @SuppressWarnings("serial")
        Action saveAction = new AbstractAction("Save") {
            @Override
            public void actionPerformed(ActionEvent e) {
                popup.SaveClose();
            }
        };

        // Add the action to the txtDescription's input map with the Ctrl + S key stroke
        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK), "saveAction");
        textArea.getActionMap().put("saveAction", saveAction);

    }
}

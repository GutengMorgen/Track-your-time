package src;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

@SuppressWarnings("serial")
public class ShortcutManager {
	int index = 0;

    public void add(JTextArea textArea, JComboBox<String> comboBox, Popup popup) {

        Action saveAction = new AbstractAction("Save") {
            @Override
            public void actionPerformed(ActionEvent e) {
                popup.SaveClose();
            }
        };

        // Add the action to the txtDescription's input map with the Ctrl + S key stroke
        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK), "saveAction");
        textArea.getActionMap().put("saveAction", saveAction);
        
        
        Action downIndexAction = new AbstractAction("downIndex") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				if(index == -1) {
					comboBox.setSelectedIndex(0);
				}
				else if(index > 0) {
					comboBox.setSelectedIndex(index - 1);
				}
			}
		};

        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, KeyEvent.CTRL_DOWN_MASK), "downIndexAction");
        textArea.getActionMap().put("downIndexAction", downIndexAction);
        
        
        
        Action upIndexAction = new AbstractAction("upIndex") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = comboBox.getSelectedIndex();
				if(index == -1) {
					comboBox.setSelectedIndex(0);
				}
				else if(index < comboBox.getItemCount() - 1) {
					comboBox.setSelectedIndex(index + 1);
				}
			}
		};

        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, KeyEvent.CTRL_DOWN_MASK), "upIndexAction");
        textArea.getActionMap().put("upIndexAction", upIndexAction);
        
        
        Action preLineAction = new AbstractAction("previousLine") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(index > 0) {
					index--;
					String line = popup.data.ReadLineByIndex(index);
					String lineFiltered = popup.data.filterLine(line, "Description:");
					textArea.setText(lineFiltered);
				}
			}
		};

        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, KeyEvent.CTRL_DOWN_MASK), "preLineAction");
        textArea.getActionMap().put("preLineAction", preLineAction);
        
        
        Action lastLineAction = new AbstractAction("latestLine") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int dataSize = popup.data.getDataIndex();
				
				if(index < dataSize - 1) {
					index++;
					String line = popup.data.ReadLineByIndex(index);
					String lineFiltered = popup.data.filterLine(line, "Description:");
					textArea.setText(lineFiltered);
				}
			}
		};

        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, KeyEvent.CTRL_DOWN_MASK), "lastLineAction");
        textArea.getActionMap().put("lastLineAction", lastLineAction);
    }
}

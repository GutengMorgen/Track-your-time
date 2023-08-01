package src;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import src.Frames.Popup;

@SuppressWarnings("serial")
public class ShortcutManager {
	int index = 0;

    public void add(JTextArea textArea, JComboBox<MyItems> comboBox, Popup popup) {

    	/*
    	 * Keyboard to Save
    	 */
        Action saveAction = new AbstractAction("Save") {
            @Override
            public void actionPerformed(ActionEvent e) {
                popup.SaveClose();
            }
        };

        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK), "saveAction");
        textArea.getActionMap().put("saveAction", saveAction);
        
        
        /*
    	 * Keyboard to get the down index of the comboBox
    	 */
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
        
        
        /*
    	 * Keyboard to get the up index of the comboBox
    	 */
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
        
        
        /**
         * Keyboard to get the previous line of the data.csv
         */
        Action preLineAction = new AbstractAction("previousLine") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(index > 0) {
					index--;
					String line = DataManager.ReadLineByIndex(index);
					String lineFiltered = DataManager.filterLine(line, "Description:");
					textArea.setText(lineFiltered);
				}
			}
		};

        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, KeyEvent.CTRL_DOWN_MASK), "preLineAction");
        textArea.getActionMap().put("preLineAction", preLineAction);
        
        
        /*
    	 * Keyboard to get the last line of the data.csv
    	 */
        Action lastLineAction = new AbstractAction("latestLine") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int dataSize = DataManager.getSize();
				
				if(index < dataSize - 1) {
					index++;
					String line = DataManager.ReadLineByIndex(index);
					String lineFiltered = DataManager.filterLine(line, "Description:");
					textArea.setText(lineFiltered);
				}
			}
		};

        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, KeyEvent.CTRL_DOWN_MASK), "lastLineAction");
        textArea.getActionMap().put("lastLineAction", lastLineAction);
        
        
        /*
    	 * Keyboard to get template of the current tag of the comboBox
    	 */
        Action getTemplateAction = new AbstractAction("getTemplate") {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO: hacer que el combobox del popup sea igual al del mainframe
				MyItems myItems = (MyItems) comboBox.getSelectedItem();
				
				textArea.setText(myItems.getTemplate().replace("\\n", "\n"));
			}
		};

        textArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_T, KeyEvent.CTRL_DOWN_MASK), "getTemplateAction");
        textArea.getActionMap().put("getTemplateAction", getTemplateAction);
        
    }
}

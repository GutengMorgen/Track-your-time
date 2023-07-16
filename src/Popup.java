package src;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.SpringLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Cursor;
import javax.swing.DebugGraphics;

public class Popup extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Popup frame = new Popup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Popup() {
//		setUndecorated(true);
		setResizable(false);
		setAlwaysOnTop(true);
		setAlwaysOnTop(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 290);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setVerifyInputWhenFocusTarget(false);
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		comboBox.setFont(new Font("Verdana", Font.BOLD, 13));
		comboBox.setBorder(new LineBorder(new Color(0, 0, 0)));
		comboBox.setLightWeightPopupEnabled(false);
		comboBox.setRequestFocusEnabled(false);
		comboBox.setFocusTraversalKeysEnabled(false);
		comboBox.setFocusable(false);
		comboBox.setForeground(new Color(248, 248, 241));
		comboBox.setBackground(new Color(26, 18, 11));
		comboBox.setBounds(324, 62, 129, 25);
		comboBox.setToolTipText("Select any tag");
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"working", "studing", "relax"}));
		contentPane.add(comboBox);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Verdana", Font.PLAIN, 12));
		textArea.setForeground(new Color(26, 18, 11));
		textArea.setBackground(new Color(235, 235, 235));
		textArea.setBorder(new CompoundBorder(new LineBorder(new Color(187, 187, 187)), new EmptyBorder(10, 10, 10, 10)));
		textArea.setMargin(new Insets(10, 10, 10, 10));
		textArea.setBounds(10, 92, 444, 75);
		contentPane.add(textArea);
		
		JButton btnNewButton = new JButton("SAVE (ctrl + s)");
		btnNewButton.setFont(new Font("Lucida Console", Font.BOLD, 18));
		btnNewButton.setForeground(new Color(248, 248, 241));
		btnNewButton.setBackground(new Color(26, 18, 11));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBounds(10, 195, 444, 44);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		textField = new JTextField();
		textField.setFont(new Font("Consolas", Font.PLAIN, 12));
		textField.setDisabledTextColor(new Color(81, 81, 81));
		textField.setRequestFocusEnabled(false);
		textField.setFocusable(false);
		textField.setFocusTraversalKeysEnabled(false);
		textField.setBorder(new LineBorder(new Color(216, 216, 216)));
		textField.setAutoscrolls(false);
		textField.setBackground(new Color(240, 240, 240));
		textField.setEnabled(false);
		textField.setBounds(10, 28, 443, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("View report");
		lblNewLabel.setForeground(new Color(26, 18, 11));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Console", Font.PLAIN, 10));
		lblNewLabel.setBounds(10, 179, 79, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setForeground(new Color(26, 18, 11));
		lblSettings.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblSettings.setFont(new Font("Lucida Console", Font.PLAIN, 10));
		lblSettings.setBounds(395, 178, 58, 14);
		contentPane.add(lblSettings);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setForeground(new Color(26, 18, 11));
		lblDescription.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		lblDescription.setBounds(10, 69, 93, 14);
		contentPane.add(lblDescription);
		
		JLabel lblTags = new JLabel("Tags:");
		lblTags.setForeground(new Color(26, 18, 11));
		lblTags.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTags.setHorizontalAlignment(SwingConstants.CENTER);
		lblTags.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		lblTags.setBounds(281, 69, 38, 14);
		contentPane.add(lblTags);
		
		JLabel lblLastUpdate = new JLabel("Last Update:");
		lblLastUpdate.setForeground(new Color(81, 81, 81));
		lblLastUpdate.setHorizontalTextPosition(SwingConstants.CENTER);
		lblLastUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastUpdate.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		lblLastUpdate.setBounds(10, 13, 93, 14);
		contentPane.add(lblLastUpdate);
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("hello");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dispose();
	}
}

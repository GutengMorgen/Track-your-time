package src;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JList;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {
	private JTextField textField_2;
	private JTextField txtCtrlS;
	private JTextField txtCtrlK;
	private JTextField txtCtrlM;
	private JTextField txtCtrl;
	private JTextField txtCtrlArrow;
	private JTextField txtCtrlL;
	private JTextField textField;
	private JTextArea textTesting;
	private JTextArea textTesting2;
	private JButton btnStop;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setAlwaysOnTop(true);
		setBackground(new Color(0, 0, 0));
		getContentPane().setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Tack your time");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 625);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255));
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel home = new JPanel();
		tabbedPane.addTab("Home", null, home, null);
		tabbedPane.setEnabledAt(0, true);
		home.setLayout(null);
		
		btnNewButton = new JButton("Start");
		btnNewButton.setBounds(40, 36, 203, 84);
		home.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		btnStop = new JButton("Stop");
		btnStop.setBounds(276, 36, 203, 84);
		home.add(btnStop);
		btnStop.addActionListener(this);
		
		JScrollPane scrollPaneTesting = new JScrollPane();
		scrollPaneTesting.setBounds(40, 359, 439, 73);
		home.add(scrollPaneTesting);
		
		textTesting = new JTextArea();
		textTesting.setLineWrap(true);
		scrollPaneTesting.setViewportView(textTesting);
		
		JScrollPane scrollPaneTesting2 = new JScrollPane();
		scrollPaneTesting2.setBounds(40, 462, 439, 73);
		home.add(scrollPaneTesting2);
		
		textTesting2 = new JTextArea();
		textTesting2.setLineWrap(true);
		scrollPaneTesting2.setViewportView(textTesting2);
		
		JLabel lblNewLabel_3 = new JLabel("History");
		lblNewLabel_3.setFont(new Font("Lucida Console", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(40, 134, 96, 29);
		home.add(lblNewLabel_3);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(40, 174, 439, 165);
		home.add(scrollPane_2);
		
		JTextArea txtrImportJavautilregexpatternImport = new JTextArea();
		txtrImportJavautilregexpatternImport.setAutoscrolls(false);
		txtrImportJavautilregexpatternImport.setText("import java.util.regex.Pattern;\r\nimport java.util.regex.Matcher;\r\n\r\npublic class StringFormatMatcher {\r\n    public static void main(String[] args) {\r\n        String input = \"Date: 2023-07-19;Time: 10:30 AM;Tag: Meeting;Description: Discuss project updates\";\r\n        String format = \"Date: %s;Time: %s;Tag: %s;Description: %s\";\r\n\r\n        // Escape special characters in the format string\r\n        String escapedFormat = format.replaceAll(\"%\", \"%%\");\r\n\r\n        // Create a regular expression from the escaped format string\r\n        String regex = escapedFormat.replaceFirst(\"\\\\\\\\s\", \"\\\\\\\\S+\");\r\n\r\n        // Compile the regular expression pattern\r\n        Pattern pattern = Pattern.compile(regex);\r\n\r\n        // Match the input against the pattern\r\n        Matcher matcher = pattern.matcher(input);\r\n\r\n        // Check if the input matches the format\r\n        boolean matchesFormat = matcher.matches();\r\n\r\n        // Print the result\r\n        if (matchesFormat) {\r\n            System.out.println(\"Input matches the expected format.\");\r\n        } else {\r\n            System.out.println(\"Input does not match the expected format.\");\r\n        }\r\n    }\r\n}\r\n");
		scrollPane_2.setViewportView(txtrImportJavautilregexpatternImport);
		txtrImportJavautilregexpatternImport.setVerifyInputWhenFocusTarget(false);
		txtrImportJavautilregexpatternImport.setRequestFocusEnabled(false);
		txtrImportJavautilregexpatternImport.setFocusTraversalKeysEnabled(false);
		txtrImportJavautilregexpatternImport.setFocusable(false);
		txtrImportJavautilregexpatternImport.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtrImportJavautilregexpatternImport.setEditable(false);
		txtrImportJavautilregexpatternImport.setLineWrap(true);
		
		JPanel dayReport = new JPanel();
		tabbedPane.addTab("Day Report", null, dayReport, null);
		
		JPanel weekReport = new JPanel();
		tabbedPane.addTab("Week Report", null, weekReport, null);
		tabbedPane.setEnabledAt(2, true);
		
		JPanel monthReport = new JPanel();
		tabbedPane.addTab("Month Report", null, monthReport, null);
		
		JPanel settings = new JPanel();
		tabbedPane.addTab("Settings", null, settings, null);
		tabbedPane.setEnabledAt(4, true);
		settings.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Save and close:");
		lblNewLabel_1.setBounds(34, 91, 89, 14);
		settings.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Set Display Time:");
		lblNewLabel.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lblNewLabel.setBounds(24, 21, 166, 24);
		settings.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"15 min", "20 min", "25 min", "30 min", "35 min", "40 min", "45 min", "50 min", "55 min", "60 min"}));
		comboBox.setFont(new Font("Lucida Console", Font.PLAIN, 13));
		comboBox.setBounds(284, 22, 202, 22);
		settings.add(comboBox);
		
		JLabel lblKeyworks = new JLabel("Keyboard Shortcuts");
		lblKeyworks.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lblKeyworks.setBounds(24, 56, 185, 24);
		settings.add(lblKeyworks);
		
		JLabel lblNewLabel_1_1 = new JLabel("Skip and close:");
		lblNewLabel_1_1.setBounds(295, 91, 89, 14);
		settings.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Browsing the history:");
		lblNewLabel_1_1_1.setBounds(34, 132, 147, 14);
		settings.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Get 2 previous description:");
		lblNewLabel_1_1_1_1.setBounds(295, 132, 147, 14);
		settings.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Get latest description:");
		lblNewLabel_1_1_1_1_1.setBounds(34, 172, 119, 14);
		settings.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Get tag template:");
		lblNewLabel_1_1_1_1_1_1.setBounds(295, 172, 97, 14);
		settings.add(lblNewLabel_1_1_1_1_1_1);
		
		txtCtrlS = new JTextField();
		txtCtrlS.setText("ctrl + s");
		txtCtrlS.setBounds(175, 88, 86, 20);
		settings.add(txtCtrlS);
		txtCtrlS.setColumns(10);
		
		txtCtrlK = new JTextField();
		txtCtrlK.setText("ctrl + k");
		txtCtrlK.setColumns(10);
		txtCtrlK.setBounds(400, 88, 86, 20);
		settings.add(txtCtrlK);
		
		txtCtrlM = new JTextField();
		txtCtrlM.setText("ctrl + m");
		txtCtrlM.setColumns(10);
		txtCtrlM.setBounds(175, 129, 86, 20);
		settings.add(txtCtrlM);
		
		txtCtrl = new JTextField();
		txtCtrl.setText("ctrl + arrow up");
		txtCtrl.setColumns(10);
		txtCtrl.setBounds(428, 129, 86, 20);
		settings.add(txtCtrl);
		
		txtCtrlArrow = new JTextField();
		txtCtrlArrow.setText("ctrl + arrow down");
		txtCtrlArrow.setColumns(10);
		txtCtrlArrow.setBounds(175, 169, 104, 20);
		settings.add(txtCtrlArrow);
		
		txtCtrlL = new JTextField();
		txtCtrlL.setText("ctrl + t");
		txtCtrlL.setColumns(10);
		txtCtrlL.setBounds(402, 169, 86, 20);
		settings.add(txtCtrlL);
		
		JLabel lblTagsTemplate = new JLabel("Custom Tags");
		lblTagsTemplate.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lblTagsTemplate.setBounds(24, 200, 135, 24);
		settings.add(lblTagsTemplate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 235, 280, 103);
		settings.add(scrollPane);
		
		JTextArea txtrworkingstudingrelax = new JTextArea();
		scrollPane.setViewportView(txtrworkingstudingrelax);
		txtrworkingstudingrelax.setText("[Working]\r\n[Studing]\r\n[Relax]\r\n[Working in own project]\r\n[Offline]\r\n[Wander online]");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(34, 398, 443, 108);
		settings.add(scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		
		textField = new JTextField();
		scrollPane_1.setColumnHeaderView(textField);
		textField.setColumns(10);
		
		JLabel lblTagsTemplate_2 = new JLabel("Tags Template");
		lblTagsTemplate_2.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lblTagsTemplate_2.setBounds(24, 363, 135, 24);
		settings.add(lblTagsTemplate_2);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Get tag template by default");
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		rdbtnNewRadioButton.setBounds(289, 193, 166, 23);
		settings.add(rdbtnNewRadioButton);
		
		JButton btnNewButton_1 = new JButton("Set template");
		btnNewButton_1.setBounds(34, 509, 358, 23);
		settings.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Done!");
		lblNewLabel_2.setLabelFor(btnNewButton_1);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(419, 510, 60, 22);
		settings.add(lblNewLabel_2);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNewButton) {
			String text = textTesting.getText();
			String newText = text.replace("\n", "\\n");
			
			textTesting2.setText(ReadLastLine());
//			textTesting2.setText(newText);
		}
		else if(e.getSource() == btnStop) {
			String text = textTesting2.getText();
			String newText = text.replace("\\n", "\n");
			
			textTesting.setText(newText);
		}
	}
	
	private String ReadLastLine() {
		String info = "";
		
		try {
			Path filePath = Paths.get("./Data/data.csv");
			if(!Files.exists(filePath))
				throw new FileNotFoundException("The file data.csv doesnt exist in the directory Data");
			
			List<String> lines = Files.readAllLines(filePath);
			
			if (!lines.isEmpty()) {
				String lastLine = lines.get(lines.size() - 1);
				String[] lineSplited = lastLine.split(";");
				String tag = "";
				String description = "";
				
				for (String part : lineSplited) {
					if(part.contains("Tag:"))
						tag = part;
					else if(part.contains("Description:"))
						description = part;
				}
				
				info = tag + " - " + description;
			} else
				info = "History clean";
			
		} catch (Exception e) {
			System.out.println("An error occurred while reading the file: " + e.getMessage());
		}
		
		return info;
	}
}

package src;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.Cursor;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import java.awt.Color;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {
	private JTextField txtSavekb;
	private JTextField txtSkipkb;
	private JTextField txtBrowsingkb;
	private JTextField txtPreviouskb;
	private JTextField txtLatestkb;
	private JTextField txtTemplatekb;
	private JTextField txtActualTag;
	private JButton btnStop;
	private JButton btnStart;
	private JComboBox<String> comboTime;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
		setBackground(new Color(0, 0, 0));
		getContentPane().setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Tack your time");
		setBounds(100, 100, 560, 625);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255));
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel home = new JPanel();
		tabbedPane.addTab("Home", null, home, null);
		tabbedPane.setEnabledAt(0, true);
		home.setLayout(null);
		
		btnStart = new JButton("Start");
		btnStart.setBounds(10, 25, 230, 50);
		home.add(btnStart);
		btnStart.addActionListener(this);
		
		btnStop = new JButton("Stop");
		btnStop.setBounds(296, 25, 230, 50);
		home.add(btnStop);
		btnStop.addActionListener(this);
		
		JLabel lblHistory = new JLabel("History:");
		lblHistory.setFont(new Font("Leelawadee UI", Font.PLAIN, 20));
		lblHistory.setBounds(10, 98, 96, 29);
		home.add(lblHistory);
		
		JScrollPane scrollPaneHistory = new JScrollPane();
		scrollPaneHistory.setBounds(10, 138, 516, 409);
		home.add(scrollPaneHistory);
		
		JTextArea txtHistorial = new JTextArea();
		txtHistorial.setFont(new Font("Verdana", Font.PLAIN, 11));
		txtHistorial.setAutoscrolls(false);
		txtHistorial.setText("import java.util.regex.Pattern;\r\nimport java.util.regex.Matcher;\r\n\r\npublic class StringFormatMatcher {\r\n    public static void main(String[] args) {\r\n        String input = \"Date: 2023-07-19;Time: 10:30 AM;Tag: Meeting;Description: Discuss project updates\";\r\n        String format = \"Date: %s;Time: %s;Tag: %s;Description: %s\";\r\n\r\n        // Escape special characters in the format string\r\n        String escapedFormat = format.replaceAll(\"%\", \"%%\");\r\n\r\n        // Create a regular expression from the escaped format string\r\n        String regex = escapedFormat.replaceFirst(\"\\\\\\\\s\", \"\\\\\\\\S+\");\r\n\r\n        // Compile the regular expression pattern\r\n        Pattern pattern = Pattern.compile(regex);\r\n\r\n        // Match the input against the pattern\r\n        Matcher matcher = pattern.matcher(input);\r\n\r\n        // Check if the input matches the format\r\n        boolean matchesFormat = matcher.matches();\r\n\r\n        // Print the result\r\n        if (matchesFormat) {\r\n            System.out.println(\"Input matches the expected format.\");\r\n        } else {\r\n            System.out.println(\"Input does not match the expected format.\");\r\n        }\r\n    }\r\n}\r\n");
		scrollPaneHistory.setViewportView(txtHistorial);
		txtHistorial.setVerifyInputWhenFocusTarget(false);
		txtHistorial.setFocusTraversalKeysEnabled(false);
		txtHistorial.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtHistorial.setEditable(false);
		
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
		
		JLabel lblSave = new JLabel("Save and close:");
		lblSave.setBounds(34, 91, 89, 14);
		settings.add(lblSave);
		
		JLabel lblSetDisplay = new JLabel("Set Display Time:");
		lblSetDisplay.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lblSetDisplay.setBounds(24, 21, 166, 24);
		settings.add(lblSetDisplay);
		
		comboTime = new JComboBox<String>();
		comboTime.setModel(new DefaultComboBoxModel<String>(new String[] {"15 min", "20 min", "25 min", "30 min", "35 min", "40 min", "45 min", "50 min", "55 min", "60 min"}));
		comboTime.setFont(new Font("Lucida Console", Font.PLAIN, 13));
		comboTime.setBounds(284, 22, 202, 22);
		settings.add(comboTime);
		
		JLabel lblKeyboards = new JLabel("Keyboard Shortcuts");
		lblKeyboards.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lblKeyboards.setBounds(24, 56, 185, 24);
		settings.add(lblKeyboards);
		
		JLabel lblSkip = new JLabel("Skip and close:");
		lblSkip.setBounds(295, 91, 89, 14);
		settings.add(lblSkip);
		
		JLabel lblBrowsing = new JLabel("Browsing the history:");
		lblBrowsing.setBounds(34, 132, 147, 14);
		settings.add(lblBrowsing);
		
		JLabel lblPrevious = new JLabel("Get 2 previous description:");
		lblPrevious.setBounds(295, 132, 147, 14);
		settings.add(lblPrevious);
		
		JLabel lblLatest = new JLabel("Get latest description:");
		lblLatest.setBounds(34, 172, 119, 14);
		settings.add(lblLatest);
		
		JLabel lblTemplate = new JLabel("Get tag template:");
		lblTemplate.setBounds(295, 172, 97, 14);
		settings.add(lblTemplate);
		
		txtSavekb = new JTextField();
		txtSavekb.setText("ctrl + s");
		txtSavekb.setBounds(175, 88, 86, 20);
		settings.add(txtSavekb);
		txtSavekb.setColumns(10);
		
		txtSkipkb = new JTextField();
		txtSkipkb.setText("ctrl + k");
		txtSkipkb.setColumns(10);
		txtSkipkb.setBounds(400, 88, 86, 20);
		settings.add(txtSkipkb);
		
		txtBrowsingkb = new JTextField();
		txtBrowsingkb.setText("ctrl + m");
		txtBrowsingkb.setColumns(10);
		txtBrowsingkb.setBounds(175, 129, 86, 20);
		settings.add(txtBrowsingkb);
		
		txtPreviouskb = new JTextField();
		txtPreviouskb.setText("ctrl + arrow up");
		txtPreviouskb.setColumns(10);
		txtPreviouskb.setBounds(428, 129, 86, 20);
		settings.add(txtPreviouskb);
		
		txtLatestkb = new JTextField();
		txtLatestkb.setText("ctrl + arrow down");
		txtLatestkb.setColumns(10);
		txtLatestkb.setBounds(175, 169, 104, 20);
		settings.add(txtLatestkb);
		
		txtTemplatekb = new JTextField();
		txtTemplatekb.setText("ctrl + t");
		txtTemplatekb.setColumns(10);
		txtTemplatekb.setBounds(402, 169, 86, 20);
		settings.add(txtTemplatekb);
		
		JLabel lblCustomTags = new JLabel("Custom Tags");
		lblCustomTags.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lblCustomTags.setBounds(24, 200, 135, 24);
		settings.add(lblCustomTags);
		
		JScrollPane scrollPaneTags = new JScrollPane();
		scrollPaneTags.setBounds(34, 235, 358, 103);
		settings.add(scrollPaneTags);
		
		JTextArea txtTags = new JTextArea();
		scrollPaneTags.setViewportView(txtTags);
		txtTags.setText("[Working]\r\n[Studing]\r\n[Relax]\r\n[Working in own project]\r\n[Offline]\r\n[Wander online]");
		
		JScrollPane scrollPaneTemplate = new JScrollPane();
		scrollPaneTemplate.setBounds(34, 398, 443, 108);
		settings.add(scrollPaneTemplate);
		
		JTextArea txtTemplate = new JTextArea();
		scrollPaneTemplate.setViewportView(txtTemplate);
		
		txtActualTag = new JTextField();
		scrollPaneTemplate.setColumnHeaderView(txtActualTag);
		txtActualTag.setColumns(10);
		
		JLabel lblTagsTemplate = new JLabel("Tags Template");
		lblTagsTemplate.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lblTagsTemplate.setBounds(24, 363, 135, 24);
		settings.add(lblTagsTemplate);
		
		JRadioButton rdGetTemplate = new JRadioButton("Get tag template by default");
		rdGetTemplate.setFont(new Font("Tahoma", Font.PLAIN, 9));
		rdGetTemplate.setBounds(289, 193, 166, 23);
		settings.add(rdGetTemplate);
		
		JButton btnSetTemplate = new JButton("Set template");
		btnSetTemplate.setBounds(34, 509, 358, 23);
		settings.add(btnSetTemplate);
		
		JLabel lblResult = new JLabel("Done!");
		lblResult.setLabelFor(btnSetTemplate);
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblResult.setBounds(419, 510, 60, 22);
		settings.add(lblResult);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnStart) {
			
			Popup popup = new Popup();
			popup.setVisible(true);
		}
		else if(e.getSource() == btnStop) {
			
		}
	}
	
	public int getTime() {
		String getItemString = comboTime.getSelectedItem().toString();
		String splited = getItemString.split(" ")[0];
		int time = Integer.parseInt(splited);
		
		return time;
	}
}

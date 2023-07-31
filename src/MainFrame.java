package src;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {
	private JTextField txtSavekb;
	private JTextField txtSkipkb;
	private JTextField txtPreviouskb;
	private JTextField txtLatestkb;
	private JTextField txtTemplatekb;
	private JButton btnStop;
	private JButton btnStart;
	private JTextArea txtTags;
	private JComboBox<MyItems> comboTags;
	MyItems myItems = new MyItems();
	private JButton btnSetTemplate;
	private JTextArea txtTemplate;
	private JComboBox<String> comboTime;
	private JButton btnSetTags;
	private JButton btnRefresh;
	private JTextArea txtHistorial;
	private JLabel lblPopupStatus;
	public JLabel lblTimeStatus;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage("stuff/icon.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(0, 0, 0));
		getContentPane().setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Time Dial");
		setBounds(100, 100, 560, 625);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255));
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel home = new JPanel();
		tabbedPane.addTab("Home", null, home, null);
		tabbedPane.setEnabledAt(0, true);
		home.setLayout(null);
		
		btnStart = new JButton("Start");
		btnStart.setBounds(10, 56, 190, 35);
		home.add(btnStart);
		btnStart.addActionListener(this);
		
		btnStop = new JButton("Stop");
		btnStop.setBounds(10, 8, 95, 24);
		home.add(btnStop);
		btnStop.addActionListener(this);
		
		JScrollPane scrollPaneHistory = new JScrollPane();
		scrollPaneHistory.setBounds(10, 110, 516, 437);
		home.add(scrollPaneHistory);
		
		txtHistorial = new JTextArea("The history data will be print here.");
		txtHistorial.setFont(new Font("Verdana", Font.PLAIN, 11));
		txtHistorial.setAutoscrolls(false);
		scrollPaneHistory.setViewportView(txtHistorial);
		txtHistorial.setVerifyInputWhenFocusTarget(false);
		txtHistorial.setFocusTraversalKeysEnabled(false);
		txtHistorial.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtHistorial.setEditable(false);
		
		JPanel panel = new JPanel();
		scrollPaneHistory.setColumnHeaderView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {516};
		gbl_panel.rowHeights = new int[] {40, 0};
		gbl_panel.columnWeights = new double[]{0.0};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		panel_1.setLayout(null);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setMargin(new Insets(2, 7, 2, 7));
		btnRefresh.setFont(new Font("Arial", Font.PLAIN, 10));
		btnRefresh.setBounds(432, 9, 64, 21);
		btnRefresh.addActionListener(this);
		panel_1.add(btnRefresh);
		
		JLabel lblHistory = new JLabel("History");
		lblHistory.setBounds(10, 7, 481, 25);
		panel_1.add(lblHistory);
		lblHistory.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistory.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHistory.setFont(new Font("Arial Black", Font.PLAIN, 20));
		
		lblPopupStatus = new JLabel("Popup is turn off");
		lblPopupStatus.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblPopupStatus.setHorizontalTextPosition(SwingConstants.LEADING);
		lblPopupStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblPopupStatus.setBounds(178, 10, 180, 18);
		home.add(lblPopupStatus);
		

		lblTimeStatus = new JLabel();
		lblTimeStatus.setHorizontalTextPosition(SwingConstants.LEADING);
		lblTimeStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeStatus.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblTimeStatus.setBounds(145, 30, 250, 18);
		home.add(lblTimeStatus);
		
		JLabel lblSetDisplay = new JLabel("every ->");
		lblSetDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetDisplay.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lblSetDisplay.setBounds(210, 61, 96, 24);
		home.add(lblSetDisplay);
		
		comboTime = new JComboBox<String>();
		comboTime.setModel(new DefaultComboBoxModel<String>(new String[] {"15 min", "20 min", "25 min", "30 min", "35 min", "40 min", "45 min", "50 min", "55 min", "60 min"}));
		comboTime.setFont(new Font("Lucida Console", Font.PLAIN, 13));
		comboTime.setBounds(316, 56, 210, 35);
		home.add(comboTime);
		
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
		lblSave.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSave.setBounds(20, 57, 89, 14);
		settings.add(lblSave);
		
		JLabel lblKeyboards = new JLabel("Keyboard Shortcuts");
		lblKeyboards.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lblKeyboards.setBounds(10, 22, 185, 24);
		settings.add(lblKeyboards);
		
		JLabel lblSkip = new JLabel("Skip and close:");
		lblSkip.setFont(new Font("Arial", Font.PLAIN, 11));
		lblSkip.setBounds(281, 57, 89, 14);
		settings.add(lblSkip);
		
		JLabel lblPrevious = new JLabel("Get previous data:");
		lblPrevious.setFont(new Font("Arial", Font.PLAIN, 11));
		lblPrevious.setBounds(20, 98, 109, 14);
		settings.add(lblPrevious);
		
		JLabel lblLatest = new JLabel("Get latest data:");
		lblLatest.setFont(new Font("Arial", Font.PLAIN, 11));
		lblLatest.setBounds(281, 98, 97, 14);
		settings.add(lblLatest);
		
		JLabel lblTemplate = new JLabel("Get tag template:");
		lblTemplate.setFont(new Font("Arial", Font.PLAIN, 11));
		lblTemplate.setBounds(20, 138, 97, 14);
		settings.add(lblTemplate);
		
		txtSavekb = new JTextField();
		txtSavekb.setText("ctrl + s");
		txtSavekb.setBounds(125, 54, 86, 20);
		settings.add(txtSavekb);
		txtSavekb.setColumns(10);
		
		txtSkipkb = new JTextField();
		txtSkipkb.setText("ctrl + k");
		txtSkipkb.setColumns(10);
		txtSkipkb.setBounds(386, 54, 86, 20);
		settings.add(txtSkipkb);
		
		txtPreviouskb = new JTextField();
		txtPreviouskb.setText("ctrl + arrow up");
		txtPreviouskb.setColumns(10);
		txtPreviouskb.setBounds(125, 95, 86, 20);
		settings.add(txtPreviouskb);
		
		txtLatestkb = new JTextField();
		txtLatestkb.setText("ctrl + arrow down");
		txtLatestkb.setColumns(10);
		txtLatestkb.setBounds(388, 95, 104, 20);
		settings.add(txtLatestkb);
		
		txtTemplatekb = new JTextField();
		txtTemplatekb.setText("ctrl + t");
		txtTemplatekb.setColumns(10);
		txtTemplatekb.setBounds(127, 135, 86, 20);
		settings.add(txtTemplatekb);
		
		JLabel lblCustomTags = new JLabel("Custom Tags");
		lblCustomTags.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lblCustomTags.setBounds(10, 182, 135, 24);
		settings.add(lblCustomTags);
		
		JScrollPane scrollPaneTags = new JScrollPane();
		scrollPaneTags.setBounds(20, 217, 495, 100);
		settings.add(scrollPaneTags);
		
		txtTags = new JTextArea();
		scrollPaneTags.setViewportView(txtTags);
//		txtTags.setText("Studying\r\nRelax\r\nOffline\r\nWander online\r\nWorking in own project\r\nWorking");
		txtTags.setText(DataManager.readTemplates(DataManager.TAG));
		
		JScrollPane scrollPaneTemplate = new JScrollPane();
		scrollPaneTemplate.setBounds(20, 400, 495, 108);
		settings.add(scrollPaneTemplate);
		
		txtTemplate = new JTextArea();
		scrollPaneTemplate.setViewportView(txtTemplate);
		
		comboTags = new JComboBox<MyItems>();
		comboTags.addActionListener(this);
		myItems.setItems(comboTags);
		scrollPaneTemplate.setColumnHeaderView(comboTags);
		
		JLabel lblTagsTemplate = new JLabel("Tags Template");
		lblTagsTemplate.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lblTagsTemplate.setBounds(10, 365, 135, 24);
		settings.add(lblTagsTemplate);
		
		JRadioButton rdGetTemplate = new JRadioButton("Get tag template by default");
		rdGetTemplate.setFont(new Font("Arial", Font.PLAIN, 11));
		rdGetTemplate.setBounds(281, 134, 166, 23);
		settings.add(rdGetTemplate);
		
		btnSetTemplate = new JButton("Set template");
		btnSetTemplate.setBounds(20, 511, 358, 23);
		settings.add(btnSetTemplate);
		btnSetTemplate.addActionListener(this);
		
		JLabel lblResult = new JLabel("Done!");
		lblResult.setLabelFor(btnSetTemplate);
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblResult.setBounds(405, 512, 60, 22);
		settings.add(lblResult);
		
		btnSetTags = new JButton("Set tags");
		btnSetTags.setBounds(20, 320, 495, 23);
		settings.add(btnSetTags);
		btnSetTags.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnStart) {
			
			Popup popup = new Popup();
			popup.setVisible(true);
			
			lblPopupStatus.setText("Popup is turn on");
//			lblTimeStatus.setText("Time to apperd: 00:00");
		}
		else if(e.getSource() == btnStop) {
//			getTags();
//			MyItems currentTag = (MyItems) comboTags.getSelectedItem();
//			System.out.println(currentTag.getName() + " - " + currentTag.getTemplate());
			
			lblPopupStatus.setText("Popup is turn off");
		}
		else if(e.getSource() == comboTags) {
			MyItems myItems = (MyItems) comboTags.getSelectedItem();
			txtTemplate.setText(myItems.getTemplate().replace("\\n", "\n"));
		}
		else if(e.getSource() == btnSetTemplate) {
//			setTemplate();
			setTemplateToFile();
		}
		else if(e.getSource() == btnSetTags) {
			setTagToFile();
		}
		else if(e.getSource() == btnRefresh) {
			txtHistorial.setText(DataManager.readHistory());
		}
	}

	public int getTime() {
		String getItemString = comboTime.getSelectedItem().toString();
		String splited = getItemString.split(" ")[0];
		int time = Integer.parseInt(splited);
		
		return time;
	}
	
	public String[] getTags() {
		String[] txtSplited = txtTags.getText().split("\n");
//		System.out.println(txtSplited.length);
		return txtSplited;
	}
	
	public void setTemplate() {
		MyItems currentTag = (MyItems) comboTags.getSelectedItem();
		String txt = txtTemplate.getText().replace("\n", "\\n");
		
		currentTag.setTemplate(txt);
		
		System.out.println(currentTag.getName() + " - " + currentTag.getTemplate());
	}

	
	private void setTemplateToFile() {
		MyItems currentTag = (MyItems) comboTags.getSelectedItem();
		String template = txtTemplate.getText().replace("\n", "\\n");

		//TODO: to not create the reset button, although i think i have to
		currentTag.setTemplate(template);
		
		List<String> lines = DataManager.linesTemplate();
		
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			String[] split = line.split(";");
			
			if(split[0].equals(currentTag.getName())) {
				lines.remove(i);
				lines.add(i, String.join(";", split[0], template));
				DataManager.writeTemplate(lines);
			}
		}
	}
	
	private void setTagToFile() {
	    List<String> lines = DataManager.linesTemplate();
	    String[] tags = getTags();

	    for (String tag : tags) {
	        boolean tagExists = false;

	        for (String line : lines) {
	            String[] split = line.split(";");
	            if (split[0].equals(tag)) {
	                tagExists = true;
	                break;
	            }
	        }

	        if (!tagExists) {
	            lines.add(String.join(";", tag, "default template"));
	        }
	    }

	    DataManager.writeTags(lines);
	}
}

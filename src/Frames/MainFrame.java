package src.Frames;

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

import src.MyItems;
import src.Singleton;
import src.TimerHandler;
import src.Data.DataManager;
import src.Data.TaggedManager;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {
	MyItems myItems = new MyItems();
	TimerHandler timerHandler = new TimerHandler();
	private JTextField txtSavekb;
	private JTextField txtSkipkb;
	private JTextField txtPreviouskb;
	private JTextField txtLatestkb;
	private JTextField txtTemplatekb;
	private JButton btnStop;
	private JButton btnStart;
	private JComboBox<MyItems> comboTags;
	private JTextArea txtTemplate;
	private JComboBox<String> comboTime;
	private JButton btnSetTags;
	private JButton btnRefresh;
	private JTextArea txtHistorial;
	private JLabel lblPopupStatus;
	private JLabel lblTimeStatus;
	private JButton btnStartPeriod;
	private JPanel settings;
	public JTabbedPane tabbedPane;
	private JLabel lblResult;
	private JTextField textFieldTag;
	private JButton btnReleased;
	private JComboBox<String> comboOperations;
	private static String CREATE = "Create", UPDATE = "Update", DELETE = "Delete";

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
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255));
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel home = new JPanel();
		tabbedPane.addTab("Home", null, home, null);
		tabbedPane.setEnabledAt(0, true);
		home.setLayout(null);

		btnStartPeriod = new JButton("Start");
		btnStartPeriod.setBounds(110, 56, 90, 35);
		home.add(btnStartPeriod);
		btnStartPeriod.addActionListener(this);
		
		btnStart = new JButton("Start now");
		btnStart.setMargin(new Insets(2, 7, 2, 7));
		btnStart.setBounds(10, 56, 90, 35);
		home.add(btnStart);
		btnStart.addActionListener(this);
		
		btnStop = new JButton("Stop");
		btnStop.setBounds(10, 8, 90, 35);
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
		lblPopupStatus.setBounds(194, 10, 150, 18);
		home.add(lblPopupStatus);
		
		lblTimeStatus = new JLabel();
		lblTimeStatus.setHorizontalTextPosition(SwingConstants.LEADING);
		lblTimeStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeStatus.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblTimeStatus.setBounds(182, 30, 175, 18);
		home.add(lblTimeStatus);
		timerHandler.setLabel(lblTimeStatus);
		
		JLabel lblSetDisplay = new JLabel("every ->");
		lblSetDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetDisplay.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lblSetDisplay.setBounds(224, 61, 90, 24);
		home.add(lblSetDisplay);
		
		comboTime = new JComboBox<String>();
		comboTime.setModel(new DefaultComboBoxModel<String>(new String[] {"15 min", "20 min", "25 min", "30 min", "35 min", "40 min", "45 min", "50 min", "55 min", "60 min"}));
		comboTime.setFont(new Font("Lucida Console", Font.PLAIN, 13));
		comboTime.setBounds(336, 56, 190, 35);
		home.add(comboTime);
		
		JPanel dayReport = new JPanel();
		tabbedPane.addTab("Day Report", null, dayReport, null);
		
		JPanel weekReport = new JPanel();
		tabbedPane.addTab("Week Report", null, weekReport, null);
		tabbedPane.setEnabledAt(2, true);
		
		JPanel monthReport = new JPanel();
		tabbedPane.addTab("Month Report", null, monthReport, null);
		
		settings = new JPanel();
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
		lblCustomTags.setBounds(10, 191, 109, 24);
		settings.add(lblCustomTags);
		
		JScrollPane scrollPaneTemplate = new JScrollPane();
		scrollPaneTemplate.setBounds(20, 345, 492, 103);
		settings.add(scrollPaneTemplate);
		
		txtTemplate = new JTextArea();
		scrollPaneTemplate.setViewportView(txtTemplate);
		
		lblResult = new JLabel();
		lblResult.setText("34234");
		lblResult.setHorizontalTextPosition(SwingConstants.CENTER);
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblResult.setBounds(169, 494, 185, 22);
		settings.add(lblResult);
		
		btnSetTags = new JButton("Set tags");
		btnSetTags.setBounds(387, 524, 125, 23);
		settings.add(btnSetTags);
		
		comboOperations = new JComboBox<String>();
		comboOperations.setModel(new DefaultComboBoxModel<String>(new String[] {CREATE, UPDATE, DELETE}));
		comboOperations.setBounds(205, 226, 80, 31);
		comboOperations.setSelectedIndex(0);
		settings.add(comboOperations);
		comboOperations.addActionListener(this);
		
		btnReleased = new JButton("Released");
		btnReleased.setBounds(20, 459, 492, 24);
		settings.add(btnReleased);
		btnReleased.addActionListener(this);
		
		JLabel lblSelectOperation = new JLabel("Select Operation:");
		lblSelectOperation.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lblSelectOperation.setBounds(20, 226, 159, 31);
		settings.add(lblSelectOperation);
		
		JLabel lblCustomTag = new JLabel("New Tag Name");
		lblCustomTag.setBounds(20, 268, 97, 14);
		settings.add(lblCustomTag);
		
		JLabel lblCustomTemplate = new JLabel("New Template");
		lblCustomTemplate.setBounds(20, 320, 89, 14);
		settings.add(lblCustomTemplate);
		
		textFieldTag = new JTextField();
		textFieldTag.setBounds(20, 289, 149, 20);
		settings.add(textFieldTag);
		textFieldTag.setColumns(10);
		
		comboTags = new JComboBox<MyItems>();
		comboTags.setBounds(293, 287, 219, 24);
		settings.add(comboTags);
		comboTags.addActionListener(this);
		myItems.setItems(comboTags);
		
		
		btnSetTags.addActionListener(this);
		Singleton.getInstance().setFrame(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnStart) {
			share(false);
			lblPopupStatus.setText("Popup is turn on");
		}
		else if(e.getSource() == btnStartPeriod) {
			share(true);
			lblPopupStatus.setText("Popup is turn on");
		}
		else if(e.getSource() == btnStop) {
			timerHandler.stop();
			timerHandler.setPeriod(0);
			lblPopupStatus.setText("Popup is turn off");
		}
		else if(e.getSource() == comboTags) {
			String item = comboOperations.getSelectedItem().toString();
			if (item == UPDATE) {
				MyItems myItems = (MyItems) comboTags.getSelectedItem();
				
				textFieldTag.setText(myItems.toString());
				txtTemplate.setText(myItems.getTemplate().replace("\\n", "\n"));
			}
		}
		else if(e.getSource() == btnSetTags) {
			DataManager.saveTags(getArrayTags());
			comboTags.removeAllItems();
			myItems.setItems(comboTags);
			TimerHandler.temporalText(1500, lblResult, "Tags stored in the file");
		}
		else if(e.getSource() == btnRefresh) {
			txtHistorial.setText(DataManager.readHistory());
		}
		else if(e.getSource() == comboOperations) {
			releasedComboOperations();
		}
		else if(e.getSource() == btnReleased) {
			releasedOperations();
		}
//		DataManager.saveTemplate(comboTags, txtTemplate);
//		TimerHandler.temporalText(1500, lblResult, "Template stored in the file");
	}

	private void releasedComboOperations() {
		String item = comboOperations.getSelectedItem().toString();
		
		if (item == CREATE) {
			textFieldTag.setEnabled(true);
			txtTemplate.setEnabled(true);
			comboTags.setEnabled(false);
		}
		else if (item == UPDATE) {
			textFieldTag.setEnabled(true);
			txtTemplate.setEnabled(true);
			comboTags.setEnabled(true);
		}
		else if (item == DELETE) {
			textFieldTag.setEnabled(false);
			txtTemplate.setEnabled(false);
			comboTags.setEnabled(true);
		}
		
		btnReleased.setText("Released " + item);
	}

	// TODO Make a new Class to put all this methods
	private void releasedOperations() {
		String item = comboOperations.getSelectedItem().toString();
		
		if(item == CREATE) {
			String tag = textFieldTag.getText();
			String template = txtTemplate.getText().replace("\n", "\\n");
			if(!tag.isEmpty() && !template.isEmpty())
				TaggedManager.createLine(tag, template);
			
		}else if (item == UPDATE) {
			int index = comboTags.getSelectedIndex();
			String tag = textFieldTag.getText();
			String template = txtTemplate.getText().replace("\n", "\\n");
			if(!tag.isEmpty() && !template.isEmpty())
				TaggedManager.updateLine(tag, template, index);
			
		}else if (item == DELETE) {
			int index = comboTags.getSelectedIndex();
			TaggedManager.deleteLine(index);
			
			//TODO: make a method to update all comboBox with the new items
			comboTags.removeAllItems();
			myItems.setItems(comboTags);
		}
	}

	public void share(Boolean usePeriod) {
		if(usePeriod)
			timerHandler.setPeriod(getComboxPeriod());
		timerHandler.start();
	}

	private int getComboxPeriod() {
		String itemString = comboTime.getSelectedItem().toString();
		String splited = itemString.split(" ")[0]; //return the number of the item
		return Integer.parseInt(splited);
	}
	
	public String[] getArrayTags() {
//		String[] txtSplited = txtTags.getText().split("\n");
		String[] txtSplited = "fwef".split("\n");
		return txtSplited;
	}
}

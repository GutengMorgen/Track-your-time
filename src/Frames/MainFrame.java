package src.Frames;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.DefaultComboBoxModel;
import java.awt.Cursor;
import java.awt.Dimension;
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
import java.awt.Rectangle;
import java.awt.SystemTray;
import java.awt.Toolkit;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

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
	private JButton btnRefresh;
	private JLabel lblHistory;

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
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		MainFrame mainFrame = this;
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	if(SystemTray.isSupported())
            		TrayHandler.minimizeToTray(mainFrame);
            	else
            		mainFrame.dispose();
            }
			@Override
			public void windowIconified(WindowEvent e) {
				if(SystemTray.isSupported())
            		TrayHandler.minimizeToTray(mainFrame);
            	else
            		mainFrame.dispose();
			}
        });
		
		setBackground(new Color(0, 0, 0));
		getContentPane().setBackground(new Color(255, 255, 255));
		setResizable(false);
		setTitle("Time Dial");
		setBounds(100, 100, 560, 625);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255));
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel home = new JPanel();
		home.setBackground(new Color(253, 253, 255));
		tabbedPane.addTab("Home", null, home, null);
		tabbedPane.setEnabledAt(0, true);
		home.setLayout(null);

		btnStartPeriod = new JButton("Start");
		btnStartPeriod.setBounds(110, 56, 90, 35);
		btnStartPeriod.setFont(new Font("Lucida Console", Font.PLAIN, 17));
		btnStartPeriod.setForeground(new Color(248, 248, 241));
		btnStartPeriod.setBackground(new Color(26, 18, 11));
		home.add(btnStartPeriod);
		btnStartPeriod.addActionListener(this);
		
		btnStart = new JButton("Now");
		btnStart.setMargin(new Insets(2, 7, 2, 7));
		btnStart.setBounds(10, 56, 90, 35);
		btnStart.setFont(new Font("Lucida Console", Font.PLAIN, 17));
		btnStart.setForeground(new Color(248, 248, 241));
		btnStart.setBackground(new Color(26, 18, 11));
		home.add(btnStart);
		btnStart.addActionListener(this);
		
		btnStop = new JButton("Stop");
		btnStop.setBounds(10, 8, 90, 35);
		btnStop.setFont(new Font("Lucida Console", Font.PLAIN, 17));
		btnStop.setForeground(new Color(248, 248, 241));
		btnStop.setBackground(new Color(26, 18, 11));
		home.add(btnStop);
		btnStop.addActionListener(this);
		
		JScrollPane scrollPaneHistory = new JScrollPane();
		scrollPaneHistory.setBorder(null);
		scrollPaneHistory.setBounds(11, 110, 516, 437);
		home.add(scrollPaneHistory);
		
		txtHistorial = new JTextArea("The history data will be print here.");
		txtHistorial.setBackground(new Color(235, 235, 235));
		txtHistorial.setBorder(new CompoundBorder(new LineBorder(new Color(187, 187, 187)), new EmptyBorder(6, 6, 6, 6)));
		txtHistorial.setFont(new Font("Verdana", Font.PLAIN, 11));
		txtHistorial.setAutoscrolls(false);
		scrollPaneHistory.setViewportView(txtHistorial);
		txtHistorial.setVerifyInputWhenFocusTarget(false);
		txtHistorial.setFocusTraversalKeysEnabled(false);
		txtHistorial.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtHistorial.setEditable(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(253, 253, 253));
		panel.setEnabled(false);
		panel.setDoubleBuffered(false);
		panel.setFocusable(false);
		panel.setFocusTraversalKeysEnabled(false);
		scrollPaneHistory.setColumnHeaderView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {445, 40};
		gbl_panel.rowHeights = new int[] {35, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblHistory = new JLabel("History");
		lblHistory.setHorizontalTextPosition(SwingConstants.CENTER);
		lblHistory.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistory.setFont(new Font("Arial Black", Font.PLAIN, 20));
		GridBagConstraints gbc_lblHistory = new GridBagConstraints();
		gbc_lblHistory.anchor = GridBagConstraints.WEST;
		gbc_lblHistory.insets = new Insets(0, 0, 0, 5);
		gbc_lblHistory.gridx = 0;
		gbc_lblHistory.gridy = 0;
		panel.add(lblHistory, gbc_lblHistory);
		
		btnRefresh = new JButton("Refresh");
		btnRefresh.setMargin(new Insets(2, 7, 2, 7));
		btnRefresh.setFont(new Font("Arial", Font.BOLD, 11));
		btnRefresh.setForeground(new Color(248, 248, 241));
		btnRefresh.setBackground(new Color(26, 18, 11));
		GridBagConstraints gbc_btnRefresh = new GridBagConstraints();
		gbc_btnRefresh.gridx = 1;
		gbc_btnRefresh.gridy = 0;
		panel.add(btnRefresh, gbc_btnRefresh);
		btnRefresh.addActionListener(this);
		
		lblPopupStatus = new JLabel("Popup is turn off");
		lblPopupStatus.setFont(new Font("Verdana", Font.PLAIN, 12));
		lblPopupStatus.setHorizontalTextPosition(SwingConstants.LEADING);
		lblPopupStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblPopupStatus.setBounds(209, 10, 120, 18);
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
		comboTime.setBorder(null);
		comboTime.setModel(new DefaultComboBoxModel<String>(new String[] {"15 min", "20 min", "25 min", "30 min", "35 min", "40 min", "45 min", "50 min", "55 min", "60 min"}));
		comboTime.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		comboTime.setForeground(new Color(248, 248, 241));
		comboTime.setBackground(new Color(26, 18, 11));
		comboTime.setBounds(337, 56, 190, 35);
		home.add(comboTime);
		
		JPanel dayReport = new JPanel();
		tabbedPane.addTab("Day Report", null, dayReport, null);
		
		JPanel weekReport = new JPanel();
		tabbedPane.addTab("Week Report", null, weekReport, null);
		tabbedPane.setEnabledAt(2, true);
		
		JPanel monthReport = new JPanel();
		tabbedPane.addTab("Month Report", null, monthReport, null);
		
		settings = new JPanel();
		settings.setBackground(new Color(253, 253, 253));
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
		lblCustomTags.setBounds(10, 183, 109, 24);
		settings.add(lblCustomTags);
		
		JScrollPane scrollPaneTemplate = new JScrollPane();
		scrollPaneTemplate.setBorder(null);
		scrollPaneTemplate.setBounds(23, 345, 492, 118);
		settings.add(scrollPaneTemplate);
		
		txtTemplate = new JTextArea();
		txtTemplate.setBackground(new Color(235, 235, 235));
		txtTemplate.setBorder(new CompoundBorder(new LineBorder(new Color(187, 187, 187)), new EmptyBorder(6, 6, 6, 6)));
		txtTemplate.setFont(new Font("Verdana", Font.PLAIN, 13));
		scrollPaneTemplate.setViewportView(txtTemplate);
		
		lblResult = new JLabel();
		lblResult.setHorizontalTextPosition(SwingConstants.CENTER);
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblResult.setBounds(169, 525, 200, 22);
		settings.add(lblResult);
		
		comboOperations = new JComboBox<String>();
		comboOperations.setModel(new DefaultComboBoxModel<String>(new String[] {CREATE, UPDATE, DELETE}));
		comboOperations.setBounds(205, 218, 80, 31);
		comboOperations.setForeground(new Color(248, 248, 241));
		comboOperations.setBackground(new Color(26, 18, 11));
		settings.add(comboOperations);
		comboOperations.addActionListener(this);
		
		btnReleased = new JButton("Released");
		btnReleased.setFont(new Font("Lucida Console", Font.PLAIN, 17));
		btnReleased.setForeground(new Color(248, 248, 241));
		btnReleased.setBackground(new Color(26, 18, 11));
		btnReleased.setBounds(23, 474, 492, 40);
		settings.add(btnReleased);
		btnReleased.addActionListener(this);
		
		JLabel lblSelectOperation = new JLabel("Select Operation:");
		lblSelectOperation.setFont(new Font("Lucida Console", Font.PLAIN, 15));
		lblSelectOperation.setBounds(20, 218, 159, 31);
		settings.add(lblSelectOperation);
		
		JLabel lblCustomTag = new JLabel("New Tag Name");
		lblCustomTag.setBounds(20, 260, 97, 14);
		settings.add(lblCustomTag);
		
		JLabel lblCustomTemplate = new JLabel("New Template");
		lblCustomTemplate.setBounds(20, 320, 89, 14);
		settings.add(lblCustomTemplate);
		
		textFieldTag = new JTextField();
		textFieldTag.setBackground(new Color(235, 235, 235));
		textFieldTag.setBorder(new CompoundBorder(new LineBorder(new Color(187, 187, 187)), new EmptyBorder(0, 4, 0, 4)));
		textFieldTag.setFont(new Font("Verdana", Font.PLAIN, 12));
		textFieldTag.setBounds(20, 281, 175, 25);
		settings.add(textFieldTag);
		textFieldTag.setColumns(10);
		
		comboTags = new JComboBox<MyItems>();
		comboTags.setBounds(296, 281, 219, 25);
		comboTags.setForeground(new Color(248, 248, 241));
		comboTags.setBackground(new Color(26, 18, 11));
		settings.add(comboTags);
		comboTags.addActionListener(this);
		myItems.addItems(comboTags);
		
		comboOperations.setSelectedIndex(0);
		
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
			
			if (item == UPDATE || item == DELETE) {
				MyItems myItems = (MyItems) comboTags.getSelectedItem();
				textFieldTag.setText(myItems.toString());
				txtTemplate.setText(myItems.getTemplate().replace("\\n", "\n"));
			}
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
			
			if(!tag.isEmpty() && !template.isEmpty()) {
				String newLine = String.join(";", tag, template);
				TaggedManager.createLine(newLine);
				updateComboTags();
				TimerHandler.temporalText(1500, lblResult, "Tags stored in the file");
			}
			
		}else if (item == UPDATE) {
			int index = comboTags.getSelectedIndex();
			String tag = textFieldTag.getText();
			String template = txtTemplate.getText().replace("\n", "\\n");
			
			if(!tag.isEmpty() && !template.isEmpty()) {
				String newLine = String.join(";", tag, template);
				TaggedManager.updateLine(index, newLine);
				updateComboTags();
				TimerHandler.temporalText(1500, lblResult, "Updated Tag");
			}
			
		}else if (item == DELETE) {
			int index = comboTags.getSelectedIndex();
			TaggedManager.deleteLine(index);
			updateComboTags();
			TimerHandler.temporalText(1500, lblResult, "Tag removed from the file");
		}
	}
	
	private void updateComboTags() {
		comboTags.removeAllItems();
		myItems.addItems(comboTags);
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
}

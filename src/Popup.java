package src;

import java.awt.EventQueue;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class Popup extends JFrame implements ActionListener {
	final int WidthFrame = 480, HeightFrame = 290;
	ReadWriteData data = new ReadWriteData();
	Timer timer = new Timer();
	private JPanel contentPane;
	private JComboBox<String> comboTags;
	private JTextArea txtDescription;
	private JTextArea txtLastData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Popup frame = new Popup();
					frame.setVisible(true);
//					frame.timer.setDisplay(1);
//					frame.timer.start(frame);
					
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
		setUndecorated(true);
		setResizable(false);
		setAlwaysOnTop(true);
		
		setSize(465, 250);
		Dimension dimension = getSize();
		setLocation(getCoordinate(dimension, "x", 0.01), getCoordinate(dimension, "y", 0.35));

		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboTags = new JComboBox<String>();
		comboTags.setVerifyInputWhenFocusTarget(false);
		comboTags.setFont(new Font("Verdana", Font.BOLD, 13));
		comboTags.setBorder(new LineBorder(new Color(0, 0, 0)));
		comboTags.setLightWeightPopupEnabled(false);
		comboTags.setRequestFocusEnabled(false);
		comboTags.setFocusTraversalKeysEnabled(false);
		comboTags.setFocusable(false);
		comboTags.setForeground(new Color(248, 248, 241));
		comboTags.setBackground(new Color(26, 18, 11));
		comboTags.setBounds(324, 62, 129, 25);
		comboTags.setToolTipText("Select any tag");
		comboTags.setCursor(new Cursor(Cursor.HAND_CURSOR));
		comboTags.setModel(new DefaultComboBoxModel<String>(new String[] {"working", "studing", "relax"}));
		contentPane.add(comboTags);
		
		JButton btnSave = new JButton("SAVE (ctrl + s)");
		btnSave.setFont(new Font("Lucida Console", Font.BOLD, 17));
		btnSave.setForeground(new Color(248, 248, 241));
		btnSave.setBackground(new Color(26, 18, 11));
		btnSave.setFocusPainted(false);
		btnSave.setBounds(10, 195, 444, 44);
		btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(btnSave);
		btnSave.addActionListener(this);
		
		MouseAdapter mouseAdapter = new MouseAdapter() {
		    public void mouseReleased(MouseEvent e) {
		        openMainFrame();
		    }
		};
		
		JLabel lblViewReport = new JLabel("View report");
		lblViewReport.addMouseListener(mouseAdapter);
		lblViewReport.setForeground(new Color(26, 18, 11));
		lblViewReport.setHorizontalTextPosition(SwingConstants.CENTER);
		lblViewReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewReport.setFont(new Font("Lucida Console", Font.PLAIN, 10));
		lblViewReport.setBounds(10, 179, 79, 14);
		lblViewReport.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(lblViewReport);
		
		JLabel lblSettings = new JLabel("Settings");
		lblSettings.addMouseListener(mouseAdapter);
		lblSettings.setForeground(new Color(26, 18, 11));
		lblSettings.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblSettings.setFont(new Font("Lucida Console", Font.PLAIN, 10));
		lblSettings.setBounds(395, 178, 58, 14);
		lblSettings.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 92, 443, 75);
		contentPane.add(scrollPane);
		
		txtDescription = new JTextArea();
		txtDescription.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyReleased(KeyEvent e) {
//				
//			}
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e);
				if(e.getKeyCode() == 83 && e.getKeyCode() == 17) {
					System.out.println("save");
				}
			}
		});
		scrollPane.setViewportView(txtDescription);
		txtDescription.setLineWrap(true);
		txtDescription.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtDescription.setForeground(new Color(26, 18, 11));
		txtDescription.setBackground(new Color(235, 235, 235));
		txtDescription.setBorder(new CompoundBorder(new LineBorder(new Color(187, 187, 187)), new EmptyBorder(10, 10, 10, 10)));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(null);
		scrollPane_1.setFocusTraversalKeysEnabled(false);
		scrollPane_1.setFocusable(false);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(10, 32, 443, 22);
		contentPane.add(scrollPane_1);
		
		txtLastData = new JTextArea();
		txtLastData.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtLastData.setAutoscrolls(false);
		txtLastData.setForeground(new Color(115, 115, 115));
		txtLastData.setFont(new Font("Verdana", Font.PLAIN, 9));
		txtLastData.setDisabledTextColor(new Color(115, 115, 115));
		txtLastData.setRequestFocusEnabled(false);
		txtLastData.setFocusable(false);
		txtLastData.setEditable(false);
		scrollPane_1.setViewportView(txtLastData);
		txtLastData.setBorder(new CompoundBorder(new LineBorder(new Color(216, 216, 216)), new EmptyBorder(4, 5, 0, 5)));
		txtLastData.setBackground(new Color(240, 240, 240));
		txtLastData.setLineWrap(true);
		txtLastData.setFocusTraversalKeysEnabled(false);
		data.setTextField(txtLastData);
	}
	
	public void actionPerformed(ActionEvent e) {
		String getTextDescription = txtDescription.getText();
		String getTagString = comboTags.getSelectedItem().toString();
//		data.WriteData(getTagString, getTextDescription);
		data.writeData(getTagString, getTextDescription);
		
		//close the Pop up frame
		dispose();
		
		//start the timer
		timer.start(new Popup());
	}
	
	public int getCoordinate(Dimension frameDimension, String positionString, double percent) {
		double setCoord = 0;
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		
		if(positionString == "x") {
			setCoord = (screenDimension.getWidth() - frameDimension.getWidth()) * percent;
		}
		else if (positionString == "y") {
			setCoord = (screenDimension.getHeight() - frameDimension.getHeight()) * percent;
		}
		else {
			setCoord = 0;
		}
		
		return (int)setCoord;
	}
	
	
	public void openMainFrame() {
		MainFrame mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}
}

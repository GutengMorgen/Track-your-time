package src;

import java.awt.EventQueue;
import java.awt.event.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

@SuppressWarnings("serial")
public class Popup extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtLastData;
	private JComboBox comboTags;
	private JTextArea txtDescription;

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
		
		comboTags = new JComboBox();
		comboTags.setVerifyInputWhenFocusTarget(false);
//		comboTags.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
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
		comboTags.setModel(new DefaultComboBoxModel(new String[] {"working", "studing", "relax"}));
		contentPane.add(comboTags);
		
		txtDescription = new JTextArea();
		txtDescription.setFont(new Font("Verdana", Font.PLAIN, 12));
		txtDescription.setForeground(new Color(26, 18, 11));
		txtDescription.setBackground(new Color(235, 235, 235));
		txtDescription.setBorder(new CompoundBorder(new LineBorder(new Color(187, 187, 187)), new EmptyBorder(10, 10, 10, 10)));
		txtDescription.setMargin(new Insets(10, 10, 10, 10));
		txtDescription.setBounds(10, 92, 444, 75);
		contentPane.add(txtDescription);
		
		JButton btnSave = new JButton("SAVE (ctrl + s)");
		btnSave.setFont(new Font("Lucida Console", Font.BOLD, 18));
		btnSave.setForeground(new Color(248, 248, 241));
		btnSave.setBackground(new Color(26, 18, 11));
		btnSave.setFocusPainted(false);
		btnSave.setBounds(10, 195, 444, 44);
		contentPane.add(btnSave);
		btnSave.addActionListener(this);
		
		txtLastData = new JTextField();
		txtLastData.setForeground(new Color(0, 0, 0));
		txtLastData.setHorizontalAlignment(SwingConstants.LEFT);
		txtLastData.setText("tag: studing - description: afaefaw");
		txtLastData.setFont(new Font("Verdana", Font.PLAIN, 9));
		txtLastData.setDisabledTextColor(new Color(155, 155, 155));
		txtLastData.setRequestFocusEnabled(false);
		txtLastData.setFocusable(false);
		txtLastData.setFocusTraversalKeysEnabled(false);
		txtLastData.setBorder(new LineBorder(new Color(216, 216, 216)));
		txtLastData.setAutoscrolls(false);
		txtLastData.setBackground(new Color(240, 240, 240));
		txtLastData.setEnabled(false);
		txtLastData.setBounds(10, 28, 443, 23);
		contentPane.add(txtLastData);
		txtLastData.setColumns(10);
		
		JLabel lblViewReport = new JLabel("View report");
		lblViewReport.setForeground(new Color(26, 18, 11));
		lblViewReport.setHorizontalTextPosition(SwingConstants.CENTER);
		lblViewReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblViewReport.setFont(new Font("Lucida Console", Font.PLAIN, 10));
		lblViewReport.setBounds(10, 179, 79, 14);
		contentPane.add(lblViewReport);
		
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
		String getTextFiled = txtLastData.getText();
		String getTextDescription = txtDescription.getText();
		String getTagString = comboTags.getSelectedItem().toString();
		WriteData(getTagString, getTextDescription);
//		System.out.println("last update: " + getTextFiled + "\ntext description: " + getTextDescription + "\ntag text: " + getTagString);
		dispose();
	}
	
	public void WriteData(String tag, String description){
		File file = new File("./Data/data.cvs");
		String date = "14/06/2023";
//		System.out.println(file.isFile() + "\n" + file.isDirectory() + "\n" + file.isAbsolute());
		String line = "Date: " + date + "; Tag: " + tag + "; Description: " + description;
		
		try {
//			TODO: filewriter borra todo el contenido del file
//			FileOutputStream fileOut = new FileOutputStream(file);
//			BufferedOutputStream bufferedOutput = new BufferedOutputStream(fileOut);
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(line);
			fileWriter.write("\n");
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

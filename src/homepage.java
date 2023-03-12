import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JProgressBar;

public class homepage {

	private JFrame frame;
	private JTextField texturl;
	private JTextField textpin;
	private File file;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					homepage window = new homepage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public homepage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(153, 204, 255));
		frame.setResizable(false);
		frame.setBounds(100, 100, 994, 508);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("File/Document - Encryption/Decryption");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel.setBounds(10, 10, 960, 52);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(204, 204, 255));
		panel.setBounds(90, 72, 808, 334);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Choose File");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(null);
				file = fc.getSelectedFile();
				String url = file.getAbsolutePath();
				texturl.setText(url);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton.setBounds(69, 93, 201, 38);
		panel.add(btnNewButton);
		
		texturl = new JTextField();
		texturl.setEditable(false);
		texturl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		texturl.setBounds(296, 93, 465, 38);
		panel.add(texturl);
		texturl.setColumns(10);
		
		JLabel lblEnterPin = new JLabel("Enter pin");
		lblEnterPin.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblEnterPin.setBounds(84, 191, 123, 52);
		panel.add(lblEnterPin);
		
		textpin = new JTextField();
		textpin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textpin.setColumns(10);
		textpin.setBounds(217, 200, 201, 38);
		panel.add(textpin);
		
		JButton btnEncryptdecrypt = new JButton("Encrypt/Decrypt");
		btnEncryptdecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url = texturl.getText();
				String pin = textpin.getText();
				if(url.isEmpty() || pin.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Please enter all details!");
				}
				else
				{
					try {
						int i=0;
						long p = Long.parseLong(pin);
						FileInputStream fis = new FileInputStream(file);
						byte []data = new byte[fis.available()];
						fis.read(data);
						for(byte b:data)
						{
							data[i]=(byte)(b^p);
							i+=1;
						}
						FileOutputStream fos = new FileOutputStream(file);
						fos.write(data);
						JOptionPane.showMessageDialog(null, "Operation is completed successfully!\nwith pin - "+p);
						texturl.setText(null);
						textpin.setText(null);
						fis.close();
						fos.close();
					}
					catch(Exception e1)
					{
						e1.printStackTrace();
					}
				}
			}
		});
		btnEncryptdecrypt.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnEncryptdecrypt.setBounds(370, 416, 257, 45);
		frame.getContentPane().add(btnEncryptdecrypt);
	}
}

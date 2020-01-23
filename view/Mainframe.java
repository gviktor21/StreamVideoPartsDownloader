package streamdownload.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class Mainframe extends JFrame {
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	JButton button1;
	JButton b_download;
	JTextField tfield1;
	JTextField tfield2;
	JTextField tfield3;
	JLabel jlabel1;
	JLabel jlabel2;
	JLabel jlabel3;
	JLabel jlabel4;
	File generatedLinks;
	DownloadManagerPanel downloadPanel;
	public static String ACTION_GENERATE_LINK="GENERATE_LINK";
	public static String ACTION_DOWNLOAD_FILES="DOWNLOAD_FILES";

	public Mainframe(){
		super("streamdownload");
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		downloadPanel= new DownloadManagerPanel();
		
		GridLayout mainLayout = new GridLayout(2,1);
		FlowLayout panellayout = new FlowLayout(FlowLayout.CENTER,10,5);
		GridLayout donwpanellayout = new GridLayout(2,1);
		this.setSize(800, 500);
		this.setLayout(mainLayout);
		initElements();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		tfield1.setSize(100, 20);
		panel1.setLayout(panellayout);panel2.setLayout(panellayout);panel3.setLayout(panellayout);downloadPanel.setLayout(donwpanellayout);
		panel1.add(jlabel1); panel1.add(tfield2);panel1.add(jlabel2);panel1.add(tfield3);
		panel2.add(jlabel3);panel2.add(tfield1);
		panel3.add(button1); panel3.add(b_download);
		this.add(panel1);this.add(panel2);this.add(panel3);this.add(downloadPanel);
		downloadPanel.setVisible(false);
		this.setVisible(true);
	}
	public void initElements() {
		button1= new JButton("Generate");
		b_download = new JButton("Download");
		tfield1= new JTextField(30);
		tfield2 = new JTextField(10);
		tfield3 = new JTextField(10);
		jlabel1= new JLabel("from");
		jlabel2= new JLabel("to");
		jlabel3= new JLabel("link");
		jlabel1.setSize(50, 20);
		jlabel2.setSize(50, 20);

	}
	public void addActionListener(ActionListener al) {
		button1.addActionListener(al);
		button1.setActionCommand(ACTION_GENERATE_LINK);
		b_download.addActionListener(al);
		b_download.setActionCommand(ACTION_DOWNLOAD_FILES);
	}
	public void setDownloadManagerViewVisible(Boolean visibility) {
		downloadPanel.setVisible(visibility);
	}
	public void setTo(int to) {
		tfield3.setText(Integer.toString(to));
	}
	public void setfrom(int from) {
		tfield2.setText(Integer.toString(from));
	}
	public String getTo() {
		return tfield3.getText();
	}
	public String geFrom() {
		return tfield2.getText();
	}
	public String getLinkText() {
		return tfield1.getText();
	}
	public void setLinkText(String link) {
		tfield1.setText(link);
	}
	 public DownloadManagerView getDownloadManagerView() {
		 return this.downloadPanel;
	 }

}

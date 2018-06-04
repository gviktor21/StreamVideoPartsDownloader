package streamdownload.view;

import java.awt.GridLayout;
import java.awt.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class DownloadManagerPanel extends JPanel implements DownloadManagerView{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel upperPanel,lowerPanel;
	JProgressBar downloadstatusbar;
	private static int maxStatus = 100;
	private static int minStatus = 0;
	List loglist;
	JLabel l_currentFilename;
	JLabel l_currentspeed;
	public DownloadManagerPanel(){
		upperPanel = new JPanel();
		lowerPanel = new JPanel();
		GridLayout upperLayout= new GridLayout(1,2);
		GridLayout lowerLayout= new GridLayout(1,1);
		downloadstatusbar = new JProgressBar();
		downloadstatusbar.setMaximum(maxStatus);
		downloadstatusbar.setMinimum(minStatus);
		l_currentFilename =  new JLabel("filename");
		loglist  = new List();
		upperPanel.add(l_currentFilename);
		upperPanel.add(downloadstatusbar);
		lowerPanel.add(loglist);
		this.add(upperPanel);
		this.add(lowerPanel);
	}
	@Override
	public void updateDownloadData(int data) {
		// TODO Auto-generated method stub
		this.downloadstatusbar.setValue(data);
	}
	@Override
	public void addDownloadLogNote(String note) {
		// TODO Auto-generated method stub
		this.loglist.add(note);
	}
	@Override
	public void addDownload(String name, int process) {
		// TODO Auto-generated method stub
		downloadstatusbar.setValue(minStatus);
		l_currentFilename.setText(name);
		
	}
	@Override
	public void removeDownload() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateSpeed(long speed) {
		// TODO Auto-generated method stub
		this.l_currentspeed.setText(speed + "kb/s");
	}
	@Override
	public void updateFile(String name) {
		// TODO Auto-generated method stub
		l_currentFilename.setText(name);
	}
	@Override
	public void repaintDownSceen() {
		// TODO Auto-generated method stub
		this.validate();
	}
	

}

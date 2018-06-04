package streamdownload;

import streamdownload.control.DownloadManager;
import streamdownload.control.StreamDownloadControl;
import streamdownload.view.Mainframe;

public class main {
	//todo: STATE-eket adni a foablakhoz
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Mainframe view = new Mainframe();
		DownloadManager downloadManager = new DownloadManager(view.getDownloadManagerView());
		StreamDownloadControl lctrl = new StreamDownloadControl(view,downloadManager); 
	}

}

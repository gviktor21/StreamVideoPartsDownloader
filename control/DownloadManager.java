package streamdownload.control;

import streamdownload.model.DownloadListener;
import streamdownload.model.Downloader;
import streamdownload.view.DownloadManagerView;

public class DownloadManager implements DownloadListener {
	private long speedlimit;
	private DownloadManagerView view;
	private Downloader downloader;
	private static String LOGNOTE_SESSION_START="!!!!Download Session Starts!!!!";
	private static String LOGNOTE_NEW_FILE="file donwnload start";
	private static String LOGNOTE_DOWNLOAD_FINISHED="!!!!Download Session fINISHED!!!!";
	private int previouspercent;
	
	public DownloadManager(DownloadManagerView downloadManagerView) {
		this.view = downloadManagerView;
	}

	@Override
	public void update() {
		if(downloader.getState() == Downloader.STATE_START_DOWNLOADING) {
			view.addDownloadLogNote(LOGNOTE_SESSION_START);
			view.repaintDownSceen();
		}else if(downloader.getState() == Downloader.STATE_NEW_FILE_TO_DOWNLOAD) {
			view.addDownloadLogNote(downloader.getCurrentFileName()+ " "+ LOGNOTE_NEW_FILE);
			view.addDownload(downloader.getCurrentFileName(), 0,downloader.getCurrentFileSize());
			view.repaintDownSceen();
		}else if(downloader.getState() == Downloader.STATE_DOWNLOAD_IN_PROGRESS) {
			int process = (int) ((downloader.getCurrentDownloadedData() / downloader.getCurrentFileSize())* 100) ;
			view.updateDownloadData(process);
			//System.out.println("filename:" + downloader.getCurrentFileName()+ " "+ process);
			view.repaintDownSceen();
		}else if(downloader.getState() == Downloader.STATE_DOWNLOAD_FINISHED) {
			view.addDownloadLogNote(LOGNOTE_DOWNLOAD_FINISHED);
		}
	}
	public void setDownloader(Downloader downloader) {
		this.downloader = downloader;
	}
	
	@Override
	public void startDownload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pauseDownload() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopDownload() {
		// TODO Auto-generated method stub
		
	}

	
}

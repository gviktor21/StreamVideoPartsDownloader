package streamdownload.model;

public interface DownloadListener {
	public void update();
	public void startDownload();
	public void pauseDownload();
	public void stopDownload();
}

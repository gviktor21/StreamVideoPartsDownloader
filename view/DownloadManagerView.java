package streamdownload.view;

public interface DownloadManagerView {
	public void updateDownloadData(int data);
	public void addDownloadLogNote(String note);
	public void removeDownload();
	public void updateSpeed(long  speed);
	public void updateFile(String name);
	public void repaintDownSceen();
	public void addDownload(String name, int process, double d);
}

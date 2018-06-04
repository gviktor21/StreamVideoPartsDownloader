package streamdownload.model;

public interface DownloadManageable {
	public void addDownloadManager(DownloadListener dlist);
	public void removeDownloadManager(DownloadListener dlist);
	public void notifyDownloadManagers();
}

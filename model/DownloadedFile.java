package streamdownload.model;

import java.io.File;

public class DownloadedFile  extends File{
	private String FilePath;
	private String extension;
	private String filename;
	private int filenumber;
	public DownloadedFile(String pathname) {
		super(pathname);
	}
	
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		
	}
	public boolean isHaveExtension() {
		return false;
		
	}

}

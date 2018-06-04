package streamdownload.model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class Downloader implements DownloadManageable, Runnable {
	private File f_linkList;
	private String destanitionDirectory;
	private double currentFileSize;
	private long currentDownloadedData;
	private String currentFileName;
	private int state;
	private ArrayList<DownloadListener> observers;
	public static int STATE_INITIAL=0;
	public static int STATE_START_DOWNLOADING=1;
	public static int STATE_DOWNLOAD_IN_PROGRESS=3;
	public static int STATE_NEW_FILE_TO_DOWNLOAD=2;
	public static int STATE_DOWNLOAD_FINISHED=4;
	public static String ERROR_WRONG_URL="URL formed wrong";
	public static String ERROR_NO_LIST_GENERATED="There is no link to download";
	public static String ERROR_DESTINATION_UNREACHABLE="Newtwork Socket Exception. Destination unreachable.";
	
	//todo get filename from disposition
	//todo observerek beállítása State Changenel biztosan kell
	//todo saját számozott fájlnév
	// todo újrapróbálható letöltés
	public Downloader(File generatedLinks) {
		f_linkList=generatedLinks;
		observers=new ArrayList<DownloadListener>();
		this.currentDownloadedData=0;
		this.currentDownloadedData=0;
		this.currentFileName="";
		this.state = Downloader.STATE_INITIAL;
	}
	public void loadList() throws MalformedURLException, FileNotFoundException {
		this.state=Downloader.STATE_START_DOWNLOADING;
		this.notifyDownloadManagers();
		FileReader fr = new FileReader(f_linkList);
		BufferedReader br = new BufferedReader(fr);
		String line;
		try {
			while((line= br.readLine()) != null) {
				URL source = new URL(line);
				downloadFile(source);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.state=Downloader.STATE_DOWNLOAD_FINISHED;
		this.notifyDownloadManagers();
	}
	private String getFileNameFromDisposition() {
		return "";
	}
	private String getFileNameFromUrl(URL source) {
		String URL = source.toString();
		String filename =URL.substring(URL.lastIndexOf("/")+1, URL.length());
		return filename;
	} 
	private void downloadFile(URL source) {
		HttpURLConnection httpConn;
		BufferedInputStream in;
		BufferedOutputStream out;
		this.currentFileSize=0;
		this.currentDownloadedData=0;
		try {
			httpConn= (HttpURLConnection) source.openConnection();
			int responseCode = httpConn.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK ) {
				currentFileSize = httpConn.getContentLengthLong();
				this.currentFileName =getFileNameFromUrl(source);
				File downloadedFile = new File(currentFileName);
				this.state=Downloader.STATE_NEW_FILE_TO_DOWNLOAD;
				notifyDownloadManagers();
				String disposition = httpConn.getHeaderField("Content-Disposition");
				in= new BufferedInputStream(httpConn.getInputStream());
				FileOutputStream fos = new FileOutputStream(downloadedFile);
				out = new 	BufferedOutputStream(fos);
				byte[] buffer = new byte[1024];
				int read = 0;
				while((read = in.read(buffer, 0, 1024)) >=0) {
					this.state= Downloader.STATE_DOWNLOAD_IN_PROGRESS;
					notifyDownloadManagers();
					out.write(buffer, 0, read);
					currentDownloadedData+=read;
				}
				if(currentDownloadedData ==currentFileSize) {
					System.out.println("letoltodott teljesen");
				}else {
					System.out.println(this.currentFileName+" nem letoltodott teljesen");
				}
				out.close();
				in.close();
			}
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
		}
	}
	@Override
	public void addDownloadManager(DownloadListener dlisten) {
		// TODO Auto-generated method stub
		observers.add(dlisten);
		
	}
	
	public File getF_linkList() {
		return f_linkList;
	}
	public void setF_linkList(File f_linkList) {
		this.f_linkList = f_linkList;
	}
	public String getDestanitionDirectory() {
		return destanitionDirectory;
	}
	public void setDestanitionDirectory(String destanitionDirectory) {
		this.destanitionDirectory = destanitionDirectory;
	}
	public double getCurrentFileSize() {
		return currentFileSize;
	}
	public void setCurrentFileSize(double currentFileSize) {
		this.currentFileSize = currentFileSize;
	}
	public long getCurrentDownloadedData() {
		return currentDownloadedData;
	}
	public void setCurrentDownloadedData(long currentDownloadedData) {
		this.currentDownloadedData = currentDownloadedData;
	}
	public String getCurrentFileName() {
		return currentFileName;
	}
	public void setCurrentFileName(String currentFileName) {
		this.currentFileName = currentFileName;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public void removeDownloadManager(DownloadListener dlisten) {
		// TODO Auto-generated method stub
		observers.remove(dlisten);
	}
	@Override
	public void notifyDownloadManagers() {
		// TODO Auto-generated method stub
		Iterator it = observers.iterator();
		while(it.hasNext()) {
			DownloadListener dlisten = (DownloadListener) it.next();
			dlisten.update();
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.state=Downloader.STATE_START_DOWNLOADING;
			this.notifyDownloadManagers();
			FileReader fr = new FileReader(f_linkList);
			BufferedReader br = new BufferedReader(fr);
			String line;
			try {
				while((line= br.readLine()) != null) {
					URL source = new URL(line);
					downloadFile(source);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}catch(FileNotFoundException fe) {
			fe.printStackTrace();
		}	
		this.state=Downloader.STATE_DOWNLOAD_FINISHED;
		this.notifyDownloadManagers();
	}
}

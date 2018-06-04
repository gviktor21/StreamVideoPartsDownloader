package streamdownload.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;

import streamdownload.model.Downloader;
import streamdownload.view.Mainframe;

public class StreamDownloadControl implements ActionListener {
	Mainframe linkGView;
	File generatedLinks;
	DownloadManager downloadManager;
	int from,to;
	//todo State-eket rakni: linkGenerate kesz-e? download kesz-e vagy hibaval szallt el? ott folytatni ahol abbamaradt
	// fejlesztesei lehetoseg: tobbszalusag:tobb filenev es progressbar egyszerre tobb mentett linkhalmaz.
	//fejlesztesi lehetoseg: pause,stop, continue gombok
	public StreamDownloadControl(Mainframe view, DownloadManager downloadManager) {
		this.linkGView =view;
		this.downloadManager =downloadManager;
		from=0;
		to=2;
		generatedLinks = new File("output.txt");
		view.setTo(to);
		view.setfrom(from);
		view.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals(Mainframe.ACTION_GENERATE_LINK)){
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(generatedLinks);
				BufferedWriter bwr = new BufferedWriter(new OutputStreamWriter(fos,"UTF-8"));
				String url =linkGView.getLinkText();
				from=Integer.parseInt(linkGView.geFrom());
				to = Integer.parseInt(linkGView.getTo());
				//Todo: majd tesztelni az érvénytelen from to értékeket
				for(int i = from; i < to; i++){
					//System.out.println("url: "+" "+url);
					String newURL =url.replace("**", Integer.toString(i));
					//System.out.println("url(módosítás után): "+" "+url);
					//System.out.println(newURL);
					bwr.write(newURL);
					bwr.newLine();
				}
				bwr.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getActionCommand().equals(Mainframe.ACTION_DOWNLOAD_FILES)) {
			this.linkGView.setDownloadManagerViewVisible(true);

            Thread hilo = new Thread(new Runnable() {

                @Override
                public void run() {

        			Downloader down = new Downloader(generatedLinks);
        			down.addDownloadManager(downloadManager);
        			downloadManager.setDownloader(down);
        			down.run();

                }
            });         
            hilo.start();
			/*
			try {
				down.loadList();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				System.out.println(Downloader.ERROR_WRONG_URL);
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				System.out.println(Downloader.ERROR_NO_LIST_GENERATED);
				e1.printStackTrace();
			}
			*/
		}
		
	}
}

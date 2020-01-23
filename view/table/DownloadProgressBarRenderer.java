package streamdownload.view.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class DownloadProgressBarRenderer extends JProgressBar implements TableCellRenderer {
	
	public DownloadProgressBarRenderer() {
		setOpaque(true); //MUST do this for background to show up.
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object object, boolean selected, boolean focused, int row,
			int column) {
		if(selected) {
			//this.setBackground(Color.whit);
		}else {
			//this.setBackground(Color.GRAY);
		}
		String str =  object.toString();
		Integer val = Integer.parseInt(str);
		this.setValue(val);
		return this;
	}
	

}

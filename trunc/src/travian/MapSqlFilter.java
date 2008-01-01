package travian;

import java.io.File;

public class MapSqlFilter extends javax.swing.filechooser.FileFilter{
	public boolean accept(File file) {
		String filename = file.getName();
		return filename.endsWith(".sql") || file.isDirectory();
	}
	public String getDescription() {
		return "*.sql";
	}
}

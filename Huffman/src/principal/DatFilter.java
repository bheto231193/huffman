package principal;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class DatFilter extends FileFilter{
    final static String dat = "dat";
	public boolean accept(File f) {
        if (f.isDirectory()) { 
            return true; 
        } 
        String s = f.getName(); 
        int i = s.lastIndexOf('.'); 
        if (i > 0 &&  i < s.length() - 1) { 
            String extension = s.substring(i+1).toLowerCase(); 
            if (dat.equals(extension)) { 
                    return true; 
            } else { 
                return false; 
            } 
        } 
		return false;
	}

	@Override
	public String getDescription() {
		return "Archivos .dat";
	}


}

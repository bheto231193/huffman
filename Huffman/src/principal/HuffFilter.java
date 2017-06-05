package principal;

import java.io.File;

public class HuffFilter extends javax.swing.filechooser.FileFilter{
    final static String huf = "huffman";
    public HuffFilter() {
    }
    
    public String getDescription() {
        return "Archivos .huffman";
    }
    
	public boolean accept(File f) {
        if (f.isDirectory()) { 
            return true; 
        } 
        String s = f.getName(); 
        int i = s.lastIndexOf('.'); 
        if (i > 0 &&  i < s.length() - 1) { 
            String extension = s.substring(i+1).toLowerCase(); 
            if (huf.equals(extension)) { 
                    return true; 
            } else { 
                return false; 
            } 
        } 
        return false;
	}
    
}
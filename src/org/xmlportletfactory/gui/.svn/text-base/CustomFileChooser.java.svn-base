/**
 *     Copyright (C) 2009-2011  Jack A. Rider All rights reserved.
 * 
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 */

package org.xmlportletfactory.gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * @author Moises Belda
 *
 */
public class CustomFileChooser extends JFileChooser {
	
	private String extension;
	
	public CustomFileChooser(){
		this("",false,null);
	}
	public CustomFileChooser(String extension, boolean onlyDirs,File fichero) {
	    super(fichero);
	    setModeOnlyDirs(onlyDirs);
	    if(extension==null) extension = "";
	    this.extension = extension;	    
	    if(!extension.equals("")){
	    	addChoosableFileFilter(new FileNameExtensionFilter(extension.toUpperCase()+" files",extension.toUpperCase(),extension));	    
	    }
	}
	
	private void setModeOnlyDirs(boolean dirs){
		if(dirs){
			setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);  
		} else {
			setFileSelectionMode(JFileChooser.FILES_ONLY);
		}
	}
}


class FiltroXML extends FileFilter {
    // Ending of file.
    final static String xml = "xml";

    // Accept all directories and all xml files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            String extension = s.substring(i+1).toLowerCase();
            if (xml.equals(extension)) return true;
        }
        return false;
    }

    // The description of this filter
    public String getDescription() {
        return "XML File";
    }

	
}
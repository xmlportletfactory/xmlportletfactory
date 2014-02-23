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

package org.xmlportletfactory.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jack A. Rider
 */
public class CopyUtil {
//    private static Pattern _dontCopyPattern = Pattern.compile("^(CVS|SCCS|vssver.?\.scc|#.*#|%.*%|_svn)$|~$|^\.(?!htaccess$).*$");
//    private static Pattern _dontCopyPattern = Pattern.compile(".*\r?\n");
    private static Pattern _dontCopyPattern = Pattern.compile("(CVS|SCCS|vssver|svn|bak|.*~+)");

    public static void copyDirectoryStructure(File sourceLocation, File targetLocation) {
        Matcher matcher = _dontCopyPattern.matcher(sourceLocation.getName());
        if (!matcher.find()){
            copyIt(sourceLocation, targetLocation);
        }
    }

    public static void copyDirectoryStructure(File sourceLocation, File targetLocation, Pattern dontCopyPattern) {
        _dontCopyPattern = dontCopyPattern;
        copyDirectoryStructure(sourceLocation, targetLocation);
    }

    public static void copyDirectoryStructure(File sourceLocation, File targetLocation, String dontCopyPatternString) {
        _dontCopyPattern = Pattern.compile(dontCopyPatternString);
        copyDirectoryStructure(sourceLocation, targetLocation);
    }

    public static void copyDirectoryStructure(File sourceLocation, File targetLocation, String[] dontCopyPatternStrings) {
        String dontCopyPattern = "(";
        for (String element:dontCopyPatternStrings) {
            dontCopyPattern = dontCopyPattern + element.trim() + "|";
        }
        dontCopyPattern = dontCopyPattern + ")";
        _dontCopyPattern = Pattern.compile(dontCopyPattern);
        copyDirectoryStructure(sourceLocation, targetLocation);
    }

    private static void copyIt(File sourceLocation, File targetLocation) {
        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }

            String[] children = sourceLocation.list();
            for (int i = 0; i < children.length; i++) {
                CopyUtil.copyDirectoryStructure(new File(sourceLocation, children[i]),
                        new File(targetLocation, children[i]));
            }
        } else {
            InputStream in = null;
            try {
                in = new FileInputStream(sourceLocation);
                OutputStream out = new FileOutputStream(targetLocation);
                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CopyUtil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CopyUtil.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(CopyUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}

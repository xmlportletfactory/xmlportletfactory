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
import java.io.FilenameFilter;

/**
 *
 * @author Jack A. Rider
 */

public class VelocityFileNameFilter implements FilenameFilter {

  public boolean accept(File dir, String s) {
    if (s.contains("macro")) return false;
    if (s.endsWith(".java")) return true;
    if (s.endsWith(".vm")) return true;
    return false;
  }
}

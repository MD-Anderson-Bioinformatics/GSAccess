/*
TcgaGSData Copyright 2014, 2015, 2016 University of Texas MD Anderson Cancer Center

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mda.bcb.gsaccess;

import java.io.File;
import java.util.TreeSet;

/**
 *
 * @author tdcasasent
 */
public class GSStringUtils
{

	////////////////////////////////////////////////////
	////////////////////////////////////////////////////

	static public String beforeTab(String theString)
	{
		int index = theString.indexOf("\t");
		String subString = theString.substring(0, index);
		return subString;
	}

	static public String afterWithTab(String theString)
	{
		int index = theString.indexOf("\t");
		String subString = theString.substring(index);
		return subString;
	}

	static public String afterTab(String theString)
	{
		int index = theString.indexOf("\t");
		String subString = theString.substring(index+1);
		return subString;
	}

	////////////////////////////////////////////////////
	////////////////////////////////////////////////////

	static public String beforePipe(String theString)
	{
		int index = theString.indexOf("|");
		String subString = theString.substring(0, index);
		return subString;
	}

	static public String afterPipe(String theString)
	{
		int index = theString.indexOf("|");
		String subString = theString.substring(index+1);
		return subString;
	}

	////////////////////////////////////////////////////
	////////////////////////////////////////////////////
	
	static public File getFileWith(String theString, TreeSet<File> theFiles)
	{
		File myFile = null;
		for(File file : theFiles)
		{
			if (file.getAbsolutePath().contains(theString))
			{
				myFile = file;
			}
		}
		return myFile;
	}

}

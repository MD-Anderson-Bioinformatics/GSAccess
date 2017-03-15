/*
GSAccess Copyright 2014, 2015, 2016 University of Texas MD Anderson Cancer Center

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mda.bcb.gsaccess.retrieve;

import java.io.IOException;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;
import org.mda.bcb.gsaccess.GSAccess;
import org.mda.bcb.gsaccess.ReadZipFile;

/**
 *
 * @author tdcasasent
 */
// use default to make package protected
public class GetNamesGeneEq extends ReadZipFile
{
	public String mPath = null;
	public int mSize = 0;
	public String [] mGenes = null;
	// static data for GetNamesGeneEq
	static public TreeMap<String, String> M_PATH = null;
	static public TreeMap<String, TreeSet<String>> M_GENES = null;

	public GetNamesGeneEq(String theZipFile)
	{
		super(theZipFile);
		mPath = theZipFile;
		if (null==M_PATH)
		{
			M_PATH = new TreeMap<>();
			M_GENES = new TreeMap<>();
		}
	}

	public boolean getNamesGenes(String theInternalFile) throws IOException
	{
		String path = M_PATH.get(theInternalFile);
		TreeSet<String> genes = M_GENES.get(theInternalFile);
		boolean found = false;
		if ((null==path)||(false==mPath.equals(path))||(null==genes))
		{
			found = getNamesGenesFromFiles(theInternalFile);
			M_PATH.put(theInternalFile, mPath);
			M_GENES.put(theInternalFile, new TreeSet<>(Arrays.asList(mGenes)));
		}
		else
		{
			mGenes = genes.toArray(new String[0]);
			mSize = mGenes.length;
			found = true;
		}
		return found;
	}

	protected boolean getNamesGenesFromFiles(String theInternalFile) throws IOException
	{
		GSAccess.printVersion();
		mSize = 0;
		mGenes = null;
		boolean found = false;
		long start = System.currentTimeMillis();
		found = processFile(theInternalFile);
		long finish = System.currentTimeMillis();
		if(true==found)
		{
			GSAccess.printWithFlag("Gene list (" + mGenes.length + ") retrieved for " + theInternalFile + " in " + ((finish-start)/1000.0) + " seconds");
		}
		else
		{
			GSAccess.printWithFlag("Gene list not found for " + theInternalFile + " in " + ((finish-start)/1000.0) + " seconds");
		}
		return found;
	}

	@Override
	protected boolean processLine(String theLine)
	{
		mGenes = theLine.split("\t", -1);
		mSize = mGenes.length;
		return true;
	}
}

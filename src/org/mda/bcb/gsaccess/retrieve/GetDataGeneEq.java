/*
GSAccess Copyright 2014, 2015, 2016 University of Texas MD Anderson Cancer Center

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mda.bcb.gsaccess.retrieve;

import java.io.IOException;
import org.apache.commons.codec.digest.DigestUtils;
import org.mda.bcb.gsaccess.GSAccess;
import org.mda.bcb.gsaccess.GSStringUtils;
import org.mda.bcb.gsaccess.ReadZipFile;

/**
 *
 * @author tdcasasent
 */
public class GetDataGeneEq extends ReadZipFile
{
	public String mPath = null;
	public int mValueSize = 0;
	public int mSampleSize = 0;
	public double [] mValues = null;
	public String [] mSamples = null;
	//
	protected String mRequestedGene = null;

	public GetDataGeneEq(String theZipFile)
	{
		super(theZipFile);
		GSAccess.printVersion();
		mPath = theZipFile;
	}

	public boolean getData(String theRequestedGene, String theInternalPath) throws IOException
	{
		mValueSize = 0;
		mSampleSize = 0;
		mValues = null;
		mSamples = null;
		mRequestedGene = theRequestedGene;
		long start = System.currentTimeMillis();
		String input = theInternalPath + "/matrix_data_" + DigestUtils.md5Hex(theRequestedGene).substring(0, 2) + ".tsv";
		boolean found = processFile(input);
		long finish = System.currentTimeMillis();
		if(true==found)
		{
			GSAccess.printWithFlag("Gene " + theRequestedGene + " retrieved for " + theInternalPath + " in " + ((finish-start)/1000.0) + " seconds");
		}
		else
		{
			GSAccess.printWithFlag("Gene " + theRequestedGene + " not found for " + theInternalPath + " in " + ((finish-start)/1000.0) + " seconds");
		}
		return found;
	}

	double [] convertToDouble(String [] theStrings)
	{
		double [] doubleArray = new double[theStrings.length];
		for(int x=0;x<theStrings.length;x++)
		{
			String myStr = theStrings[x];
			if ("NA".equalsIgnoreCase(myStr))
			{
				doubleArray[x] = Double.NaN;
			}
			else if ("NaN".equalsIgnoreCase(myStr))
			{
				doubleArray[x] = Double.NaN;
			}
			else 
			{
				doubleArray[x] = Double.parseDouble(myStr);
			}
		}
		return doubleArray;
	}

	@Override
	protected boolean processLine(String theLine)
	{
		boolean keepLooking = true;
		// first line samples
		if (null==mSamples)
		{
			mSamples = GSStringUtils.afterTab(theLine).split("\t", -1);
			mSampleSize = mSamples.length;
		}
		else
		{
			String gene = GSStringUtils.beforeTab(theLine);
			if (mRequestedGene.equals(gene))
			{
				keepLooking = false;
				mValues = convertToDouble(GSStringUtils.afterTab(theLine).split("\t", -1));
				mValueSize = mValues.length;
			}
		}
		return keepLooking;
	}
}

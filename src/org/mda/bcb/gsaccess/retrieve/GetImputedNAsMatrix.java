/*
GSAccess Copyright 2014, 2015, 2016 University of Texas MD Anderson Cancer Center

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mda.bcb.gsaccess.retrieve;

import java.io.IOException;
import java.util.Arrays;
import java.util.TreeSet;
import org.mda.bcb.gsaccess.GSAccess;

/**
 *
 * @author tdcasasent
 */
public class GetImputedNAsMatrix
{
	public String mPath = null;
	public int mSampleSize = 0;
	public int mGenesSize = 0;
	public boolean [][] mGenesBySamplesValues = null;
	public String [] mSamples = null;
	public String [] mGenes = null;

	public GetImputedNAsMatrix(String thePath)
	{
		GSAccess.printVersion();
		mPath = thePath;
	}

	public boolean getImputedNAsMatrix(String [] theGenes, String theInternalPath, String theNamesInternalPath) throws IOException
	{
		mSampleSize = 0;
		mGenesSize = 0;
		mGenesBySamplesValues = null;
		mSamples = null;
		mGenes = null;
		boolean found = false;
		long start = System.currentTimeMillis();
		// get list of genes
		{
			GetNamesGeneEq lg = new GetNamesGeneEq(mPath);
			lg.getNamesGenes(theNamesInternalPath);
			TreeSet<String> allGenes = new TreeSet<>();
			allGenes.addAll(Arrays.asList(lg.mGenes));
			TreeSet<String> ts = new TreeSet<>();
			for(String gene : theGenes)
			{
				if (allGenes.contains(gene))
				{
					GSAccess.printWithFlag("Gene " + gene + " found in " + theNamesInternalPath);
					ts.add(gene);
				}
				else
				{
					GSAccess.printWithFlag("Gene " + gene + " not found in " + theNamesInternalPath);
				}
			}
			mGenes = ts.toArray(new String[0]);
			mGenesSize = mGenes.length;
		}
		// get data
		for(int counter=0;counter<mGenes.length;counter++)
		{
			String requestedGene = mGenes[counter];
			GetImputedNAsGeneEq rg = new GetImputedNAsGeneEq(mPath);
			if (true==rg.getData(requestedGene, theInternalPath))
			{
				if (null==mGenesBySamplesValues)
				{
					mSampleSize = rg.mSampleSize;
					mSamples = rg.mSamples;
					mGenesBySamplesValues = new boolean[mGenesSize][mSampleSize];
				}
				System.arraycopy(rg.mValues, 0, mGenesBySamplesValues[counter], 0, mSampleSize);
				found = true;
			}
		}
		long finish = System.currentTimeMillis();
		if(true==found)
		{
			GSAccess.printWithFlag(mGenesSize + " genes retrieved for " + theInternalPath + " in " + ((finish-start)/1000.0) + " seconds");
		}
		else
		{
			GSAccess.printWithFlag("No genes retrieved for " + theInternalPath + " in " + ((finish-start)/1000.0) + " seconds");
		}
		return found;
	}
}

/*
GSAccess Copyright 2014, 2015, 2016 University of Texas MD Anderson Cancer Center

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mda.bcb.gsaccess.neighbors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import org.mda.bcb.gsaccess.GSAccess;
import org.mda.bcb.gsaccess.ReadZipFile;
import org.mda.bcb.gsaccess.retrieve.MetadataGene;

/**
 *
 * @author linux
 */
public class FindGeneScoreNeighbors_Mixin extends ReadZipFile
{
	protected String mMapFile = null;
	protected String mPath = null;
	//
	public int mIndexGeneSymbol = -1;
	public int mIndexChromosome = -1;
	public int mIndexLocationStart = -1;
	public int mIndexLocationEnd = -1;
	public int mIndexStrand = -1;
	//
	protected boolean mFirstLine = true;
	protected ArrayList<MetadataGene> mList = null;
	protected long mMin = -1;
	protected long mMax = -1;
	protected String mChromosome = null;
	protected String mStrand = null;

	
	public FindGeneScoreNeighbors_Mixin(String theMapFile, String theZipFile)
	{
		super(theZipFile);
		mMapFile = theMapFile;
		mPath = theZipFile;
	}
	
	public void populateHeaderLinesGeneric(String theLine)
	{
		// first line headers
		ArrayList<String> headers = new ArrayList<>();
		headers.addAll(Arrays.asList(theLine.split("\t", -1)));
		mIndexGeneSymbol = headers.indexOf("gene_symbol");
		mIndexChromosome = headers.indexOf("chromosome");
		mIndexLocationStart = headers.indexOf("start_loc");
		mIndexLocationEnd = headers.indexOf("stop_loc");
		mIndexStrand = headers.indexOf("strand");
	}

	public MetadataGene makeMetadataGeneric(String[] theSplitted)
	{
		MetadataGene mdG = new MetadataGene(mPath);
		mdG.mGeneSymbol = theSplitted[mIndexGeneSymbol];
		mdG.mGeneId = "none";
		mdG.mVersionIndex = "none";
		mdG.mChromosome = theSplitted[mIndexChromosome];
		mdG.mLocationStart = theSplitted[mIndexLocationStart];
		mdG.mLocationEnd = theSplitted[mIndexLocationEnd];
		mdG.mStrand = theSplitted[mIndexStrand];
		return mdG;
	}
	
	public MetadataGene matchesParameters(String[] theSplitted, long theMin, long theMax, String theChromosome, String theStrand)	
	{
		MetadataGene result = null;
		MetadataGene mdg = makeMetadataGeneric(theSplitted);
		if ((null==theChromosome)||(theChromosome.equals(mdg.mChromosome)))
		{
			if ((null==theStrand)||(theStrand.equals(mdg.mStrand)))
			{
				long start = Long.parseLong(mdg.mLocationStart);
				long end = Long.parseLong(mdg.mLocationEnd);
				if (start>end)
				{
					long tmp = end;
					end = start;
					start = tmp;
				}
				if ((start<=theMax)&&(theMin<=end))
				{
					result = mdg;
				}
			}	
		}
		return result;
	}
	
	public MetadataGene [] findNeighbors(long theMin, long theMax, String theChromosome, String theStrand) throws IOException
	{
		long start = System.currentTimeMillis();
		mFirstLine = true;
		mList = new ArrayList<>();
		mMin = theMin;
		mMax = theMax;
		mChromosome = theChromosome;
		mStrand = theStrand;
		processFile(mMapFile);
		long finish = System.currentTimeMillis();
		GSAccess.printWithFlag("FindGeneScoreNeighbors_Mixin findNeighbors retrieved " + mList.size() + " elements for in " + ((finish - start) / 1000.0) + " seconds");
		ArrayList<MetadataGene> list = mList;
		mList = null;
		return list.toArray(new MetadataGene[0]);
	}

	@Override
	protected boolean processLine(String theLine)
	{
		
		if (true==mFirstLine)
		{
			mFirstLine = false;
			populateHeaderLinesGeneric(theLine);
		}
		else
		{
			String[] splitted = theLine.split("\t", -1);
			MetadataGene mdg = matchesParameters(splitted, mMin, mMax, mChromosome, mStrand);
			if (null!=mdg)
			{
				mList.add(mdg);
			}
		}
		return true;
	}
}

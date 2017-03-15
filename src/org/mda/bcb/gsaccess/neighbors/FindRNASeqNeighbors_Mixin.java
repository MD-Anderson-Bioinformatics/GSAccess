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
public class FindRNASeqNeighbors_Mixin extends ReadZipFile
{
	static protected String M_MAP_FILE = null;
	static protected String M_PATH = null;
	//static protected ArrayList<
	//
	public int mIndexGeneSymbol = -1;
	public int mIndexGeneId = -1;
	public int mIndexVersionIndex = -1;
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

	public FindRNASeqNeighbors_Mixin(String theMapFile, String theZipFile)
	{
		super(theZipFile);
		if ((false==theMapFile.equals(M_MAP_FILE))||(false==theZipFile.equals(M_PATH)))
		{
			M_MAP_FILE = theMapFile;
			M_PATH = theZipFile;
		}
	}
	
	public void populateHeaderLinesGeneric(String theLine)
	{
		// first line headers
		ArrayList<String> headers = new ArrayList<>();
		headers.addAll(Arrays.asList(theLine.split("\t", -1)));
		mIndexGeneSymbol = headers.indexOf("gene_symbol");
		mIndexGeneId = headers.indexOf("gene_id");
		mIndexVersionIndex = headers.indexOf("version_index");
		mIndexChromosome = headers.indexOf("chromosome");
		mIndexLocationStart = headers.indexOf("location_start");
		mIndexLocationEnd = headers.indexOf("location_end");
		mIndexStrand = headers.indexOf("strand");
	}

	public MetadataGene makeMetadataGeneric(String[] theSplitted)
	{
		MetadataGene mdG = new MetadataGene(M_PATH);
		mdG.mGeneSymbol = theSplitted[mIndexGeneSymbol];
		mdG.mGeneId = theSplitted[mIndexGeneId];
		mdG.mVersionIndex = theSplitted[mIndexVersionIndex];
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
				if ((start>=theMin)&&(end<=theMax))
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
		mMin = Long.min(theMin, theMax);
		mMax = Long.max(theMin, theMax);
		mChromosome = theChromosome;
		mStrand = theStrand;
		processFile(M_MAP_FILE);
		long finish = System.currentTimeMillis();
		GSAccess.printWithFlag("FindRNASeqNeighbors_Mixin findNeighbors retrieved " + mList.size() + " elements for in " + ((finish - start) / 1000.0) + " seconds");
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

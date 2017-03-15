/*
GSAccess Copyright 2014, 2015, 2016 University of Texas MD Anderson Cancer Center

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mda.bcb.gsaccess.retrieve;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.mda.bcb.gsaccess.GSAccess;
import org.mda.bcb.gsaccess.ReadZipFile;

/**
 *
 * @author linux
 */
public class MetadataGene extends ReadZipFile
{
	//
	public String mGeneSymbol = null;
	public String mGeneId = null;
	public String mVersionIndex = null;
	public String mChromosome = null;
	public String mLocationStart = null;
	public String mLocationEnd = null;
	public String mStrand = null;
	//
	public int mIndexGeneSymbol = -1;
	public int mIndexGeneId = -1;
	public int mIndexVersionIndex = -1;
	public int mIndexChromosome = -1;
	public int mIndexLocationStart = -1;
	public int mIndexLocationEnd = -1;
	public int mIndexStrand = -1;
	//
	protected static String M_PATH = null;
	protected static HashMap<String, ArrayList<MetadataGene>> M_RNASEQ_ID_TO_METADATA = null;
	protected static HashMap<String, ArrayList<MetadataGene>> M_RNASEQ_SYMBOL_TO_METADATA = null;
	protected static HashMap<String, ArrayList<MetadataGene>> M_HG18_SYMBOL_TO_METADATA = null;
	protected static HashMap<String, ArrayList<MetadataGene>> M_HG19_SYMBOL_TO_METADATA = null;
	//
	protected boolean mFirstLine = true;
	protected boolean mRNASEQflag = false;
	protected boolean mHG18flag = false;
	protected boolean mHG19flag = false;

	public MetadataGene(String theZipFile)
	{
		super(theZipFile);
		if (null!=theZipFile)
		{
			if (false==theZipFile.equals(M_PATH))
			{
				M_PATH = theZipFile;
				M_RNASEQ_ID_TO_METADATA = null;
				M_RNASEQ_SYMBOL_TO_METADATA = null;
				M_HG18_SYMBOL_TO_METADATA = null;
				M_HG19_SYMBOL_TO_METADATA = null;
			}
		}
		mGeneSymbol = null;
		mGeneId = null;
		mVersionIndex = null;
		mChromosome = null;
		mLocationStart = null;
		mLocationEnd = null;
		mStrand = null;
	}

	public MetadataGene [] getMetadataList_Mutations(String theStandardizedDataId, String theInternalPath) throws IOException
	{
		try
		{
			GSAccess.printVersion();
			return getMetadataListHg19(theStandardizedDataId, theInternalPath);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
			M_RNASEQ_ID_TO_METADATA = null;
			M_RNASEQ_SYMBOL_TO_METADATA = null;
			M_HG18_SYMBOL_TO_METADATA = null;
			M_HG19_SYMBOL_TO_METADATA = null;
			throw exp;
		}
	}

	public MetadataGene [] getMetadataList_RNASeq(String theStandardizedDataId, String theInternalPath) throws IOException
	{
		try
		{
			GSAccess.printVersion();
			return getMetadataListRnaSeq(theStandardizedDataId, theInternalPath);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
			M_RNASEQ_ID_TO_METADATA = null;
			M_RNASEQ_SYMBOL_TO_METADATA = null;
			M_HG18_SYMBOL_TO_METADATA = null;
			M_HG19_SYMBOL_TO_METADATA = null;
			throw exp;
		}
	}

	public MetadataGene [] getMetadataList_RNASeqV2(String theStandardizedDataId, String theInternalPath) throws IOException
	{
		try
		{
			GSAccess.printVersion();
			return getMetadataListRnaSeq(theStandardizedDataId, theInternalPath);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
			M_RNASEQ_ID_TO_METADATA = null;
			M_RNASEQ_SYMBOL_TO_METADATA = null;
			M_HG18_SYMBOL_TO_METADATA = null;
			M_HG19_SYMBOL_TO_METADATA = null;
			throw exp;
		}
	}

	public MetadataGene [] getMetadataList_HG18(String theStandardizedDataId, String theInternalPath) throws IOException
	{
		try
		{
			GSAccess.printVersion();
			return getMetadataListHg18(theStandardizedDataId, theInternalPath);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
			M_RNASEQ_ID_TO_METADATA = null;
			M_RNASEQ_SYMBOL_TO_METADATA = null;
			M_HG18_SYMBOL_TO_METADATA = null;
			M_HG19_SYMBOL_TO_METADATA = null;
			throw exp;
		}
	}

	public MetadataGene [] getMetadataList_HG19(String theStandardizedDataId, String theInternalPath) throws IOException
	{
		try
		{
			GSAccess.printVersion();
			return getMetadataListHg19(theStandardizedDataId, theInternalPath);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
			M_RNASEQ_ID_TO_METADATA = null;
			M_RNASEQ_SYMBOL_TO_METADATA = null;
			M_HG18_SYMBOL_TO_METADATA = null;
			M_HG19_SYMBOL_TO_METADATA = null;
			throw exp;
		}
	}
	
	////////////////////////////////////////////////////////////////////////////
	
	protected void addToLists(MetadataGene theMG, 
			HashMap<String, ArrayList<MetadataGene>> theIdToMetadata,  
			HashMap<String, ArrayList<MetadataGene>> theGeneSymbolToMetadata )
	{
		if (null!=theIdToMetadata)
		{
			ArrayList<MetadataGene> al = theIdToMetadata.get(theMG.mGeneId);
			if (null==al)
			{
				al = new ArrayList<>();
			}
			al.add(theMG);
			theIdToMetadata.put(theMG.mGeneId, al);
		}
		//
		if (null!=theGeneSymbolToMetadata)
		{
			ArrayList<MetadataGene> al = theGeneSymbolToMetadata.get(theMG.mGeneSymbol);
			if (null==al)
			{
				al = new ArrayList<>();
			}
			al.add(theMG);
			theGeneSymbolToMetadata.put(theMG.mGeneSymbol, al);
		}
	}
	
	////////////////////////////////////////////////////////////////////////////
	//// RNASEQ functions
	////////////////////////////////////////////////////////////////////////////

	protected void populateHeaderLinesRnaSeq(String theLine)
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

	protected void loadRNASEQdata(String theFile) throws IOException
	{
		if (null==M_RNASEQ_ID_TO_METADATA)
		{
			M_RNASEQ_ID_TO_METADATA = new HashMap<>();
			M_RNASEQ_SYMBOL_TO_METADATA = new HashMap<>();
			mFirstLine = true;
			mRNASEQflag = true;
			mHG18flag = false;
			mHG19flag = false;
			processFile(theFile);
		}
	}

	protected MetadataGene [] getMetadataListRnaSeq(String theStandardizedDataId, String theFile) throws IOException
	{
		ArrayList<MetadataGene> list = new ArrayList<>();
		String [] splittedId = theStandardizedDataId.split("\\|", -1);
		String geneSymbol = splittedId[0];
		String geneId = (splittedId.length==1)?null:splittedId[1];
		boolean found = false;
		long start = System.currentTimeMillis();
		loadRNASEQdata(theFile);
		if (null==geneId)
		{
			list = M_RNASEQ_SYMBOL_TO_METADATA.get(geneSymbol);
			if (null!=list)
			{
				found = true;
			}
		}
		else
		{
			list = M_RNASEQ_ID_TO_METADATA.get(geneId);
			if (null!=list)
			{
				found = true;
			}
		}
		if (null==list)
		{
			 list = new ArrayList<>();
		}
		long finish = System.currentTimeMillis();
		if (true == found)
		{
			GSAccess.printWithFlag("List StandardizedDataId " + theStandardizedDataId + " retrieved " + list.size() + " elements for " + theFile + " in " + ((finish - start) / 1000.0) + " seconds");
		}
		else
		{
			GSAccess.printWithFlag("List StandardizedDataId " + theStandardizedDataId + " not found for " + theFile + " in " + ((finish - start) / 1000.0) + " seconds");
		}
		return list.toArray(new MetadataGene[0]);
	}

	////////////////////////////////////////////////////////////////////////////
	//// HG18 functions
	////////////////////////////////////////////////////////////////////////////

	protected MetadataGene [] getMetadataListHg18(String theGeneSymbol, String theFile) throws IOException
	{
		ArrayList<MetadataGene> list = new ArrayList<>();
		boolean found = false;
		long start = System.currentTimeMillis();
		if (null==M_HG18_SYMBOL_TO_METADATA)
		{
			M_HG18_SYMBOL_TO_METADATA = new HashMap<>();
			loadHGdata(theFile, false);
		}
		list = M_HG18_SYMBOL_TO_METADATA.get(theGeneSymbol);
		if (null!=list)
		{
			found = true;
		}
		else
		{
			for (String key : M_HG18_SYMBOL_TO_METADATA.keySet())
			{
				if (null==list)
				{
					if (key.startsWith(theGeneSymbol))
					{
						list = M_HG18_SYMBOL_TO_METADATA.get(key);
					}
				}
			}
			if (null==list)
			{
				list = new ArrayList<>();
			}
		}
		long finish = System.currentTimeMillis();
		if (true == found)
		{
			GSAccess.printWithFlag("List getMetadataListHg18 " + theGeneSymbol + " retrieved " + list.size() + " elements for " + theFile + " in " + ((finish - start) / 1000.0) + " seconds");
		}
		else
		{
			GSAccess.printWithFlag("List getMetadataListHg18 " + theGeneSymbol + " not found for " + theFile + " in " + ((finish - start) / 1000.0) + " seconds");
		}
		return list.toArray(new MetadataGene[0]);
	}

	////////////////////////////////////////////////////////////////////////////
	//// HG19 functions
	////////////////////////////////////////////////////////////////////////////

	protected MetadataGene [] getMetadataListHg19(String theGeneSymbol, String theFile) throws IOException
	{
		ArrayList<MetadataGene> list = new ArrayList<>();
		boolean found = false;
		long start = System.currentTimeMillis();
		if (null==M_HG19_SYMBOL_TO_METADATA)
		{
			M_HG19_SYMBOL_TO_METADATA = new HashMap<>();
			loadHGdata(theFile, true);
		}
		list = M_HG19_SYMBOL_TO_METADATA.get(theGeneSymbol);
		if (null!=list)
		{
			found = true;
		}
		else
		{
			for (String key : M_HG19_SYMBOL_TO_METADATA.keySet())
			{
				if (null==list)
				{
					if (key.startsWith(theGeneSymbol))
					{
						list = M_HG19_SYMBOL_TO_METADATA.get(key);
					}
				}
			}
			if (null==list)
			{
				list = new ArrayList<>();
			}
		}
		long finish = System.currentTimeMillis();
		if (true == found)
		{
			GSAccess.printWithFlag("List getMetadataListHg19 " + theGeneSymbol + " retrieved " + list.size() + " elements for " + theFile + " in " + ((finish - start) / 1000.0) + " seconds");
		}
		else
		{
			GSAccess.printWithFlag("List getMetadataListHg19 " + theGeneSymbol + " not found for " + theFile + " in " + ((finish - start) / 1000.0) + " seconds");
		}
		return list.toArray(new MetadataGene[0]);
	}

	////////////////////////////////////////////////////////////////////////////
	//// HG shared
	////////////////////////////////////////////////////////////////////////////
	
	protected void reduceKeys(HashMap<String, ArrayList<MetadataGene>> theMap)
	{
		ArrayList<String> origKeys = new ArrayList<>(theMap.keySet());
		ArrayList<String> reduKeys = new ArrayList<>();
		for(String key : origKeys)
		{
			reduKeys.add(key.split("\\|", -1)[0]);
		}
		for(int x=0;x<origKeys.size();x++)
		{
			String longO = origKeys.get(x);
			String longR = reduKeys.get(x);
			if ((reduKeys.indexOf(longR))==(reduKeys.lastIndexOf(longR)))
			{
				ArrayList<MetadataGene> values = theMap.get(longO);
				for (MetadataGene mg : values)
				{
					mg.mGeneSymbol = longR;
				}
				theMap.remove(longO);
				theMap.put(longR, values);
			}
		}
	}
	
	protected void loadHGdata(String theFile, boolean the19flag) throws IOException
	{
		mFirstLine = true;
		mRNASEQflag = false;
		mHG18flag = !the19flag;
		mHG19flag = the19flag;
		processFile(theFile);
		// reduce the keys for HG18 and HG19 files
		if (mHG18flag)
		{
			reduceKeys(M_HG18_SYMBOL_TO_METADATA);
		}
		else if (mHG19flag)
		{
			reduceKeys(M_HG19_SYMBOL_TO_METADATA);
		}
	}

	protected void populateHeaderLinesHg(String theLine)
	{
		// first line headers
		ArrayList<String> headers = new ArrayList<>();
		headers.addAll(Arrays.asList(theLine.split("\t", -1)));
		mIndexGeneSymbol = headers.indexOf("gene_symbol");
		mIndexGeneId = -1;
		mIndexVersionIndex = -1;
		mIndexChromosome = headers.indexOf("chromosome");
		mIndexLocationStart = headers.indexOf("start_loc");
		mIndexLocationEnd = headers.indexOf("stop_loc");
		mIndexStrand = headers.indexOf("strand");
	}

	protected MetadataGene makeMetadataHg(String[] theSplitted)
	{
		MetadataGene mdG = new MetadataGene(M_PATH);
		mdG.mGeneSymbol = theSplitted[mIndexGeneSymbol];
		if (-1!=mIndexGeneId)
		{
			mdG.mGeneId = theSplitted[mIndexGeneId];
		}
		else
		{
			mdG.mGeneId = "none";
		}
		if (-1!=mIndexVersionIndex)
		{
			mdG.mVersionIndex = theSplitted[mIndexVersionIndex];
		}
		else
		{
			mdG.mVersionIndex = "none";
		}
		mdG.mChromosome = theSplitted[mIndexChromosome];
		mdG.mLocationStart = theSplitted[mIndexLocationStart];
		mdG.mLocationEnd = theSplitted[mIndexLocationEnd];
		mdG.mStrand = theSplitted[mIndexStrand];
		return mdG;
	}
	
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	
	
	@Override
	protected boolean processLine(String theLine)
	{
		if (true==mFirstLine)
		{
			if (mHG18flag||mHG19flag)
			{
				populateHeaderLinesHg(theLine);
			}
			else
			{
				populateHeaderLinesRnaSeq(theLine);
			}
			mFirstLine = false;
		}
		else
		{
			String[] splitted = theLine.split("\t", -1);
			MetadataGene mg = makeMetadataHg(splitted);
			if (mHG18flag)
			{
				addToLists(mg, null, M_HG18_SYMBOL_TO_METADATA);
			}
			else if (mHG19flag)
			{
				addToLists(mg, null, M_HG19_SYMBOL_TO_METADATA);
			}
			else
			{
				addToLists(mg, M_RNASEQ_ID_TO_METADATA, M_RNASEQ_SYMBOL_TO_METADATA);
			}
		}
		return true;
	}

}

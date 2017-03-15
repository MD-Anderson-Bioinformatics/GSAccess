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
import java.util.TreeMap;
import org.mda.bcb.gsaccess.GSAccess;
import org.mda.bcb.gsaccess.ReadZipFile;

/**
 *
 * @author tdcasasent
 */
public class MetadataProbe extends ReadZipFile
{
	public int mGeneStructureLength = 0;
	public String mName = null;
	public String mChromosome = null;
	public long mProbeLocation = -1;
	public String mStrand = null;
	public String[] mGene = null;
	public String[] mStructure = null;
	public int mIndexProbe = -1;
	public int mIndexChromosome = -1;
	public int mIndexProbeLocation = -1;
	public int mIndexStrand = -1;
	public int mIndexGene = -1;
	public int mIndexStructure = -1;
	//
	protected static String M_PATH = null;
	protected static TreeMap<String, MetadataProbe> M_METH27_PROBE_TO_METADATA = null;
	protected static HashMap<String, MetadataProbe> M_METH450_PROBE_TO_METADATA = null;

	public MetadataProbe(String theZipFile)
	{
		super(theZipFile);
		if (false==theZipFile.equals(M_PATH))
		{
			M_PATH = theZipFile;
			M_METH27_PROBE_TO_METADATA = null;
			M_METH450_PROBE_TO_METADATA = null;
		}
	}

	public boolean getMetadata_Meth27(String theProbe, String theInternalPath) throws IOException
	{
		try
		{
			GSAccess.printVersion();
			return getMetadataMeth27(theProbe, theInternalPath);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
			M_METH27_PROBE_TO_METADATA = null;
			M_METH450_PROBE_TO_METADATA = null;
			throw exp;
		}
	}

	public boolean getMetadata_Meth450(String theProbe, String theInternalPath) throws IOException
	{
		try
		{
			GSAccess.printVersion();
			return getMetadata450(theProbe, theInternalPath);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
			M_METH27_PROBE_TO_METADATA = null;
			M_METH450_PROBE_TO_METADATA = null;
			throw exp;
		}
	}

	protected void copyIndexes(MetadataProbe theOtherProbe)
	{
		mIndexProbe = theOtherProbe.mIndexProbe;
		mIndexChromosome = theOtherProbe.mIndexChromosome;
		mIndexProbeLocation = theOtherProbe.mIndexProbeLocation;
		mIndexStrand = theOtherProbe.mIndexStrand;
		mIndexGene = theOtherProbe.mIndexGene;
		mIndexStructure = theOtherProbe.mIndexStructure;
	}

	protected void copyValues(MetadataProbe theOtherProbe)
	{
		mName = theOtherProbe.mName;
		mChromosome = theOtherProbe.mChromosome;
		mProbeLocation = theOtherProbe.mProbeLocation;
		mStrand = theOtherProbe.mStrand;
		mGene = theOtherProbe.mGene;
		mStructure = theOtherProbe.mStructure;
	}

	public void populateHeaderLines450(String theLine)
	{
		// first line headers
		ArrayList<String> headers = new ArrayList<>();
		headers.addAll(Arrays.asList(theLine.split("\t", -1)));
		mIndexProbe = headers.indexOf("IlmnID");
		mIndexChromosome = headers.indexOf("CHR");
		mIndexProbeLocation = headers.indexOf("MAPINFO");
		mIndexStrand = headers.indexOf("Strand");
		mIndexGene = headers.indexOf("UCSC_RefGene_Name");
		mIndexStructure = headers.indexOf("UCSC_RefGene_Group");
	}

	public void populateHeaderLines27(String theLine)
	{
		// first line headers
		ArrayList<String> headers = new ArrayList<>();
		headers.addAll(Arrays.asList(theLine.split("\t", -1)));
		mIndexProbe = headers.indexOf("probe_id");
		mIndexChromosome = headers.indexOf("chromosome");
		mIndexProbeLocation = headers.indexOf("probe_location");
		mIndexGene = headers.indexOf("gene_id");
	}

	public MetadataProbe populateGetMetadata450(String[] theSplitted)
	{
		MetadataProbe mdp = new MetadataProbe(M_PATH);
		mdp.mName = theSplitted[mIndexProbe];
		mdp.mChromosome = theSplitted[mIndexChromosome];
		try
		{
			mdp.mProbeLocation = Long.parseLong(theSplitted[mIndexProbeLocation]);
		}
		catch(Exception exp)
		{
			// means location was empty or NA
			mdp.mProbeLocation = -1;
		}
		mdp.mStrand = theSplitted[mIndexStrand];
		String[] genes = theSplitted[mIndexGene].split(";", -1);
		String[] structures = theSplitted[mIndexStructure].split(";", -1);
		if (0 == genes.length)
		{
			mdp.mGene = new String[1];
			mdp.mGene[0] = "Not Reported";
			mdp.mStructure = new String[1];
			mdp.mStructure[0] = "Not Reported";
		}
		else
		{
			TreeMap<String, String> geneToStructure = new TreeMap<>();
			for (int x = 0; x < genes.length; x++)
			{
				String currentStructure = geneToStructure.get(genes[x]);
				if (null == currentStructure)
				{
					geneToStructure.put(genes[x], structures[x]);
				}
				else
				{
					if (currentStructure.equals(structures[x]))
					{
						// do nothing, already entered
					}
					else
					{
						// different structure, combine strings
						geneToStructure.put(genes[x], currentStructure + ";" + structures[x]);
					}
				}
			}
			ArrayList<String> geneArray = new ArrayList<>();
			ArrayList<String> structArray = new ArrayList<>();
			for (String gene : geneToStructure.keySet())
			{
				geneArray.add(gene);
				structArray.add(geneToStructure.get(gene));
			}
			mdp.mGene = geneArray.toArray(new String[0]);
			mdp.mStructure = structArray.toArray(new String[0]);
			mdp.mGeneStructureLength = mdp.mStructure.length;
		}
		return mdp;
	}

	public MetadataProbe populateGetMetadataMeth27(String[] theSplitted)
	{
		MetadataProbe mdp = new MetadataProbe(M_PATH);
		mdp.mName = theSplitted[mIndexProbe];
		mdp.mChromosome = theSplitted[mIndexChromosome];
		try
		{
			mdp.mProbeLocation = Long.parseLong(theSplitted[mIndexProbeLocation]);
		}
		catch(Exception exp)
		{
			// means location was empty or NA
			mdp.mProbeLocation = -1;
		}
		String[] genes = theSplitted[mIndexGene].split(";", -1);
		if (0 == genes.length)
		{
			mdp.mGene = new String[1];
			mdp.mGene[0] = "Not Reported";
			mdp.mStructure = new String[1];
			mdp.mStructure[0] = "Not Reported";
		}
		else
		{
			mdp.mGene = genes;
			mdp.mStructure = new String[mdp.mGene.length];
			for (int x = 0; x < mdp.mGene.length; x++)
			{
				mdp.mStructure[x] = "Not Reported";
			}
		}
		return mdp;
	}

	protected boolean getMetadata450(String theProbe, String theInternalPath) throws IOException
	{
		mName = null;
		mGeneStructureLength = 0;
		mChromosome = null;
		mProbeLocation = -1;
		mStrand = null;
		mGene = null;
		mStructure = null;
		boolean found = false;
		long start = System.currentTimeMillis();
		if (null==M_METH450_PROBE_TO_METADATA)
		{
			M_METH450_PROBE_TO_METADATA = new HashMap<>();
			mM27flag = false;
			mFirstLine = true;
			processFile(theInternalPath);
		}
		MetadataProbe mdp = M_METH450_PROBE_TO_METADATA.get(theProbe);
		if (null!=mdp)
		{
			found = true;
			copyValues(mdp);
		}
		long finish = System.currentTimeMillis();
		if (true == found)
		{
			GSAccess.printWithFlag("Probe " + theProbe + " retrieved for " + theInternalPath + " in " + ((finish - start) / 1000.0) + " seconds");
		}
		else
		{
			GSAccess.printWithFlag("Probe " + theProbe + " not found for " + theInternalPath + " in " + ((finish - start) / 1000.0) + " seconds");
		}
		return found;
	}

	protected boolean mM27flag = false;
	protected boolean mFirstLine = true;
	
	protected boolean getMetadataMeth27(String theProbe, String theInternalPath) throws IOException
	{
		mName = null;
		mGeneStructureLength = 0;
		mChromosome = null;
		mProbeLocation = -1;
		mStrand = null;
		mGene = null;
		mStructure = null;
		boolean found = false;
		long start = System.currentTimeMillis();
		if (null==M_METH27_PROBE_TO_METADATA)
		{
			mM27flag = true;
			mFirstLine = true;
			M_METH27_PROBE_TO_METADATA = new TreeMap<>();
			processFile(theInternalPath);
		}
		MetadataProbe mdp = M_METH27_PROBE_TO_METADATA.get(theProbe);
		if (null!=mdp)
		{
			found = true;
			copyValues(mdp);
		}
		long finish = System.currentTimeMillis();
		if (true == found)
		{
			GSAccess.printWithFlag("Probe " + theProbe + " retrieved for " + theInternalPath + " in " + ((finish - start) / 1000.0) + " seconds");
		}
		else
		{
			GSAccess.printWithFlag("Probe " + theProbe + " not found for " + theInternalPath + " in " + ((finish - start) / 1000.0) + " seconds");
		}
		return found;
	}

	@Override
	protected boolean processLine(String theLine)
	{
		if (true==mFirstLine)
		{
			if (mM27flag)
			{
				populateHeaderLines27(theLine);
			}
			else
			{
				populateHeaderLines450(theLine);
			}
			mFirstLine = false;
		}
		else
		{
			String[] splitted = theLine.split("\t", -1);
			if (mM27flag)
			{
				MetadataProbe mdp = populateGetMetadataMeth27(splitted);
				M_METH27_PROBE_TO_METADATA.put(mdp.mName, mdp);
			}
			else
			{
				MetadataProbe mdp = populateGetMetadata450(splitted);
				M_METH450_PROBE_TO_METADATA.put(mdp.mName, mdp);
			}
		}
		return true;
	}
}

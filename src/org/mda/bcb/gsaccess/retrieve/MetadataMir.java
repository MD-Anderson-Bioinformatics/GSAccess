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
import java.util.TreeMap;
import java.util.TreeSet;
import org.mda.bcb.gsaccess.GSAccess;
import org.mda.bcb.gsaccess.ReadZipFile;

/**
 *
 * @author linux
 */
public class MetadataMir extends ReadZipFile implements Comparable<MetadataMir>
{
	protected static String M_PATH = null;
	//
	public String mMirId = null;
	public String mMimatId = null;
	public String mMirType = null;
	public String mChromosome = null;
	public long mLocationStart = -1;
	public long mLocationEnd = -1;
	public String mStrand = null;
	public String mDerivedFrom = null;
	//
	public int mIndexMirId = -1;
	public int mIndexMimatId = -1;
	public int mIndexMirType = -1;
	public int mIndexChromosome = -1;
	public int mIndexLocationStart = -1;
	public int mIndexLocationEnd = -1;
	public int mIndexStrand = -1;
	public int mIndexDerivedFrom = -1;
	//
	protected static TreeMap<String, TreeSet<MetadataMir>> M_MIRMAP = null;
	protected static TreeMap<String, TreeSet<MetadataMir>> M_MIMATMAP = null;

	public MetadataMir(String theZipFile)
	{
		super(theZipFile);
		if (false==theZipFile.equals(M_PATH))
		{
			M_PATH = theZipFile;
			M_MIRMAP = null;
			M_MIMATMAP = null;
		}
	}

	@Override
	public String toString()
	{
		return "MetadataMir{" + "mMirId=" + mMirId + ", mMimatId=" + mMimatId + ", mMirType=" + mMirType + ", mChromosome=" + mChromosome + ", mLocationStart=" + mLocationStart + ", mLocationEnd=" + mLocationEnd + ", mStrand=" + mStrand + ", mDerivedFrom=" + mDerivedFrom + '}';
	}

	public String [] getMirList(String theInternalPath) throws IOException
	{
		try
		{
			GSAccess.printVersion();
			checkData(theInternalPath);
			return M_MIRMAP.keySet().toArray(new String[0]);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
			M_MIRMAP = null;
			M_MIMATMAP = null;
			throw exp;
		}
	}

	public String [] getMimatList(String theInternalPath) throws IOException
	{
		try
		{
			GSAccess.printVersion();
			checkData(theInternalPath);
			return M_MIMATMAP.keySet().toArray(new String[0]);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
			M_MIRMAP = null;
			M_MIMATMAP = null;
			throw exp;
		}
	}
	
	public MetadataMir [] getMetadata_miRNA_mir(String theMirId, String theInternalPath) throws IOException
	{
		try
		{
			GSAccess.printVersion();
			checkData(theInternalPath);
			return M_MIRMAP.get(theMirId).toArray(new MetadataMir[0]);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
			M_MIRMAP = null;
			M_MIMATMAP = null;
			throw exp;
		}
	}

	public MetadataMir [] getMetadata_miRNA_mimat(String theMimatId, String theInternalPath) throws IOException
	{
		try
		{
			GSAccess.printVersion();
			checkData(theInternalPath);
			return M_MIMATMAP.get(theMimatId).toArray(new MetadataMir[0]);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
			M_MIRMAP = null;
			M_MIMATMAP = null;
			throw exp;
		}
	}
	
	protected void checkData(String theInternalPath) throws IOException
	{
		if (null==M_MIRMAP)
		{
			processFile(theInternalPath);
		}
	}

	protected void populateHeaderIndexes(String theLine)
	{
		// first line headers
		ArrayList<String> headers = new ArrayList<>();
		headers.addAll(Arrays.asList(theLine.split("\t", -1)));
		mIndexMirId = headers.indexOf("mir_id");
		mIndexMimatId = headers.indexOf("mimat_id");
		mIndexMirType = headers.indexOf("mir_type");
		mIndexChromosome = headers.indexOf("chromosome");
		mIndexLocationStart = headers.indexOf("location_start");
		mIndexLocationEnd = headers.indexOf("location_end");
		mIndexStrand = headers.indexOf("strand");
		mIndexDerivedFrom = headers.indexOf("derived_from");
	}
	
	public MetadataMir makeMetadata(String[] theSplitted)
	{
		MetadataMir mdG = new MetadataMir(M_PATH);
		mdG.mMirId = theSplitted[mIndexMirId];
		mdG.mMimatId = theSplitted[mIndexMimatId];
		mdG.mMirType = theSplitted[mIndexMirType];
		mdG.mChromosome = theSplitted[mIndexChromosome];
		mdG.mLocationStart = Long.parseLong(theSplitted[mIndexLocationStart]);
		mdG.mLocationEnd = Long.parseLong(theSplitted[mIndexLocationEnd]);
		mdG.mStrand = theSplitted[mIndexStrand];
		mdG.mDerivedFrom = theSplitted[mIndexDerivedFrom];
		return mdG;
	}

	@Override
	public int compareTo(MetadataMir o)
	{
		int result = this.mMirId.compareTo(o.mMirId);
		if (0==result)
		{
			result = this.mMimatId.compareTo(o.mMimatId);
		}
		return result;
	}

	@Override
	protected boolean processLine(String theLine)
	{
		if (null==M_MIRMAP)
		{
			// first line
			MetadataMir.M_MIRMAP = new TreeMap<>();
			MetadataMir.M_MIMATMAP = new TreeMap<>();
			populateHeaderIndexes(theLine);
		}
		else
		{
			String[] splitted = theLine.split("\t", -1);
			MetadataMir mir = makeMetadata(splitted);
			//
			TreeSet<MetadataMir> myset = M_MIMATMAP.get(mir.mMimatId);
			if (null==myset)
			{
				myset = new TreeSet<>();
			}
			myset.add(mir);
			M_MIMATMAP.put(mir.mMimatId, myset);
			//
			myset = M_MIRMAP.get(mir.mMirId);
			if (null==myset)
			{
				myset = new TreeSet<>();
			}
			myset.add(mir);
			M_MIRMAP.put(mir.mMirId, myset);
		}
		return true;
	}
}

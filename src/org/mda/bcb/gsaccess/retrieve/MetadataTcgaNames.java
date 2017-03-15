/*
GSAccess Copyright 2014, 2015, 2016 University of Texas MD Anderson Cancer Center

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mda.bcb.gsaccess.retrieve;

import java.io.IOException;
import java.util.TreeMap;
import org.mda.bcb.gsaccess.GSAccess;
import org.mda.bcb.gsaccess.ReadZipFile;

/**
 *
 * @author tdcasasent
 */
public class MetadataTcgaNames extends ReadZipFile
{
	static protected String M_PATH = null;
	static protected TreeMap<String, String> M_DATASETNAMES = new TreeMap<>();
	static protected TreeMap<String, String> M_DISEASENAMES = new TreeMap<>();
	static protected TreeMap<String, String> M_SAMPLETYPENAMES = new TreeMap<>();
	static public String M_UNKNOWN = "UNK";
	protected TreeMap<String, String> mMap = null;

	public MetadataTcgaNames(String theZipFile)
	{
		super(theZipFile);
		if (false==theZipFile.equals(M_PATH))
		{
			M_PATH = theZipFile;
			M_DATASETNAMES = new TreeMap<>();
			M_DISEASENAMES = new TreeMap<>();
			M_SAMPLETYPENAMES = new TreeMap<>();
		}
	}
	
	////////////////////////////////////////////////////////////////////////////

	public String getMetadataTcga_DatasetName(String theId, String theInternalPath) throws IOException
	{
		GSAccess.printVersion();
		return getMetadataTcga(theId, theInternalPath, M_DATASETNAMES);
	}

	public String getMetadataTcga_DiseaseName(String theId, String theInternalPath) throws IOException
	{
		GSAccess.printVersion();
		return getMetadataTcga(theId, theInternalPath, M_DISEASENAMES);
	}

	public String getMetadataTcga_SampleTypeName(String theId, String theInternalPath) throws IOException
	{
		GSAccess.printVersion();
		return getMetadataTcga(theId, theInternalPath, M_SAMPLETYPENAMES);
	}

	////////////////////////////////////////////////////////////////////////////

	public String [] getMetadataTcga_Ids_DatasetName(String theInternalPath) throws IOException
	{
		GSAccess.printVersion();
		if (M_DATASETNAMES.isEmpty())
		{
			getMetadataTcga("", theInternalPath, M_DATASETNAMES);
		}
		return M_DATASETNAMES.keySet().toArray(new String[0]);
	}

	public String [] getMetadataTcga_Names_DatasetName(String theInternalPath) throws IOException
	{
		GSAccess.printVersion();
		if (M_DATASETNAMES.isEmpty())
		{
			getMetadataTcga("", theInternalPath, M_DATASETNAMES);
		}
		return M_DATASETNAMES.values().toArray(new String[0]);
	}

	public String [] getMetadataTcga_Ids_DiseaseName(String theInternalPath) throws IOException
	{
		GSAccess.printVersion();
		if (M_DISEASENAMES.isEmpty())
		{
			getMetadataTcga("", theInternalPath, M_DISEASENAMES);
		}
		return M_DISEASENAMES.keySet().toArray(new String[0]);
	}

	public String [] getMetadataTcga_Names_DiseaseName(String theInternalPath) throws IOException
	{
		GSAccess.printVersion();
		if (M_DISEASENAMES.isEmpty())
		{
			getMetadataTcga("", theInternalPath, M_DISEASENAMES);
		}
		return M_DISEASENAMES.values().toArray(new String[0]);
	}

	public String [] getMetadataTcga_Ids_SampleTypeName(String theInternalPath) throws IOException
	{
		GSAccess.printVersion();
		if (M_SAMPLETYPENAMES.isEmpty())
		{
			getMetadataTcga("", theInternalPath, M_SAMPLETYPENAMES);
		}
		return M_SAMPLETYPENAMES.keySet().toArray(new String[0]);
	}

	public String [] getMetadataTcga_Names_SampleTypeName(String theInternalPath) throws IOException
	{
		GSAccess.printVersion();
		if (M_SAMPLETYPENAMES.isEmpty())
		{
			getMetadataTcga("", theInternalPath, M_SAMPLETYPENAMES);
		}
		return M_SAMPLETYPENAMES.values().toArray(new String[0]);
	}

	////////////////////////////////////////////////////////////////////////////

	private String getMetadataTcga(String theId, String theInternalPath, TreeMap<String, String> theMap) throws IOException
	{
		String name = "";
		long start = System.currentTimeMillis();
		try
		{
			if (theMap.isEmpty())
			{
				GSAccess.printWithFlag("getMetadataTcga map is empty");
				mMap = theMap;
				processFile(theInternalPath);
			}
			name = theMap.get(theId.toUpperCase());
		}
		catch(IOException exp)
		{
			M_DATASETNAMES = new TreeMap<>();
			M_DISEASENAMES = new TreeMap<>();
			M_SAMPLETYPENAMES = new TreeMap<>();
			exp.printStackTrace(System.err);
			exp.printStackTrace(System.out);
			throw exp;
		}
		catch(java.lang.NullPointerException exp)
		{
			M_DATASETNAMES = new TreeMap<>();
			M_DISEASENAMES = new TreeMap<>();
			M_SAMPLETYPENAMES = new TreeMap<>();
			exp.printStackTrace(System.err);
			exp.printStackTrace(System.out);
			throw exp;
		}
		catch(Exception exp)
		{
			M_DATASETNAMES = new TreeMap<>();
			M_DISEASENAMES = new TreeMap<>();
			M_SAMPLETYPENAMES = new TreeMap<>();
			exp.printStackTrace(System.err);
			exp.printStackTrace(System.out);
			throw exp;
		}
		long finish = System.currentTimeMillis();
		if (null!=name)
		{
			GSAccess.printWithFlag("getMetadataTcga theId " + theId + " -> " + name + " retrieved for " + theInternalPath + " in " + ((finish - start) / 1000.0) + " seconds");
		}
		else
		{
			name = M_UNKNOWN;
			GSAccess.printWithFlag("getMetadataTcga theId " + theId + " not found for " + theInternalPath + " (using UNK) in " + ((finish - start) / 1000.0) + " seconds");
		}
		return name;
	}

	@Override
	protected boolean processLine(String theLine)
	{
		String[] splitted = theLine.split("\t", -1);
		GSAccess.printWithFlag("getMetadataTcga adding " + splitted[0] + " and " + splitted[1]);
		mMap.put(splitted[0].toUpperCase(), splitted[1]);
		return true;
	}
}

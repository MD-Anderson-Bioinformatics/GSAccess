/*
GSAccess Copyright 2014, 2015, 2016 University of Texas MD Anderson Cancer Center

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mda.bcb.gsaccess.retrieve;

import java.io.IOException;
import java.util.ArrayList;
import org.mda.bcb.gsaccess.GSAccess;
import org.mda.bcb.gsaccess.ReadZipFile;

/**
 *
 * @author tdcasasent
 */
public class MetadataPop extends ReadZipFile
{
	public String mPath = null;
	public int mLength = 0;
	public String [] mValues = null;
	public String [] mIds = null;
	// BARCODEDISEASE
	static protected String M_PATH_BARCODEDISEASE = null;
	static protected int M_LENGTH_BARCODEDISEASE = 0;
	static protected String [] M_VALUES_BARCODEDISEASE = null;
	static protected String [] M_IDS_BARCODEDISEASE = null;
	// BARCODESAMPLECODE
	static protected String M_PATH_BARCODESAMPLECODE = null;
	static protected int M_LENGTH_BARCODESAMPLECODE = 0;
	static protected String [] M_VALUES_BARCODESAMPLECODE = null;
	static protected String [] M_IDS_BARCODESAMPLECODE = null;
	// PATIENTDISEASE
	static protected String M_PATH_PATIENTDISEASE = null;
	static protected int M_LENGTH_PATIENTDISEASE = 0;
	static protected String [] M_VALUES_PATIENTDISEASE = null;
	static protected String [] M_IDS_PATIENTDISEASE = null;
	//
	protected ArrayList<String> mIdsList = null;
	protected ArrayList<String> mDataList = null;


	public MetadataPop(String theZipFile)
	{
		super(theZipFile);
		GSAccess.printVersion();
		mPath = theZipFile;
	}

	// "data/barcode_disease.tsv"
	public boolean getMetadataPop_BarcodeDisease(String theInternalPath) throws IOException
	{
		boolean loaded = false;
		if ((null==M_PATH_BARCODEDISEASE)||(false==mPath.equals(M_PATH_BARCODEDISEASE)))
		{
			loaded = getMetadataPop(theInternalPath);
			if (true==loaded)
			{
				M_PATH_BARCODEDISEASE = mPath;
				M_LENGTH_BARCODEDISEASE = mLength;
				M_VALUES_BARCODEDISEASE = mValues;
				M_IDS_BARCODEDISEASE = mIds;
			}
		}
		else
		{
			loaded = true;
			mLength = M_LENGTH_BARCODEDISEASE;
			mValues = M_VALUES_BARCODEDISEASE;
			mIds = M_IDS_BARCODEDISEASE;
		}
		return loaded;
	}

	// "data/barcode_samplecode.tsv"
	public boolean getMetadataPop_BarcodeSamplecode(String theInternalPath) throws IOException
	{
		boolean loaded = false;
		if ((null==M_PATH_BARCODESAMPLECODE)||(false==mPath.equals(M_PATH_BARCODESAMPLECODE)))
		{
			loaded = getMetadataPop(theInternalPath);
			if (true==loaded)
			{
				M_PATH_BARCODESAMPLECODE = mPath;
				M_LENGTH_BARCODESAMPLECODE = mLength;
				M_VALUES_BARCODESAMPLECODE = mValues;
				M_IDS_BARCODESAMPLECODE = mIds;
			}
		}
		else
		{
			loaded = true;
			mLength = M_LENGTH_BARCODESAMPLECODE;
			mValues = M_VALUES_BARCODESAMPLECODE;
			mIds = M_IDS_BARCODESAMPLECODE;
		}
		return loaded;
	}

	// "data/patient_disease.tsv"
	public boolean getMetadataPop_PatientDisease(String theInternalPath) throws IOException
	{
		boolean loaded = false;
		if ((null==M_PATH_PATIENTDISEASE)||(false==mPath.equals(M_PATH_PATIENTDISEASE)))
		{
			loaded = getMetadataPop(theInternalPath);
			if (true==loaded)
			{
				M_PATH_PATIENTDISEASE = mPath;
				M_LENGTH_PATIENTDISEASE = mLength;
				M_VALUES_PATIENTDISEASE = mValues;
				M_IDS_PATIENTDISEASE = mIds;
			}
		}
		else
		{
			loaded = true;
			mLength = M_LENGTH_PATIENTDISEASE;
			mValues = M_VALUES_PATIENTDISEASE;
			mIds = M_IDS_PATIENTDISEASE;
		}
		return loaded;
	}

	public boolean getMetadataPop(String theInternalPath) throws IOException
	{
		boolean found = false;
		try
		{
			mLength = 0;
			mValues = null;
			mIds = null;
			mIdsList = null;
			mDataList = null;
			long start = System.currentTimeMillis();
			processFile(theInternalPath);
			mLength = mIdsList.size();
			mValues = mDataList.toArray(new String[0]);
			mIds = mIdsList.toArray(new String[0]);
			mIdsList = null;
			mDataList = null;
			found = true;
			long finish = System.currentTimeMillis();
			GSAccess.printWithFlag("ListMetadata " + theInternalPath + " in " + ((finish-start)/1000.0) + " seconds");
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
		}
		return found;
	}
	
	@Override
	protected boolean processLine(String theLine)
	{
		if (null==mIdsList)
		{
			// first line header (ID\tDATA)
			mIdsList = new ArrayList<>();
			mDataList = new ArrayList<>();
		}
		else
		{
			String [] splitted = theLine.split("\t", -1);
			mIdsList.add(splitted[0]);
			mDataList.add(splitted[1]);
		}
		return true;
	}
}

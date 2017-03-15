/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mda.bcb.gsaccess.retrieve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.mda.bcb.gsaccess.GSAccess;

/**
 *
 * @author linux
 */
public class Dataframe
{
	public String mZipFile = null;
	public int mColumnCount = 0;
	public int mRowCount = 0;
	public String [] mColumnHeaders = null;
	public String [][] mDataframe = null;

	public Dataframe(String theZipFile)
	{
		GSAccess.printVersion();
		mZipFile = theZipFile;
	}


	public boolean processFile(String theInternalFile) throws IOException
	{
		boolean found = false;
		GSAccess.printWithFlag("Dataframe::processFile mZipFile='" + mZipFile + "'");
		GSAccess.printWithFlag("Dataframe::processFile theInternalFile='" + theInternalFile + "'");
		try (ZipFile zf = new ZipFile(mZipFile))
		{
			ZipEntry ze = zf.getEntry(theInternalFile);
			if (null==ze)
			{
				found = false;
			}
			else
			{
				found = true;
				try (InputStream is = zf.getInputStream(ze))
				{
					// first, count lines, excluding header
					try(BufferedReader bfr = new BufferedReader(new InputStreamReader(is)))
					{
						for(String line=bfr.readLine() ; (null!=line); line=bfr.readLine())
						{
							mRowCount = mRowCount + 1;
						}
					}
					mRowCount = mRowCount - 1;
				}
				try (InputStream is = zf.getInputStream(ze))
				{
					// next, read headers and data
					int currentRow = 0;
					try(BufferedReader bfr = new BufferedReader(new InputStreamReader(is)))
					{
						for(String line=bfr.readLine() ; (null!=line); line=bfr.readLine())
						{
							if (null==mColumnHeaders)
							{
								// read headers
								mColumnHeaders = line.split("\t", -1);
								mColumnCount = mColumnHeaders.length;
								// [rows][columns]
								mDataframe = new String[mRowCount][mColumnCount];
							}
							else
							{
								// read data
								String [] data = line.split("\t", -1);
								for(int x=0;x<data.length;x++)
								{
									mDataframe[currentRow][x] = data[x];
								}
								currentRow = currentRow + 1;
							}
						}
					}
				}
			}
		}
		return found;
	}
	
}

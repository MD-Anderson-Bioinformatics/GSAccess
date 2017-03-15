/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mda.bcb.gsaccess.retrieve;

import java.io.IOException;
import org.mda.bcb.gsaccess.GSAccess;
import org.mda.bcb.gsaccess.ReadZipFile;

/**
 *
 * @author linux
 */
public class GetTime extends ReadZipFile
{
	public String mTime = null;
	
	public GetTime(String theZipFile)
	{
		super(theZipFile);
		mTime = null;
	}

	public boolean getTime(String theInternalFile) throws IOException
	{
		GSAccess.printVersion();
		mTime = null;
		boolean found = false;
		long start = System.currentTimeMillis();
		found = processFile(theInternalFile);
		long finish = System.currentTimeMillis();
		if(true==found)
		{
			GSAccess.printWithFlag("Time (" + mTime + ") retrieved for " + theInternalFile + " in " + ((finish-start)/1000.0) + " seconds");
		}
		else
		{
			GSAccess.printWithFlag("Time not found for " + theInternalFile + " in " + ((finish-start)/1000.0) + " seconds");
		}
		return found;
	}
	
	@Override
	protected boolean processLine(String theLine)
	{
		if (null==mTime)
		{
			mTime = theLine;
		}
		else
		{
			mTime = mTime + "\n" + theLine;
		}
		return true;
	}
}

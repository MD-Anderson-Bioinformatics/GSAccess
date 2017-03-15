/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mda.bcb.gsaccess.retrieve;

import java.io.IOException;
import java.util.Arrays;
import java.util.TreeSet;
import org.mda.bcb.gsaccess.GSAccess;
import org.mda.bcb.gsaccess.ReadZipFile;

/**
 *
 * @author linux
 */
public class GetMatrixPlatform extends ReadZipFile
{
	public int mSampleSize = 0;
	public int mGenesSize = 0;
	public double [][] mGenesBySamplesValues = null;
	public String [] mSamples = null;
	public String [] mGenes = null;

	public GetMatrixPlatform(String theZipFile)
	{
		super(theZipFile);
		GSAccess.printVersion();
	}

	public boolean getDataMatrix(String theInternalPath, String theNamesInternalPath) throws IOException
	{
		mSampleSize = 0;
		mGenesSize = 0;
		mGenesBySamplesValues = null;
		mSamples = null;
		mGenes = null;
		boolean found = false;
		long start = System.currentTimeMillis();
		// get list of genes to get size
		{
			GetNamesGeneEq lg = new GetNamesGeneEq(mZipFile);
			lg.getNamesGenes(theNamesInternalPath);
			TreeSet<String> allGenes = new TreeSet<>();
			allGenes.addAll(Arrays.asList(lg.mGenes));
			mGenes = allGenes.toArray(new String[0]);
			mGenesSize = mGenes.length;
			GSAccess.printWithFlag("Number of genes " + mGenesSize);
		}
		found = processFile(theInternalPath);
		//
		long finish = System.currentTimeMillis();
		if(true==found)
		{
			GSAccess.printWithFlag("Platform retrieved for " + theInternalPath + " in " + ((finish-start)/1000.0) + " seconds");
		}
		else
		{
			GSAccess.printWithFlag("No Platform retrieved for " + theInternalPath + " in " + ((finish-start)/1000.0) + " seconds");
		}
		return found;
	}

	@Override
	protected boolean processLine(String theLine)
	{
		String [] splitted = theLine.split("\t", -1);
		if (null==mGenesBySamplesValues)
		{
			mSamples = Arrays.copyOfRange(splitted, 1, splitted.length);
			mSampleSize = mSamples.length;
			GSAccess.printWithFlag("Number of sample " + mSampleSize);
			mGenesBySamplesValues = new double[mGenesSize][mSampleSize];
			GSAccess.printWithFlag("made array");
		}
		else
		{
			int index = Arrays.binarySearch(mGenes, splitted[0]);
			if (-1!=index)
			{
				for(int x=1;x<splitted.length;x++)
				{
					mGenesBySamplesValues[index][x-1] = Double.parseDouble(splitted[x]);
				}
			}
			else
			{
				System.err.println("Gene " + splitted[0] + " not found");
			}
		}
		return true;
	}
	
}

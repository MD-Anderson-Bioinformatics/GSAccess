/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mda.bcb.gsaccess.retrieve;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
import org.mda.bcb.gsaccess.ReadZipFile;

/**
 *
 * @author linux
 */
public class GetTranscript extends ReadZipFile
{
	public TreeSet<Transcript> mTranscripts = null;
	public String mGene = null;
	public ArrayList<String> mHeaders = null;
	public boolean mStartsWith = false;
	
	public GetTranscript(String theZipFile)
	{
		super(theZipFile);
		mTranscripts = new TreeSet<>();
		mHeaders = null;
		mGene = null;
	}

	public void getTranscriptForGene(String theFile, String theGene, boolean theStartsWithFlag) throws IOException
	{
		mGene = theGene;
		mHeaders = null;
		mStartsWith = theStartsWithFlag;
		processFile(theFile);
	}
	
	@Override
	protected boolean processLine(String theLine)
	{
		if (null==mHeaders)
		{
			mHeaders = new ArrayList<>();
			mHeaders.addAll(Arrays.asList(theLine.split("\t", -1)));
		}
		else
		{
			String [] splitted = theLine.split("\t", -1);
			String myGene = splitted[mHeaders.indexOf("gene")];
			boolean match = false;
			if (true==mStartsWith)
			{
				match = myGene.startsWith(mGene + "|");
			}
			else
			{
				match = myGene.equals(mGene);
			}
			if (match)
			{
				String myChromosome = splitted[mHeaders.indexOf("chromosome")];
				String myStart = splitted[mHeaders.indexOf("start")];
				String myEnd = splitted[mHeaders.indexOf("end")];
				String myStrand = splitted[mHeaders.indexOf("strand")];
				String myTranscriptType = splitted[mHeaders.indexOf("transcript_type")];
				String myTranscriptId = splitted[mHeaders.indexOf("transcript_id")];
				String myTranscriptSymbol = splitted[mHeaders.indexOf("transcript_symbol")];
				Transcript tran = new Transcript(myGene, myChromosome, myStart, myEnd, myStrand, myTranscriptType, myTranscriptId, myTranscriptSymbol);
				mTranscripts.add(tran);
			}
		}
		return true;
	}
}

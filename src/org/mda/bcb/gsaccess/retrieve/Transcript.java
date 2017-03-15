/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mda.bcb.gsaccess.retrieve;

/**
 *
 * @author linux
 */
public class Transcript implements Comparable<Transcript>
{
	public String mGene = null;
	public String mChromosome = null;
	public String mStart = null;
	public String mEnd = null;
	public String mStrand = null;
	public String mTranscriptType = null;
	public String mTranscriptId = null;
	public String mTranscriptSymbol = null;

	public Transcript(String theGene, String theChromosome, String theStart, String theEnd, String theStrand, String theTranscriptType, String theTranscriptId, String theTranscriptSymbol)
	{
		mGene = theGene;
		mChromosome = theChromosome;
		mStart = theStart;
		mEnd = theEnd;
		mStrand = theStrand;
		mTranscriptType = theTranscriptType;
		mTranscriptId = theTranscriptId;
		mTranscriptSymbol = theTranscriptSymbol;
	}

	@Override
	public int compareTo(Transcript o)
	{
		int comp = this.mGene.compareTo(o.mGene);
		if (0==comp)
		{
			comp = this.mTranscriptId.compareTo(o.mTranscriptId);
		}
		return comp;
	}
}

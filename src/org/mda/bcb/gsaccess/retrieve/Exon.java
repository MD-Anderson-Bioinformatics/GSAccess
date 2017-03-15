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
public class Exon implements Comparable<Exon>
{
	public String mGene = null;
	public String mChromosome = null;
	public String mStart = null;
	public String mEnd = null;
	public String mStrand = null;
	public String mExonType = null;
	public String mExonId = null;
	public int mExonNumber = -1;
	public String mTranscriptId = null;
	public String mTranscriptSymbol = null;

	public Exon(String theGene, String theChromosome, String theStart, String theEnd, String theStrand, String theExonType, String theExonId, int theExonNumber, String theTranscriptId, String theTranscriptSymbol)
	{
		mGene = theGene;
		mChromosome = theChromosome;
		mStart = theStart;
		mEnd = theEnd;
		mStrand = theStrand;
		mExonType = theExonType;
		mExonId = theExonId;
		mExonNumber = theExonNumber;
		mTranscriptId = theTranscriptId;
		mTranscriptSymbol = theTranscriptSymbol;
	}

	@Override
	public int compareTo(Exon o)
	{
		int comp = this.mGene.compareTo(o.mGene);
		if (0==comp)
		{
			comp = this.mTranscriptId.compareTo(o.mTranscriptId);
			if (0==comp)
			{
				comp = Integer.compare(this.mExonNumber, o.mExonNumber);
			}
		}
		return comp;
	}
}

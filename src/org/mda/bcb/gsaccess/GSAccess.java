/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mda.bcb.gsaccess;

import java.io.IOException;
import org.mda.bcb.gsaccess.retrieve.Dataframe;

/**
 *
 * @author linux
 */
public class GSAccess
{

	public static boolean mVerboseFlag = true;

	public static void setVerboseFlag(Boolean theVerboseFlag)
	{
		mVerboseFlag = theVerboseFlag;
	}

	public static void printWithFlag(String theString)
	{
		if (true == mVerboseFlag)
		{
			System.out.println(theString);
		}
	}

	public static String printVersion()
	{
		GSAccess.printWithFlag("GSAccess 2017-03-13-1630");
		return "GSAccess 2017-03-13-1630";
	}

	public static void main(String[] args) throws IOException
	{
		//TESTmain(args);
		//DEVmain(args);
		REALmain(args);
	}

	public static void TESTmain(String[] args) throws IOException
	{
		System.out.println("If you see this message in production, it is an ERROR and GSAccess was compiled wrong");
		try
		{
			GSAccess.printVersion();
			String outputDir = "GS_RESULTS";
			String zipfile = "GeneSurvey_2016_08_26_0922.zip";
			CallFromR call = new CallFromR(zipfile);
			System.out.println("version=" + call.printVersion());
			System.out.println("time=" + call.getValue_Time());
			String [] names = call.getNames_Mutations();
			System.out.println("mutation names: length=" + names.length + " [1]=" + names[1] + " [100]=" + names[100]);
			names = call.getNames_RnaSeq2();
			System.out.println("RnaSeq2 names: length=" + names.length + " [1]=" + names[1] + " [100]=" + names[100]);
			names = call.getNames_SNP6();
			System.out.println("SNP6 names: length=" + names.length + " [1]=" + names[1] + " [100]=" + names[100]);
			names = call.getNames_Meth450();
			System.out.println("Meth450 names: length=" + names.length + " [1]=" + names[1] + " [100]=" + names[100]);
			names = call.getNames_Meth27();
			System.out.println("Meth27 names: length=" + names.length + " [1]=" + names[1] + " [100]=" + names[100]);
			names = call.getNames_miRNASeq();
			System.out.println("miRNASeq names: length=" + names.length + " [1]=" + names[1] + " [100]=" + names[100]);
			////////////////////////////////////////////////////////////////////
			String geneSymbol = "AATF";
			System.out.println("getMutationDetails_Dataframe gene=" + geneSymbol);
			Dataframe df = call.getMutationDetails_Dataframe(geneSymbol);
			System.out.println("after");
			/*
			System.out.println("plot_GeneSymbol_Mutations gene=" + geneSymbol);
			new File(new File(outputDir, geneSymbol.substring(0,1)), geneSymbol)
			plot_GeneSymbol_Mutations(geneMut, theOutputDir=file.path(outputBasePath, substr(sharedGene, 1, 1), sharedGene, "images"), theZipFile=zipFile)
			System.out.println("plot_GeneSymbol_RnaSeq2 gene=" + geneSymbol);
			plot_GeneSymbol_RnaSeq2(geneRna, theOutputDir=file.path(outputBasePath, substr(sharedGene, 1, 1), sharedGene, "images"), theUseDeltaFlag=FALSE, theZipFile=zipFile)
			System.out.println("plot_GeneSymbol_SNP6 gene=" + geneSymbol);
			plot_GeneSymbol_SNP6(geneSnp, theOutputDir=file.path(outputBasePath, substr(sharedGene, 1, 1), sharedGene, "images"), theUseDeltaFlag=FALSE, theZipFile=zipFile)
			System.out.println("plot_GeneSymbol_Meth450 gene=" + geneSymbol);
			plot_GeneSymbol_Meth450(gene450, theOutputDir=file.path(outputBasePath, substr(sharedGene, 1, 1), sharedGene, "images"), theUseDeltaFlag=FALSE, theZipFile=zipFile)
			System.out.println("plot_GeneSymbol_Meth27 gene=" + geneSymbol);
			plot_GeneSymbol_Meth27(geneM27, theOutputDir=file.path(outputBasePath, substr(sharedGene, 1, 1), sharedGene, "images"), theUseDeltaFlag=FALSE, theZipFile=zipFile)
*/
		}
		catch (Exception exp)
		{
			exp.printStackTrace(System.err);
		}
	}

	public static void REALmain(String[] args) throws IOException
	{
		try
		{
			GSAccess.printVersion();
		}
		catch (Exception exp)
		{
			exp.printStackTrace(System.err);
		}
	}

	static public void logMemory()
	{
		/*System.gc();
		 try
		 {
		 Thread.sleep(1000);
		 }
		 catch (Exception ignore)
		 {
		 // ignore
		 }*/
		long usedMB = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024 / 1024;
		long maxMB = (Runtime.getRuntime().totalMemory()) / 1024 / 1024;
		System.out.println("memory used = " + usedMB + "/" + maxMB);
		System.out.flush();
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mda.bcb.gsaccess;

import java.io.IOException;
import java.util.TreeSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mda.bcb.gsaccess.retrieve.Dataframe;
import org.mda.bcb.gsaccess.retrieve.Exon;
import org.mda.bcb.gsaccess.retrieve.GetDataClinical;
import org.mda.bcb.gsaccess.retrieve.GetDataMatrix;
import org.mda.bcb.gsaccess.retrieve.GetImputedNAsMatrix;
import org.mda.bcb.gsaccess.retrieve.MetadataGene;
import org.mda.bcb.gsaccess.retrieve.MetadataMir;
import org.mda.bcb.gsaccess.retrieve.MetadataPop;
import org.mda.bcb.gsaccess.retrieve.MetadataProbe;
import org.mda.bcb.gsaccess.retrieve.Transcript;

/**
 *
 * @author linux
 */
public class CallFromRTest
{
	
	public CallFromRTest()
	{
	}
	
	@BeforeClass
	public static void setUpClass()
	{
	}
	
	@AfterClass
	public static void tearDownClass()
	{
	}
	
	@Before
	public void setUp()
	{
	}
	
	@After
	public void tearDown()
	{
	}

	/**
	 * Test of printVersion method, of class CallFromR.
	 */
	@Test
	public void testPrintVersion()
	{
		System.out.println("printVersion");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		String result = instance.printVersion();
		assertNotNull(result);
		assertNotSame("", result);
	}

	/**
	 * Test of getValue_Time method, of class CallFromR.
	 */
	@Test
	public void testGetValue_Time() throws Exception
	{
		System.out.println("getValue_Time");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		String result = instance.getValue_Time();
		assertNotNull(result);
		assertNotSame("", result);
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	/**
	 * Test of getNames_Mutations method, of class CallFromR.
	 */
	@Test
	public void testGetNames_Mutations() throws Exception
	{
		System.out.println("getNames_Mutations");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		String[] result = instance.getNames_Mutations();
		assertEquals(31779, result.length);
		assertEquals("101928757", result[0]);
		assertEquals("uc004ewl.1", result[31778]);
	}

	/**
	 * Test of getNames_RnaSeq2 method, of class CallFromR.
	 */
	@Test
	public void testGetNames_RnaSeq2() throws Exception
	{
		System.out.println("getNames_RnaSeq2");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		String[] result = instance.getNames_RnaSeq2();
		assertEquals(20531, result.length);
		assertEquals("?|100130426", result[0]);
		assertEquals("ZZZ3", result[20530]);
	}

	/**
	 * Test of getNames_RnaSeq method, of class CallFromR.
	 */
	@Test
	public void testGetNames_RnaSeq() throws Exception
	{
		System.out.println("getNames_RnaSeq");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		String[] result = instance.getNames_RnaSeq();
		assertEquals(20532, result.length);
		assertEquals("?|100130426", result[0]);
		assertEquals("ZZZ3", result[20531]);
	}

	/**
	 * Test of getNames_SNP6 method, of class CallFromR.
	 */
	@Test
	public void testGetNames_SNP6() throws Exception
	{
		System.out.println("getNames_SNP6");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		String[] result = instance.getNames_SNP6();
		assertEquals(57736, result.length);
		assertEquals("5S_rRNA|12", result[0]);
		assertEquals("yR211F11.2", result[57735]);
	}

	/**
	 * Test of getNames_Meth450 method, of class CallFromR.
	 */
	@Test
	public void testGetNames_Meth450() throws Exception
	{
		System.out.println("getNames_Meth450");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		String[] expResult = null;
		String[] result = instance.getNames_Meth450();
		assertEquals(485577, result.length);
		assertEquals("cg00000029", result[0]);
		assertEquals("rs9839873", result[485576]);
	}

	/**
	 * Test of getNames_Meth27 method, of class CallFromR.
	 */
	@Test
	public void testGetNames_Meth27() throws Exception
	{
		System.out.println("getNames_Meth27");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		String[] expResult = null;
		String[] result = instance.getNames_Meth27();
		assertEquals(27578, result.length);
		assertEquals("cg00000292", result[0]);
		assertEquals("cg27665659", result[27577]);
	}

	/**
	 * Test of getNames_miRNASeq method, of class CallFromR.
	 */
	@Test
	public void testGetNames_miRNASeq() throws Exception
	{
		System.out.println("getNames_miRNASeq");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		String[] expResult = null;
		String[] result = instance.getNames_miRNASeq();
		assertEquals(980, result.length);
		assertEquals("hsa-let-7a.MIMAT0000062", result[0]);
		assertEquals("hsa-mir-99b.MIMAT0000689", result[979]);
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	/**
	 * Test of getMapping_Meth450 method, of class CallFromR.
	 */
	@Test
	public void testGetMapping_Meth450() throws Exception
	{
		System.out.println("getMapping_Meth450");
		String theGene = "TTTY14";
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		String[] result = instance.getMapping_Meth450(theGene);
		assertEquals(12, result.length);
		assertEquals("cg00212031", result[0]);
		assertEquals("cg26251715", result[11]);
	}

	/**
	 * Test of getMapping_Meth27 method, of class CallFromR.
	 */
	@Test
	public void testGetMapping_Meth27() throws Exception
	{
		System.out.println("getMapping_Meth27");
		String theGene = "TP53";
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		String[] result = instance.getMapping_Meth27(theGene);
		assertEquals(2, result.length);
		assertEquals("cg11519508", result[0]);
		assertEquals("cg22175811", result[1]);
	}

	/**
	 * Test of getMappingGeneSymbols_Meth450 method, of class CallFromR.
	 */
	@Test
	public void testGetMappingGeneSymbols_Meth450() throws Exception
	{
		System.out.println("getMappingGeneSymbols_Meth450");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		String[] result = instance.getMappingGeneSymbols_Meth450();
		assertEquals(687137, result.length);
		assertEquals("TTTY18", result[0]);
		assertEquals("ATXN10", result[687136]);
	}

	/**
	 * Test of getMappingGeneSymbols_Meth27 method, of class CallFromR.
	 */
	@Test
	public void testGetMappingGeneSymbols_Meth27() throws Exception
	{
		System.out.println("getMappingGeneSymbols_Meth27");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		String[] result = instance.getMappingGeneSymbols_Meth27();
		assertEquals(27572, result.length);
		assertEquals("ATP2A1", result[0]);
		assertEquals("AP1S1", result[27571]);
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	/**
	 * Test of getDataClinical method, of class CallFromR.
	 */
	@Test
	public void testGetDataClinical() throws Exception
	{
		System.out.println("getDataClinical");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		GetDataClinical result = instance.getDataClinical();
		assertEquals(11160, result.mGenesBySamplesValues.length);
		assertEquals(11160, result.mPatientIds.length);
		assertEquals(15, result.mColumnLabels.length);
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	/**
	 * Test of getDataMatrix_Mutations method, of class CallFromR.
	 */
	@Test
	public void testGetDataMatrix_Mutations() throws Exception
	{
		System.out.println("getDataMatrix_Mutations");
		String[] theGenes = { "A2BP1" };
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		GetDataMatrix result = instance.getDataMatrix_Mutations(theGenes);
		assertEquals(6441, result.mSampleSize);
		assertEquals(1, result.mGenesSize);
		assertEquals(6441, result.mSamples.length);
	}

	/**
	 * Test of getDataMatrix_RnaSeq2 method, of class CallFromR.
	 */
	@Test
	public void testGetDataMatrix_RnaSeq2() throws Exception
	{
		System.out.println("getDataMatrix_RnaSeq2");
		String[] theGenes = { "ANKRD23" };
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		GetDataMatrix result = instance.getDataMatrix_RnaSeq2(theGenes);
		assertEquals(10464, result.mSampleSize);
		assertEquals(1, result.mGenesSize);
		assertEquals(10464, result.mSamples.length);
	}

	/**
	 * Test of getDataMatrix_RnaSeq method, of class CallFromR.
	 */
	@Test
	public void testGetDataMatrix_RnaSeq() throws Exception
	{
		System.out.println("getDataMatrix_RnaSeq");
		String[] theGenes = { "C2CD4C" };
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		GetDataMatrix result = instance.getDataMatrix_RnaSeq(theGenes);
		assertEquals(2231, result.mSampleSize);
		assertEquals(2231, result.mSamples.length);
		assertEquals(1, result.mGenes.length);
	}

	/**
	 * Test of getDataMatrix_SNP6 method, of class CallFromR.
	 */
	@Test
	public void testGetDataMatrix_SNP6() throws Exception
	{
		System.out.println("getDataMatrix_SNP6");
		String[] theGenes = { "ZNF404" };
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		GetDataMatrix result = instance.getDataMatrix_SNP6(theGenes);
		assertEquals(22447, result.mSampleSize);
		assertEquals(22447, result.mSamples.length);
	}

	/**
	 * Test of getDataMatrix_Meth450 method, of class CallFromR.
	 */
	@Test
	public void testGetDataMatrix_Meth450() throws Exception
	{
		System.out.println("getDataMatrix_Meth450");
		String[] theGenes = { "ch.X.120845377F" };
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		GetDataMatrix result = instance.getDataMatrix_Meth450(theGenes);
		assertEquals(10181, result.mSampleSize);
		assertEquals(10181, result.mSamples.length);
	}

	/**
	 * Test of getDataMatrix_Meth27 method, of class CallFromR.
	 */
	@Test
	public void testGetDataMatrix_Meth27() throws Exception
	{
		System.out.println("getDataMatrix_Meth27");
		String[] theGenes = { "cg27665659" };
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		GetDataMatrix result = instance.getDataMatrix_Meth27(theGenes);
		assertEquals(2717, result.mSampleSize);
		assertEquals(2717, result.mSamples.length);
	}

	/**
	 * Test of getDataMatrix_miRNASeq method, of class CallFromR.
	 */
	@Test
	public void testGetDataMatrix_miRNASeq() throws Exception
	{
		System.out.println("getDataMatrix_miRNASeq");
		String[] theGenes = { "hsa-mir-17.MIMAT0000070" };
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		GetDataMatrix result = instance.getDataMatrix_miRNASeq(theGenes);
		assertEquals(9695, result.mSampleSize);
		assertEquals(9695, result.mSamples.length);
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	/**
	 * Test of getImputedNAsMatrix_RnaSeq2 method, of class CallFromR.
	 */
	@Test
	public void testGetImputedNAsMatrix_RnaSeq2() throws Exception
	{
		System.out.println("getImputedNAsMatrix_RnaSeq2");
		String[] theGenes = { "ZZZ3" };
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		GetImputedNAsMatrix result = instance.getImputedNAsMatrix_RnaSeq2(theGenes);
		assertEquals(10464, result.mSampleSize);
		assertEquals(10464, result.mSamples.length);
	}

	/**
	 * Test of getImputedNAsMatrix_RnaSeq method, of class CallFromR.
	 */
	@Test
	public void testGetImputedNAsMatrix_RnaSeq() throws Exception
	{
		System.out.println("getImputedNAsMatrix_RnaSeq");
		String[] theGenes = { "ZZZ3" };
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		GetImputedNAsMatrix result = instance.getImputedNAsMatrix_RnaSeq(theGenes);
		assertEquals(2231, result.mSampleSize);
		assertEquals(2231, result.mSamples.length);
	}

	/**
	 * Test of getImputedNAsMatrix_SNP6 method, of class CallFromR.
	 */
	@Test
	public void testGetImputedNAsMatrix_SNP6() throws Exception
	{
		System.out.println("getImputedNAsMatrix_SNP6");
		String[] theGenes = { "RP11-42L13.2" };
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		GetImputedNAsMatrix result = instance.getImputedNAsMatrix_SNP6(theGenes);
		assertEquals(22447, result.mSampleSize);
		assertEquals(22447, result.mSamples.length);
	}

	/**
	 * Test of getImputedNAsMatrix_Meth450 method, of class CallFromR.
	 */
	@Test
	public void testGetImputedNAsMatrix_Meth450() throws Exception
	{
		System.out.println("getImputedNAsMatrix_Meth450");
		String[] theGenes = { "cg00013006" };
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		GetImputedNAsMatrix result = instance.getImputedNAsMatrix_Meth450(theGenes);
		assertEquals(10181, result.mSampleSize);
		assertEquals(10181, result.mSamples.length);
	}

	/**
	 * Test of getImputedNAsMatrix_Meth27 method, of class CallFromR.
	 */
	@Test
	public void testGetImputedNAsMatrix_Meth27() throws Exception
	{
		System.out.println("getImputedNAsMatrix_Meth27");
		String[] theGenes = { "cg00386408" };
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		GetImputedNAsMatrix result = instance.getImputedNAsMatrix_Meth27(theGenes);
		assertEquals(2717, result.mSampleSize);
		assertEquals(2717, result.mSamples.length);
	}

	/**
	 * Test of getImputedNAsMatrix_miRNASeq method, of class CallFromR.
	 */
	@Test
	public void testGetImputedNAsMatrix_miRNASeq() throws Exception
	{
		System.out.println("getImputedNAsMatrix_miRNASeq");
		String[] theGenes = { "hsa-mir-99b.MIMAT0000689" };
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		GetImputedNAsMatrix result = instance.getImputedNAsMatrix_miRNASeq(theGenes);
		assertEquals(9695, result.mSampleSize);
		assertEquals(9695, result.mSamples.length);
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	/**
	 * Test of getMetadataPop_BarcodeDisease method, of class CallFromR.
	 */
	@Test
	public void testGetMetadataPop_BarcodeDisease() throws Exception
	{
		System.out.println("getMetadataPop_BarcodeDisease");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		MetadataPop result = instance.getMetadataPop_BarcodeDisease();
		assertEquals(156972, result.mLength);
		assertEquals(156972, result.mValues.length);
		assertEquals(156972, result.mIds.length);
	}

	/**
	 * Test of getMetadataPop_BarcodeSamplecode method, of class CallFromR.
	 */
	@Test
	public void testGetMetadataPop_BarcodeSamplecode() throws Exception
	{
		System.out.println("getMetadataPop_BarcodeSamplecode");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		MetadataPop result = instance.getMetadataPop_BarcodeSamplecode();
		assertEquals(156972, result.mLength);
		assertEquals(156972, result.mValues.length);
		assertEquals(156972, result.mIds.length);
	}

	/**
	 * Test of getMetadataPop_PatientDisease method, of class CallFromR.
	 */
	@Test
	public void testGetMetadataPop_PatientDisease() throws Exception
	{
		System.out.println("getMetadataPop_PatientDisease");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		MetadataPop result = instance.getMetadataPop_PatientDisease();
		assertEquals(11320, result.mLength);
		assertEquals(11320, result.mValues.length);
		assertEquals(11320, result.mIds.length);
	}
	
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	/**
	 * Test of getMetadataList_Mutations method, of class CallFromR.
	 */
	@Test
	public void testGetMetadataList_Mutations() throws Exception
	{
		System.out.println("getMetadataList_Mutations");
		String theStandardizedDataId = "BPY2B";
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		MetadataGene[] result = instance.getMetadataList_Mutations(theStandardizedDataId);
		assertEquals(1, result.length);
		assertEquals("Y", result[0].mChromosome);
		assertEquals("+", result[0].mStrand);
		assertEquals("26753707", result[0].mLocationStart);
	}

	/**
	 * Test of getMetadataList_RNASeq method, of class CallFromR.
	 */
	@Test
	public void testGetMetadataList_RNASeq() throws Exception
	{
		System.out.println("getMetadataList_RNASeq");
		String theStandardizedDataId = "AASDHPPT|60496";
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		MetadataGene[] result = instance.getMetadataList_RNASeq(theStandardizedDataId);
		assertEquals(1, result.length);
		assertEquals("60496", result[0].mGeneId);
		assertEquals("105969419", result[0].mLocationEnd);
	}

	/**
	 * Test of getMetadataList_RNASeqV2 method, of class CallFromR.
	 */
	@Test
	public void testGetMetadataList_RNASeqV2() throws Exception
	{
		System.out.println("getMetadataList_RNASeqV2");
		String theStandardizedDataId = "AASDHPPT|60496";
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		MetadataGene[] result = instance.getMetadataList_RNASeqV2(theStandardizedDataId);
		assertEquals(1, result.length);
		assertEquals("60496", result[0].mGeneId);
		assertEquals("105969419", result[0].mLocationEnd);
	}

	/**
	 * Test of getMetadataList_HG18 method, of class CallFromR.
	 */
	@Test
	public void testGetMetadataList_HG18() throws Exception
	{
		System.out.println("getMetadataList_HG18");
		String theStandardizedDataId = "Y_RNA|ENSG00000207061";
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		MetadataGene[] result = instance.getMetadataList_HG18(theStandardizedDataId);
		assertEquals(1, result.length);
		assertEquals("-", result[0].mStrand);
		assertEquals("155671678", result[0].mLocationEnd);
		assertEquals("7", result[0].mChromosome);
	}

	/**
	 * Test of getMetadataList_HG19 method, of class CallFromR.
	 */
	@Test
	public void testGetMetadataList_HG19() throws Exception
	{
		System.out.println("getMetadataList_HG19");
		String theStandardizedDataId = "ABCF2P1";
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		MetadataGene[] result = instance.getMetadataList_HG19(theStandardizedDataId);
		assertEquals(1, result.length);
		assertEquals("+", result[0].mStrand);
		assertEquals("88368172", result[0].mLocationEnd);
		assertEquals("3", result[0].mChromosome);
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	/**
	 * Test of getMetadata_Meth27 method, of class CallFromR.
	 */
	@Test
	public void testGetMetadata_Meth27() throws Exception
	{
		System.out.println("getMetadata_Meth27");
		String theProbe = "cg27661264";
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		MetadataProbe result = instance.getMetadata_Meth27(theProbe);
		assertEquals("20", result.mChromosome);
		assertEquals("GNAS", result.mGene[0]);
		assertEquals(57427738L, result.mProbeLocation);
	}

	/**
	 * Test of getMetadata_Meth450 method, of class CallFromR.
	 */
	@Test
	public void testGetMetadata_Meth450() throws Exception
	{
		System.out.println("getMetadata_Meth450");
		String theProbe = "cg19421526";
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		MetadataProbe result = instance.getMetadata_Meth450(theProbe);
		assertEquals("10", result.mChromosome);
		assertEquals("F", result.mStrand);
		assertEquals(99734513, result.mProbeLocation);
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	/**
	 * Test of getMirList method, of class CallFromR.
	 */
	@Test
	public void testGetMirList() throws Exception
	{
		System.out.println("getMirList");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		String[] result = instance.getMirList();
		assertEquals(4446, result.length);
		assertEquals("hsa-let-7a-1", result[0]);
		assertEquals("hsa-mir-99b", result[4445]);
	}

	/**
	 * Test of getMimatList method, of class CallFromR.
	 */
	@Test
	public void testGetMimatList() throws Exception
	{
		System.out.println("getMimatList");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		String[] result = instance.getMimatList();
		assertEquals(4665, result.length);
		assertEquals("MI0000060", result[0]);
		assertEquals("MIMAT0031181", result[4664]);
	}

	/**
	 * Test of getMetadata_miRNA_mir method, of class CallFromR.
	 */
	@Test
	public void testGetMetadata_miRNA_mir() throws Exception
	{
		System.out.println("getMetadata_miRNA_mir");
		String theMirId = "hsa-let-7c-5p";
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		MetadataMir[] result = instance.getMetadata_miRNA_mir(theMirId);
		assertEquals(1, result.length);
		assertEquals("21", result[0].mChromosome);
		assertEquals(17912158L, result[0].mLocationStart);
		assertEquals(17912179L, result[0].mLocationEnd);
	}

	/**
	 * Test of getMetadata_miRNA_mimat method, of class CallFromR.
	 */
	@Test
	public void testGetMetadata_miRNA_mimat() throws Exception
	{
		System.out.println("getMetadata_miRNA_mimat");
		String theMimatId = "MIMAT0000064";
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		MetadataMir[] result = instance.getMetadata_miRNA_mimat(theMimatId);
		assertEquals(1, result.length);
		assertEquals("21", result[0].mChromosome);
		assertEquals(17912158L, result[0].mLocationStart);
		assertEquals(17912179L, result[0].mLocationEnd);
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	/**
	 * Test of getOneToOne_UCSC_List method, of class CallFromR.
	 */
	@Test
	public void testGetOneToOne_UCSC_List() throws Exception
	{
		System.out.println("getOneToOne_UCSC_List");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		String[] result = instance.getOneToOne_UCSC_List();
		assertEquals(31692, result.length);
		assertEquals("uc001aaa.3", result[0]);
		assertEquals("uc284rci.1", result[31691]);
	}

	/**
	 * Test of getOneToOne_GeneSymbol_List method, of class CallFromR.
	 */
	@Test
	public void testGetOneToOne_GeneSymbol_List() throws Exception
	{
		System.out.println("getOneToOne_GeneSymbol_List");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		String[] result = instance.getOneToOne_GeneSymbol_List();
		assertEquals(31692, result.length);
		assertEquals("6M1-18", result[0]);
		assertEquals("ZZZ3", result[31691]);
	}

	/**
	 * Test of getOneToOne_GeneSymbol_UCID method, of class CallFromR.
	 */
	@Test
	public void testGetOneToOne_GeneSymbol_UCID() throws Exception
	{
		System.out.println("getOneToOne_GeneSymbol_UCID");
		String theId = "uc001abw.2"; // uc001abw.1	SAMD11
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		String expResult = "SAMD11";
		String result = instance.getOneToOne_GeneSymbol_UCID(theId);
		assertEquals(expResult, result);
	}

	/**
	 * Test of getOneToOne_UCID_GeneSymbol method, of class CallFromR.
	 */
	@Test
	public void testGetOneToOne_UCID_GeneSymbol() throws Exception
	{
		System.out.println("getOneToOne_UCID_GeneSymbol");
		String theId = "SAMD11"; // uc001abw.2	SAMD11
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		String expResult = "uc001abw.2";
		String result = instance.getOneToOne_UCID_GeneSymbol(theId);
		assertEquals(expResult, result);
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	/**
	 * Test of getList_GeneSymbol_Synonym method, of class CallFromR.
	 */
	@Test
	public void testGetList_GeneSymbol_Synonym() throws Exception
	{
		System.out.println("getList_GeneSymbol_Synonym");
		String theId = "AACS"; // AACS	ACSF1	FLJ12389	SUR-5
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		String[] expResult = { "AACS" };
		String[] result = instance.getList_GeneSymbol_Synonym(theId);
		assertArrayEquals(expResult, result);
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	/**
	 * Test of findNeighbors_RnaSeq method, of class CallFromR.
	 */
	@Test
	public void testFindNeighbors_RnaSeq() throws Exception
	{
		System.out.println("findNeighbors_RnaSeq");
		long theStart = 52566300; // 52566327
		long theStop =  52645500; // 52645435
		String theChromosome = "10";
		String theStrand = "-";
		// A1CF
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		MetadataGene[] result = instance.findNeighbors_RnaSeq(theStart, theStop, theChromosome, theStrand);
		assertEquals(1, result.length);
		assertEquals("10", result[0].mChromosome);
		assertEquals("-", result[0].mStrand);
		assertEquals("52645435", result[0].mLocationStart);
		assertEquals("52566327", result[0].mLocationEnd);
		assertEquals("A1CF", result[0].mGeneSymbol);
	}

	/**
	 * Test of findNeighbors_RnaSeq2 method, of class CallFromR.
	 */
	@Test
	public void testFindNeighbors_RnaSeq2() throws Exception
	{
		System.out.println("findNeighbors_RnaSeq2");
		long theStart = 7565000; // 7565097
		long theStop =  7590900; // 7590863
		String theChromosome = "17";
		String theStrand = "-";
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		MetadataGene[] result = instance.findNeighbors_RnaSeq2(theStart, theStop, theChromosome, theStrand);
		assertEquals(1, result.length);
		assertEquals("17", result[0].mChromosome);
		assertEquals("-", result[0].mStrand);
		assertEquals("7590863", result[0].mLocationStart);
		assertEquals("7565097", result[0].mLocationEnd);
		assertEquals("TP53", result[0].mGeneSymbol);
	}

	/**
	 * Test of findNeighbors_Meth450 method, of class CallFromR.
	 */
	@Test
	public void testFindNeighbors_Meth450() throws Exception
	{
		System.out.println("findNeighbors_Meth450");
		long theStart = 72046521L; // 72046522
		long theStop =  72046523L;
		String theChromosome = "16";
		//"cg05013913"
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		MetadataProbe[] result = instance.findNeighbors_Meth450(theStart, theStop, theChromosome);
		assertEquals(1, result.length);
		assertEquals("R", result[0].mStrand);
		assertEquals(72046522, result[0].mProbeLocation);
	}

	/**
	 * Test of findNeighbors_Meth27 method, of class CallFromR.
	 */
	@Test
	public void testFindNeighbors_Meth27() throws Exception
	{
		System.out.println("findNeighbors_Meth27");
		long theStart = 100797594L; // 100797595
		long theStop = 100797596L;
		String theChromosome = "7";
		// "cg27665659"
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		MetadataProbe[] result = instance.findNeighbors_Meth27(theStart, theStop, theChromosome);
		assertEquals(1, result.length);
		assertEquals("7", result[0].mChromosome);
		assertEquals(100797595, result[0].mProbeLocation);
	}

	/**
	 * Test of findNeighbors_HG19 method, of class CallFromR.
	 */
	@Test
	public void testFindNeighbors_HG18() throws Exception
	{
		System.out.println("testFindNeighbors_HG18");
		// TP53	17	7512445	7531642	-
		long theStart = 7510000; // 7512445 <= theMin
		long theStop =  7540000; // 7531642 <= theMax
		String theChromosome = "17";
		String theStrand = "-";
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		MetadataGene[] result = instance.findNeighbors_HG18(theStart, theStop, theChromosome, theStrand);
		assertEquals(1, result.length);
		assertEquals("TP53", result[0].mGeneSymbol);
		assertEquals("17", result[0].mChromosome);
		assertEquals("7512445", result[0].mLocationStart);
		assertEquals("7531642", result[0].mLocationEnd);
	}

	/**
	 * Test of findNeighbors_HG19 method, of class CallFromR.
	 */
	@Test
	public void testFindNeighbors_HG19() throws Exception
	{
		System.out.println("findNeighbors_HG19");
		// if ((start<=theMax)&&(theMin<=end))
		long theStart = 150194700; // 150194767 <= theMin
		long theStop =  150194900; // 150194878 <= theMax
		String theChromosome = "X";
		String theStrand = "-";
		// 5S_rRNA|ENSG00000212595
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		MetadataGene[] result = instance.findNeighbors_HG19(theStart, theStop, theChromosome, theStrand);
		assertEquals(1, result.length);
		assertEquals("5S_rRNA|ENSG00000212595", result[0].mGeneSymbol);
		assertEquals("X", result[0].mChromosome);
		assertEquals("150194767", result[0].mLocationStart);
		assertEquals("150194878", result[0].mLocationEnd);
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	/**
	 * Test of getMutationDetails_Dataframe method, of class CallFromR.
	 */
	@Test
	public void testGetMutationDetails_Dataframe() throws Exception
	{
		System.out.println("getMutationDetails_Dataframe");
		String theGene = "ACAD8";
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		Dataframe result = instance.getMutationDetails_Dataframe(theGene);
		assertEquals(44, result.mColumnCount);
		assertEquals(33, result.mRowCount);
	}

	/**
	 * Test of getMutationDetails_GeneList method, of class CallFromR.
	 */
	@Test
	public void testGetMutationDetails_GeneList() throws Exception
	{
		System.out.println("getMutationDetails_GeneList");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		String[] result = instance.getMutationDetails_GeneList();
		assertEquals(28744, result.length);
		assertEquals("101928503", result[1]);
		assertEquals("uc010dwx.1", result[28743]);
	}
	
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testGetTranscripts_HG18() throws IOException
	{
		System.out.println("testGetTranscripts_HG18");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		Transcript [] result = instance.getTranscripts_HG18("TP53|ENSG00000141510", false);
		assertEquals(3, result.length);
		assertEquals("TP53-201", result[0].mTranscriptSymbol);
		result = instance.getTranscripts_HG18("TP53TG3", true);
		assertEquals(11, result.length);
		assertEquals("TP53TG3-201", result[0].mTranscriptSymbol);
	}
	
	@Test
	public void testGetTranscripts_HG19() throws IOException
	{
		System.out.println("testGetTranscripts_HG19");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		Transcript [] result = instance.getTranscripts_HG19("TP53|ENSG00000141510", false);
		assertEquals(17, result.length);
		assertEquals("TP53-001", result[0].mTranscriptSymbol);
		result = instance.getTranscripts_HG19("TP53TG3", true);
		assertEquals(9, result.length);
		assertEquals("TP53TG3-201", result[0].mTranscriptSymbol);
	}
	
	@Test
	public void testGetTranscripts_GRCh38() throws IOException
	{
		System.out.println("testGetTranscripts_GRCh38");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		Transcript [] result = instance.getTranscripts_GRCh38("TP53|ENSG00000141510", false);
		assertEquals(28, result.length);
		assertEquals("TP53-001", result[0].mTranscriptSymbol);
		result = instance.getTranscripts_GRCh38("TP53TG3", true);
		assertEquals(8, result.length);
		assertEquals("TP53TG3-001", result[0].mTranscriptSymbol);
	}
	
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	@Test
	public void testGetExons_HG18() throws IOException
	{
		System.out.println("testGetExons_HG18");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		Exon [] result = instance.getExons_HG18("TP53|ENSG00000141510", false);
		assertEquals(31, result.length);
		assertEquals("1", result[0].mExonId);
		result = instance.getExons_HG18("TP53TG3", true);
		assertEquals(22, result.length);
		assertEquals("1", result[0].mExonId);
	}
	
	@Test
	public void testGetExons_HG19() throws IOException
	{
		System.out.println("testGetExons_HG19");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		Exon [] result = instance.getExons_HG19("TP53|ENSG00000141510", false);
		assertEquals(122, result.length);
		assertEquals("ENSE00001146308", result[0].mExonId);
		result = instance.getExons_HG19("TP53TG3", true);
		assertEquals(23, result.length);
		assertEquals("ENSE00002304100", result[0].mExonId);
	}
	
	@Test
	public void testGetExons_GRCh38() throws IOException
	{
		System.out.println("testGetExons_GRCh38");
		CallFromR instance = new CallFromR(GSAccessTestSuite.M_ZIP_ARCHIVE_PATH);
		Exon [] result = instance.getExons_GRCh38("TP53|ENSG00000141510", false);
		assertEquals(236, result.length);
		assertEquals("ENSE00001146308", result[0].mExonId);
		result = instance.getExons_GRCh38("TP53TG3", true);
		assertEquals(21, result.length);
		assertEquals("ENSE00001435970", result[0].mExonId);
	}
	
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
}

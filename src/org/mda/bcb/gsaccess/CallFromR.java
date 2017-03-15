/*
GSAccess Copyright 2014, 2015, 2016 University of Texas MD Anderson Cancer Center

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mda.bcb.gsaccess;

import java.io.IOException;
import org.apache.commons.codec.digest.DigestUtils;
import org.mda.bcb.gsaccess.neighbors.FN_Meth27;
import org.mda.bcb.gsaccess.neighbors.FN_Meth450;
import org.mda.bcb.gsaccess.neighbors.FN_RNASeq;
import org.mda.bcb.gsaccess.neighbors.FN_RNASeqV2;
import org.mda.bcb.gsaccess.neighbors.GS_HG18;
import org.mda.bcb.gsaccess.neighbors.GS_HG19;
import org.mda.bcb.gsaccess.retrieve.Dataframe;
import org.mda.bcb.gsaccess.retrieve.Exon;
import org.mda.bcb.gsaccess.retrieve.GeneSynonyms;
import org.mda.bcb.gsaccess.retrieve.GetDataClinical;
import org.mda.bcb.gsaccess.retrieve.GetDataMatrix;
import org.mda.bcb.gsaccess.retrieve.GetExon;
import org.mda.bcb.gsaccess.retrieve.GetImputedNAsMatrix;
import org.mda.bcb.gsaccess.retrieve.GetMapGeneEq;
import org.mda.bcb.gsaccess.retrieve.GetNamesGeneEq;
import org.mda.bcb.gsaccess.retrieve.GetTime;
import org.mda.bcb.gsaccess.retrieve.GetTranscript;
import org.mda.bcb.gsaccess.retrieve.MetadataGene;
import org.mda.bcb.gsaccess.retrieve.MetadataMir;
import org.mda.bcb.gsaccess.retrieve.MetadataPop;
import org.mda.bcb.gsaccess.retrieve.MetadataProbe;
import org.mda.bcb.gsaccess.retrieve.MetadataTcgaNames;
import org.mda.bcb.gsaccess.retrieve.OneToOneUcscHgnc;
import org.mda.bcb.gsaccess.retrieve.Transcript;

/**
 *
 * @author linux
 */
public class CallFromR
{
	protected String mZipFile = null;
	
	public CallFromR(String theZipFile)
	{
		mZipFile = theZipFile;
	}
	
	public String printVersion()
	{
		String version = GSAccess.printVersion();
		System.out.println(version);
		return version;
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	protected String getValue_internal(String theInternalPath) throws IOException
	{
		GetTime call = new GetTime(mZipFile);
		if (true==call.getTime(theInternalPath))
		{
			return call.mTime;
		}
		else
		{
			return null;
		}
	}
	
	public String getValue_Time() throws IOException
	{
		return getValue_internal("combined/time.txt");
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	protected String [] getNames_internal(String theInternalPath) throws IOException
	{
		GetNamesGeneEq call = new GetNamesGeneEq(mZipFile);
		if (true==call.getNamesGenes(theInternalPath))
		{
			return call.mGenes;
		}
		else
		{
			return null;
		}
	}
	
	public String [] getNames_Mutations() throws IOException
	{
		return getNames_internal("combined/mutations/gene_list.tsv");
	}

	public String [] getNames_RnaSeq2() throws IOException
	{
		return getNames_internal("combined/illuminahiseq_rnaseqv2_gene/gene_list.tsv");
	}

	public String [] getNames_RnaSeq() throws IOException
	{
		return getNames_internal("combined/illuminahiseq_rnaseq_uncGeneRPKM/gene_list.tsv");
	}

	public String [] getNames_SNP6() throws IOException
	{
		return getNames_internal("combined/genome_wide_snp_6_hg19nocnvWxy/gene_list.tsv");
	}

	public String [] getNames_Meth450() throws IOException
	{
		return getNames_internal("combined/humanmethylation450_level3/gene_list.tsv");
	}

	public String [] getNames_Meth27() throws IOException
	{
		return getNames_internal("combined/humanmethylation27_hg19Wxy/gene_list.tsv");
	}

	public String [] getNames_miRNASeq() throws IOException
	{
		return getNames_internal("combined/illuminahiseq_mirnaseq_isoform/gene_list.tsv");
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	public String [] getMapping_Meth450(String theGene) throws IOException
	{
		GetMapGeneEq call = new GetMapGeneEq(mZipFile);
		return call.getMapping_Meth450(theGene, "data/meth450map.tsv");
	}

	public String [] getMapping_Meth27(String theGene) throws IOException
	{
		GetMapGeneEq call = new GetMapGeneEq(mZipFile);
		return call.getMapping_Meth27(theGene, "data/meth27map.tsv");
	}
	
	public String [] getMappingGeneSymbols_Meth450() throws IOException
	{
		GetMapGeneEq call = new GetMapGeneEq(mZipFile);
		return call.getMappingGeneSymbols_Meth450("data/meth450map.tsv");
	}
	
	public String [] getMappingGeneSymbols_Meth27() throws IOException
	{
		GetMapGeneEq call = new GetMapGeneEq(mZipFile);
		return call.getMappingGeneSymbols_Meth27("data/meth27map.tsv");
	}
	
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	public GetDataClinical getDataClinical() throws Exception
	{
		GetDataClinical call = new GetDataClinical(mZipFile);
		if (true==call.getDataClinical("combined/combined_clinical.tsv"))
		{
			return call;
		}
		else
		{
			return null;
		}
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	protected GetDataMatrix getDataMatrix_internal(String [] theGenes, String theInternalPath, String theNamesInternalPath) throws IOException
	{
		GetDataMatrix call = new GetDataMatrix(mZipFile);
		if (true==call.getDataMatrix(theGenes, theInternalPath, theNamesInternalPath))
		{
			return call;
		} 
		else
		{
			return null;
		}
	}
	
	public GetDataMatrix getDataMatrix_Mutations(String [] theGenes) throws IOException
	{
		return getDataMatrix_internal(theGenes, "combined/mutations", "combined/mutations/gene_list.tsv");
	}

	public GetDataMatrix getDataMatrix_RnaSeq2(String [] theGenes) throws IOException
	{
		return getDataMatrix_internal(theGenes, "combined/illuminahiseq_rnaseqv2_gene", "combined/illuminahiseq_rnaseqv2_gene/gene_list.tsv");
	}

	public GetDataMatrix getDataMatrix_RnaSeq(String [] theGenes) throws IOException
	{
		return getDataMatrix_internal(theGenes, "combined/illuminahiseq_rnaseq_uncGeneRPKM", "combined/illuminahiseq_rnaseq_uncGeneRPKM/gene_list.tsv");
	}

	public GetDataMatrix getDataMatrix_SNP6(String [] theGenes) throws IOException
	{
		return getDataMatrix_internal(theGenes, "combined/genome_wide_snp_6_hg19nocnvWxy", "combined/genome_wide_snp_6_hg19nocnvWxy/gene_list.tsv");
	}

	public GetDataMatrix getDataMatrix_Meth450(String [] theGenes) throws IOException
	{
		return getDataMatrix_internal(theGenes, "combined/humanmethylation450_level3", "combined/humanmethylation450_level3/gene_list.tsv");
	}

	public GetDataMatrix getDataMatrix_Meth27(String [] theGenes) throws IOException
	{
		return getDataMatrix_internal(theGenes, "combined/humanmethylation27_hg19Wxy", "combined/humanmethylation27_hg19Wxy/gene_list.tsv");
	}

	public GetDataMatrix getDataMatrix_miRNASeq(String [] theGenes) throws IOException
	{
		return getDataMatrix_internal(theGenes, "combined/illuminahiseq_mirnaseq_isoform", "combined/illuminahiseq_mirnaseq_isoform/gene_list.tsv");
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	/*
	protected GetMatrixPlatform getDataMatrixPlatform_internal(String thePlatformInternalPath, String theNamesInternalPath) throws IOException
	{
		GetMatrixPlatform call = new GetMatrixPlatform(mZipFile);
		if (true==call.getDataMatrix(thePlatformInternalPath, theNamesInternalPath))
		{
			return call;
		} 
		else
		{
			return null;
		}
	}
	
	public GetMatrixPlatform getDataMatrix_MutationsPlatform() throws IOException
	{
		return getDataMatrixPlatform_internal("combined/mutations/platform.tsv", "combined/mutations/gene_list.tsv");
	}

	public GetMatrixPlatform getDataMatrix_RnaSeq2Platform() throws IOException
	{
		return getDataMatrixPlatform_internal("combined/illuminahiseq_rnaseqv2_gene/platform.tsv", "combined/illuminahiseq_rnaseqv2_gene/gene_list.tsv");
	}

	public GetMatrixPlatform getDataMatrix_RnaSeqPlatform() throws IOException
	{
		return getDataMatrixPlatform_internal("combined/illuminahiseq_rnaseq_uncGeneRPKM/platform.tsv", "combined/illuminahiseq_rnaseq_uncGeneRPKM/gene_list.tsv");
	}

	public GetMatrixPlatform getDataMatrix_SNP6Platform() throws IOException
	{
		return getDataMatrixPlatform_internal("combined/genome_wide_snp_6_hg19nocnvWxy/platform.tsv", "combined/genome_wide_snp_6_hg19nocnvWxy/gene_list.tsv");
	}

	public GetMatrixPlatform getDataMatrix_Meth450Platform() throws IOException
	{
		return getDataMatrixPlatform_internal("combined/humanmethylation450_level3/platform.tsv", "combined/humanmethylation450_level3/gene_list.tsv");
	}

	public GetMatrixPlatform getDataMatrix_Meth27Platform() throws IOException
	{
		return getDataMatrixPlatform_internal("combined/humanmethylation27_hg19Wxy/platform.tsv", "combined/humanmethylation27_hg19Wxy/gene_list.tsv");
	}

	public GetMatrixPlatform getDataMatrix_miRNASeqPlatform() throws IOException
	{
		return getDataMatrixPlatform_internal("combined/illuminahiseq_mirnaseq_isoform/platform.tsv", "combined/illuminahiseq_mirnaseq_isoform/gene_list.tsv");
	}
	*/
	
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	protected GetImputedNAsMatrix getImputedNAsMatrix_internal(String [] theGenes, String theInternalPath, String theNamesInternalPath) throws IOException
	{
		GetImputedNAsMatrix call = new GetImputedNAsMatrix(mZipFile);
		if (true==call.getImputedNAsMatrix(theGenes, theInternalPath, theNamesInternalPath))
		{
			return call;
		} 
		else
		{
			return null;
		}
	}
	
	public GetImputedNAsMatrix getImputedNAsMatrix_RnaSeq2(String [] theGenes) throws IOException
	{
		return getImputedNAsMatrix_internal(theGenes, "combined/illuminahiseq_rnaseqv2_gene", "combined/illuminahiseq_rnaseqv2_gene/gene_list.tsv");
	}

	public GetImputedNAsMatrix getImputedNAsMatrix_RnaSeq(String [] theGenes) throws IOException
	{
		return getImputedNAsMatrix_internal(theGenes, "combined/illuminahiseq_rnaseq_uncGeneRPKM", "combined/illuminahiseq_rnaseq_uncGeneRPKM/gene_list.tsv");
	}

	public GetImputedNAsMatrix getImputedNAsMatrix_SNP6(String [] theGenes) throws IOException
	{
		return getImputedNAsMatrix_internal(theGenes, "combined/genome_wide_snp_6_hg19nocnvWxy", "combined/genome_wide_snp_6_hg19nocnvWxy/gene_list.tsv");
	}

	public GetImputedNAsMatrix getImputedNAsMatrix_Meth450(String [] theGenes) throws IOException
	{
		return getImputedNAsMatrix_internal(theGenes, "combined/humanmethylation450_level3", "combined/humanmethylation450_level3/gene_list.tsv");
	}

	public GetImputedNAsMatrix getImputedNAsMatrix_Meth27(String [] theGenes) throws IOException
	{
		return getImputedNAsMatrix_internal(theGenes, "combined/humanmethylation27_hg19Wxy", "combined/humanmethylation27_hg19Wxy/gene_list.tsv");
	}

	public GetImputedNAsMatrix getImputedNAsMatrix_miRNASeq(String [] theGenes) throws IOException
	{
		return getImputedNAsMatrix_internal(theGenes, "combined/illuminahiseq_mirnaseq_isoform", "combined/illuminahiseq_mirnaseq_isoform/gene_list.tsv");
	}
	
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	public MetadataPop getMetadataPop_BarcodeDisease() throws IOException
	{
		MetadataPop call = new MetadataPop(mZipFile);
		if (true==call.getMetadataPop_BarcodeDisease("data/barcode_disease.tsv"))
		{
			return call;
		} 
		else
		{
			return null;
		}
	}

	public MetadataPop getMetadataPop_BarcodeSamplecode() throws IOException
	{
		MetadataPop call = new MetadataPop(mZipFile);
		if (true==call.getMetadataPop_BarcodeSamplecode("data/barcode_samplecode.tsv"))
		{
			return call;
		} 
		else
		{
			return null;
		}
	}

	public MetadataPop getMetadataPop_PatientDisease() throws IOException
	{
		MetadataPop call = new MetadataPop(mZipFile);
		if (true==call.getMetadataPop_PatientDisease("data/patient_disease.tsv"))
		{
			return call;
		} 
		else
		{
			return null;
		}
	}
	
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	public String getMetadataTcga_DatasetName(String theId) throws IOException
	{
		return new MetadataTcgaNames(mZipFile).getMetadataTcga_DatasetName(theId, "data/dataset_names.tsv");
	}

	public String getMetadataTcga_DiseaseName(String theId) throws IOException
	{
		return new MetadataTcgaNames(mZipFile).getMetadataTcga_DiseaseName(theId, "data/disease_names.tsv");
	}

	public String getMetadataTcga_SampleTypeName(String theId) throws IOException
	{
		return new MetadataTcgaNames(mZipFile).getMetadataTcga_SampleTypeName(theId, "data/sampletype_names.tsv");
	}

	////////////////////////////////////////////////////////////////////////////

	public String [] getMetadataTcga_Ids_DatasetName() throws IOException
	{
		return new MetadataTcgaNames(mZipFile).getMetadataTcga_Ids_DatasetName("data/dataset_names.tsv");
	}

	public String [] getMetadataTcga_Names_DatasetName() throws IOException
	{
		return new MetadataTcgaNames(mZipFile).getMetadataTcga_Names_DatasetName("data/dataset_names.tsv");
	}

	public String [] getMetadataTcga_Ids_DiseaseName() throws IOException
	{
		return new MetadataTcgaNames(mZipFile).getMetadataTcga_Ids_DiseaseName("data/disease_names.tsv");
	}

	public String [] getMetadataTcga_Names_DiseaseName() throws IOException
	{
		return new MetadataTcgaNames(mZipFile).getMetadataTcga_Names_DiseaseName("data/disease_names.tsv");
	}

	public String [] getMetadataTcga_Ids_SampleTypeName() throws IOException
	{
		return new MetadataTcgaNames(mZipFile).getMetadataTcga_Ids_SampleTypeName("data/sampletype_names.tsv");
	}

	public String [] getMetadataTcga_Names_SampleTypeName() throws IOException
	{
		return new MetadataTcgaNames(mZipFile).getMetadataTcga_Names_SampleTypeName("data/sampletype_names.tsv");
	}
	
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	public MetadataGene [] getMetadataList_Mutations(String theStandardizedDataId) throws IOException
	{
		return new MetadataGene(mZipFile).getMetadataList_HG19(theStandardizedDataId, "data/HG19map.tsv");
	}

	public MetadataGene [] getMetadataList_RNASeq(String theStandardizedDataId) throws IOException
	{
		return new MetadataGene(mZipFile).getMetadataList_RNASeq(theStandardizedDataId, "data/rnaseqMap.tsv");
	}

	public MetadataGene [] getMetadataList_RNASeqV2(String theStandardizedDataId) throws IOException
	{
		return new MetadataGene(mZipFile).getMetadataList_RNASeqV2(theStandardizedDataId, "data/rnaseqMap.tsv");
	}

	public MetadataGene [] getMetadataList_HG18(String theStandardizedDataId) throws IOException
	{
		return new MetadataGene(mZipFile).getMetadataList_HG18(theStandardizedDataId, "data/HG18map.tsv");
	}

	public MetadataGene [] getMetadataList_HG19(String theStandardizedDataId) throws IOException
	{
		return new MetadataGene(mZipFile).getMetadataList_HG19(theStandardizedDataId, "data/HG19map.tsv");
	}
	
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	public MetadataProbe getMetadata_Meth27(String theProbe) throws IOException
	{
		MetadataProbe call = new MetadataProbe(mZipFile);
		if (true==call.getMetadata_Meth27(theProbe, "data/meth27map.tsv"))
		{
			return call;
		} 
		else
		{
			return null;
		}
	}

	public MetadataProbe getMetadata_Meth450(String theProbe) throws IOException
	{
		MetadataProbe call = new MetadataProbe(mZipFile);
		if (true==call.getMetadata_Meth450(theProbe, "data/meth450map.tsv"))
		{
			return call;
		} 
		else
		{
			return null;
		}
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	public String [] getMirList() throws IOException
	{
		return new MetadataMir(mZipFile).getMirList("data/mirHG19map.tsv");
	}

	public String [] getMimatList() throws IOException
	{
		return new MetadataMir(mZipFile).getMimatList("data/mirHG19map.tsv");
	}
	
	public MetadataMir [] getMetadata_miRNA_mir(String theMirId) throws IOException
	{
		return new MetadataMir(mZipFile).getMetadata_miRNA_mir(theMirId, "data/mirHG19map.tsv");
	}

	public MetadataMir [] getMetadata_miRNA_mimat(String theMimatId) throws IOException
	{
		return new MetadataMir(mZipFile).getMetadata_miRNA_mimat(theMimatId, "data/mirHG19map.tsv");
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	public String [] getOneToOne_UCSC_List() throws IOException
	{
		return new OneToOneUcscHgnc(mZipFile).getOneToOne_UCSC_List("iddata/downloads/oneToOneUcscHgnc.tsv");
	}

	public String [] getOneToOne_GeneSymbol_List() throws IOException
	{
		return new OneToOneUcscHgnc(mZipFile).getOneToOne_GeneSymbol_List("iddata/downloads/oneToOneUcscHgnc.tsv");
	}

	public String getOneToOne_GeneSymbol_UCID(String theId) throws IOException
	{
		return new OneToOneUcscHgnc(mZipFile).getOneToOne_GeneSymbol_UCID(theId, "iddata/downloads/oneToOneUcscHgnc.tsv");
	}
	
	public String getOneToOne_UCID_GeneSymbol(String theId) throws IOException
	{
		return new OneToOneUcscHgnc(mZipFile).getOneToOne_UCID_GeneSymbol(theId, "iddata/downloads/oneToOneUcscHgnc.tsv");
	}
	
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	
	public String [] getList_GeneSymbol_Synonym(String theId) throws IOException
	{
		return new GeneSynonyms(mZipFile).getList_GeneSymbol_Synonym(theId, "iddata/downloads/geneSynonyms.tsv");
	}
	
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	public MetadataGene [] findNeighbors_RnaSeq(long theStart, long theStop, String theChromosome, String theStrand) throws IOException
	{
		try
		{
			return new FN_RNASeq(mZipFile).findNeighbors(theStart, theStop, theChromosome, theStrand);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
			throw exp;
		}
	}

	public MetadataGene [] findNeighbors_RnaSeq2(long theStart, long theStop, String theChromosome, String theStrand) throws IOException
	{
		try
		{
			return new FN_RNASeqV2(mZipFile).findNeighbors(theStart, theStop, theChromosome, theStrand);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
			throw exp;
		}
	}

	public MetadataProbe [] findNeighbors_Meth450(long theStart, long theStop, String theChromosome) throws IOException
	{
		try
		{
			return new FN_Meth450(mZipFile).findNeighbors(theStart, theStop, theChromosome);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
			throw exp;
		}
	}

	public MetadataProbe [] findNeighbors_Meth27(long theStart, long theStop, String theChromosome) throws IOException
	{
		try
		{
			return new FN_Meth27(mZipFile).findNeighbors(theStart, theStop, theChromosome);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
			throw exp;
		}
	}

	public MetadataGene [] findNeighbors_HG18(long theStart, long theStop, String theChromosome, String theStrand) throws IOException
	{
		try
		{
			return new GS_HG18(mZipFile).findNeighbors(theStart, theStop, theChromosome, theStrand);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
			throw exp;
		}
	}

	public MetadataGene [] findNeighbors_HG19(long theStart, long theStop, String theChromosome, String theStrand) throws IOException
	{
		try
		{
			return new GS_HG19(mZipFile).findNeighbors(theStart, theStop, theChromosome, theStrand);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
			throw exp;
		}
	}
	
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	public Dataframe getMutationDetails_Dataframe(String theGene) throws IOException
	{
		Dataframe df = new Dataframe(mZipFile);
		String md5prefix = DigestUtils.md5Hex(theGene).substring(0, 2);
		boolean found = df.processFile("combined/mutations/mutation_details/" + md5prefix + "/mutation_details_" + theGene + ".tsv");
		if (false==found)
		{
			df = null;
		}
		return df;
	}
	
	public String [] getMutationDetails_GeneList() throws IOException
	{
		return getNames_internal("combined/mutations/mutation_details/gene_list.tsv");
	}
	
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	
	public Transcript [] getTranscripts_HG18(String theGene, boolean theStartsWithFlag) throws IOException
	{
		GetTranscript call = new GetTranscript(mZipFile);
		call.getTranscriptForGene("data/HG18transcripts.tsv", theGene, theStartsWithFlag);
		if (call.mTranscripts.size()>0)
		{
			return call.mTranscripts.toArray(new Transcript[0]);
		} 
		else
		{
			return null;
		}
	}
	
	public Transcript [] getTranscripts_HG19(String theGene, boolean theStartsWithFlag) throws IOException
	{
		GetTranscript call = new GetTranscript(mZipFile);
		call.getTranscriptForGene("data/HG19transcripts.tsv", theGene, theStartsWithFlag);
		if (call.mTranscripts.size()>0)
		{
			return call.mTranscripts.toArray(new Transcript[0]);
		} 
		else
		{
			return null;
		}
	}
	
	public Transcript [] getTranscripts_GRCh38(String theGene, boolean theStartsWithFlag) throws IOException
	{
		GetTranscript call = new GetTranscript(mZipFile);
		call.getTranscriptForGene("data/GRCh38transcripts.tsv", theGene, theStartsWithFlag);
		if (call.mTranscripts.size()>0)
		{
			return call.mTranscripts.toArray(new Transcript[0]);
		} 
		else
		{
			return null;
		}
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

	public Exon [] getExons_HG18(String theGene, boolean theStartsWithFlag) throws IOException
	{
		GetExon call = new GetExon(mZipFile);
		call.getExonForGene("data/HG18exons.tsv", theGene, theStartsWithFlag);
		if (call.mExons.size()>0)
		{
			return call.mExons.toArray(new Exon[0]);
		} 
		else
		{
			return null;
		}
	}
	
	public Exon [] getExons_HG19(String theGene, boolean theStartsWithFlag) throws IOException
	{
		GetExon call = new GetExon(mZipFile);
		call.getExonForGene("data/HG19exons.tsv", theGene, theStartsWithFlag);
		if (call.mExons.size()>0)
		{
			return call.mExons.toArray(new Exon[0]);
		} 
		else
		{
			return null;
		}
	}
	
	public Exon [] getExons_GRCh38(String theGene, boolean theStartsWithFlag) throws IOException
	{
		GetExon call = new GetExon(mZipFile);
		call.getExonForGene("data/GRCh38exons.tsv", theGene, theStartsWithFlag);
		if (call.mExons.size()>0)
		{
			return call.mExons.toArray(new Exon[0]);
		} 
		else
		{
			return null;
		}
	}

	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////

}

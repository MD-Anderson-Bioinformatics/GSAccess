/*
GSAccess Copyright 2014, 2015, 2016 University of Texas MD Anderson Cancer Center

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mda.bcb.gsaccess.retrieve;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TreeMap;
import org.mda.bcb.gsaccess.GSAccess;
import org.mda.bcb.gsaccess.ReadZipFile;

/**
 *
 * @author tdcasasent
 */
public class OneToOneUcscHgnc extends ReadZipFile
{

	static protected String M_PATH = null;
	static protected TreeMap<String, String> M_UCID_TO_GENESYMBOL = null;
	static protected TreeMap<String, String> M_GENESYMBOL_TO_UCID = null;

	public OneToOneUcscHgnc(String theZipFile)
	{
		super(theZipFile);
		if (false==theZipFile.equals(M_PATH))
		{
			M_PATH = theZipFile;
			M_UCID_TO_GENESYMBOL = null;
			M_GENESYMBOL_TO_UCID = null;
		}
	}

	public String [] getOneToOne_UCSC_List(String theInternalPath) throws IOException
	{
		try
		{
			GSAccess.printVersion();
			if (null==M_UCID_TO_GENESYMBOL)
			{
				loadOneToOne(theInternalPath);
			}
			return M_UCID_TO_GENESYMBOL.keySet().toArray(new String[0]);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
			M_UCID_TO_GENESYMBOL = null;
			M_GENESYMBOL_TO_UCID = null;
			throw exp;
		}
	}

	public String [] getOneToOne_GeneSymbol_List(String theInternalPath) throws IOException
	{
		try
		{
			GSAccess.printVersion();
			if (null==M_GENESYMBOL_TO_UCID)
			{
				loadOneToOne(theInternalPath);
			}
			return M_GENESYMBOL_TO_UCID.keySet().toArray(new String[0]);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
			M_UCID_TO_GENESYMBOL = null;
			M_GENESYMBOL_TO_UCID = null;
			throw exp;
		}
	}

	public String getOneToOne_GeneSymbol_UCID(String theId, String theInternalPath) throws IOException
	{
		try
		{
			GSAccess.printVersion();
			if (null==M_UCID_TO_GENESYMBOL)
			{
				loadOneToOne(theInternalPath);
			}
			return M_UCID_TO_GENESYMBOL.get(theId);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
			M_UCID_TO_GENESYMBOL = null;
			M_GENESYMBOL_TO_UCID = null;
			throw exp;
		}
	}
	
	public String getOneToOne_UCID_GeneSymbol(String theId, String theInternalPath) throws IOException
	{
		try
		{
			GSAccess.printVersion();
			if (null==M_GENESYMBOL_TO_UCID)
			{
				loadOneToOne(theInternalPath);
			}
			return M_GENESYMBOL_TO_UCID.get(theId);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
			M_UCID_TO_GENESYMBOL = null;
			M_GENESYMBOL_TO_UCID = null;
			throw exp;
		}
	}
	
	protected void loadOneToOne(String theInternalPath) throws FileNotFoundException, IOException
	{
		M_UCID_TO_GENESYMBOL = new TreeMap<>();
		M_GENESYMBOL_TO_UCID = new TreeMap<>();
		processFile(theInternalPath);
	}

	@Override
	protected boolean processLine(String theLine)
	{
		String [] tabSplit = theLine.split("\t", -1);
		String ucid = tabSplit[0];
		String symbol = tabSplit[1];
		M_UCID_TO_GENESYMBOL.put(ucid, symbol);
		M_GENESYMBOL_TO_UCID.put(symbol, ucid);
		return true;
	}
}

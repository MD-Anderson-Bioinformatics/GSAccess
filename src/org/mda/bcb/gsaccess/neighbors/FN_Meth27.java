/*
TcgaGSData Copyright 2014, 2015, 2016 University of Texas MD Anderson Cancer Center

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mda.bcb.gsaccess.neighbors;

import java.util.ArrayList;
import org.mda.bcb.gsaccess.retrieve.MetadataProbe;

/**
 *
 * @author tdcasasent
 */
public class FN_Meth27 extends FindMethNeighbors_Mixin
{
	static protected String M_PATH = null;
	static protected ArrayList<MetadataProbe> M_PROBES = null;

	public FN_Meth27(String theZipFile)
	{
		super("data/meth27map.tsv", theZipFile);
		if (false==theZipFile.equals(M_PATH))
		{
			M_PATH = theZipFile;
			M_PROBES = null;
		}
	}

	@Override
	protected MetadataProbe getReadProbe(String theHeader, String[] theSplitted)
	{
		MetadataProbe mdp = null;
		try
		{
			MetadataProbe result = new MetadataProbe(mPath);
			result.populateHeaderLines27(theHeader);
			mdp = result.populateGetMetadataMeth27(theSplitted);
		}
		catch(Exception exp)
		{
			exp.printStackTrace(System.err);
			M_PROBES = null;
			throw exp;
		}
		return mdp;
	}

	@Override
	protected ArrayList<MetadataProbe> getProbes()
	{
		return M_PROBES;
	}

	@Override
	protected void addProbe(MetadataProbe theProbe)
	{
		if (null==M_PROBES)
		{
			M_PROBES = new ArrayList<>();
		}
		M_PROBES.add(theProbe);
	}
}

/*
GSAccess Copyright 2014, 2015, 2016 University of Texas MD Anderson Cancer Center

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mda.bcb.gsaccess.neighbors;

import java.io.IOException;
import java.util.ArrayList;
import org.mda.bcb.gsaccess.GSAccess;
import org.mda.bcb.gsaccess.ReadZipFile;
import org.mda.bcb.gsaccess.retrieve.MetadataProbe;

/**
 *
 * @author tdcasasent
 */
public abstract class FindMethNeighbors_Mixin extends ReadZipFile
{
	protected String mMapFile = null;
	protected String mPath = null;
	protected MetadataProbe mReadProbe = null;
	protected String mHeaderLine = null;

	public FindMethNeighbors_Mixin(String theMapFile, String theZipFile)
	{
		super(theZipFile);
		mMapFile = theMapFile;
		mPath = theZipFile;
	}

	abstract protected MetadataProbe getReadProbe(String theHeaderLine, String[] theSplitted);
	abstract protected void addProbe(MetadataProbe theProbe);
	abstract protected ArrayList<MetadataProbe> getProbes();

	public MetadataProbe [] findNeighbors(long theMin, long theMax, String theChromosome) throws IOException
	{
		GSAccess.printVersion();
		long start = System.currentTimeMillis();
		ArrayList<MetadataProbe> probeList = getProbes();
		if (null==probeList)
		{
			mHeaderLine = null;
			processFile(mMapFile);
			probeList = getProbes();
		}
		ArrayList<MetadataProbe> results = new ArrayList<>();
		for(MetadataProbe probe : probeList)
		{
			if ((probe.mProbeLocation<=theMax)&&(probe.mProbeLocation>=theMin))
			{
				if(theChromosome.equalsIgnoreCase(probe.mChromosome))
				{
					results.add(probe);
				}
			}
		}
		long finish = System.currentTimeMillis();
		GSAccess.printWithFlag("Find " + results.size() + " Neighbors between " + theMin + " and " + theMax + " for " + mMapFile + " in " + ((finish-start)/1000.0) + " seconds");
		MetadataProbe [] retArray = results.toArray(new MetadataProbe[0]);
		GSAccess.printWithFlag("made array");
		return retArray;
	}

	@Override
	protected boolean processLine(String theLine)
	{
		if (null==mHeaderLine)
		{
			mHeaderLine = theLine;
		}
		else
		{
			String [] splitted = theLine.split("\t", -1);
			addProbe(getReadProbe(mHeaderLine, splitted));
		}
		return true;
	}

}

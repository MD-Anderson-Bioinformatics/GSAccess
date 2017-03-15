/*
TcgaGSData Copyright 2014, 2015, 2016 University of Texas MD Anderson Cancer Center

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 2 of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.mda.bcb.gsaccess.neighbors;

/**
 *
 * @author linux
 */
public class GS_HG18 extends FindGeneScoreNeighbors_Mixin
{
	public GS_HG18(String theZipFile)
	{
		super("data/HG18map.tsv", theZipFile);
	}
}

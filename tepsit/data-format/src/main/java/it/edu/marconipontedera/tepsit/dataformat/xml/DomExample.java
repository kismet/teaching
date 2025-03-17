/*
* 
* Teaching material for High School of ITI 'G.Marconi'
* locate in Pontedera, Pisa, Italy 
*
* Material for Computer Science educational path 
*
* Copyright (C) 2025 Stefano Lenzi
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package it.edu.marconipontedera.tepsit.dataformat.xml;

import java.io.ByteArrayInputStream;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class DomExample {
	public static void main(String[] args) {
		try {
	         //Input the XML file
		     File inputXmlFile = new File("corsi.xml");
		           
		     //creating DocumentBuilder
		     DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		     DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
		     Document xmldoc = docBuilder.parse(inputXmlFile);

			// Retrieving the Root Element Name
			Element root = xmldoc.getDocumentElement();
			System.out.println("Root element name is '" + root.getTagName()+"'");
			
			NodeList nodeList = root.getElementsByTagName("Corso");
			
			for (int i=0; i<nodeList.getLength(); i++) {
				System.out.println(nodeList.item(i).getAttributes().getNamedItem("type"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

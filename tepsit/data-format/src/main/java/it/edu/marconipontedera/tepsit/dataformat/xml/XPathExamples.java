/*
* 
* Teaching material for High School of ITI 'G.Marconi'
* locate in Pontedera, Pisa, Italy 
*
* Material for Computer Science educational path 
*
* Copyright (C) 2024 Stefano Lenzi
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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author Stefano Lenzi &lt; stefano@lenzi.pro &gt;
 * 
 */
public class XPathExamples {

	   public static void main(String[] args) {     
	      try {
	          //Creating DocumentBuilder
	          DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	          DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	          
	          //Reading the XML
	          File inputFile = new File("corsi.xml");
	          
	          //Creating Document from file or Stream
	          Document doc = dBuilder.parse(inputFile);	    	  

	          //Building XPath
	          XPath xPath =  XPathFactory.newInstance().newXPath();

	          //Preparing and Evaluating XPath expression
	          String expression = "/corsi/Corso/Titolo";	        
	          XPathExpression query = xPath.compile(expression);
	          
	          NodeList nodeList = (NodeList) query.evaluate(
	             doc, XPathConstants.NODESET);

	          //Iterating over NodeList
	          for (int i = 0; i < nodeList.getLength(); i++) {
	             Node node = nodeList.item(i);
	             //Querying the Elements	             
	             System.out.println(node.getNodeName()+":"+node.getTextContent());	             
	          }	          	      

	          expression = "/corsi/Corso";	        
	          query = xPath.compile(expression);
	          
	          nodeList = (NodeList) query.evaluate(
	             doc, XPathConstants.NODESET);

	          //Iterating over NodeList
	          for (int i = 0; i < nodeList.getLength(); i++) {
	             Node node = nodeList.item(i);
	             //Querying the Elements	             
	             System.out.println(node.getAttributes().getNamedItem("id"));	             
	          }	          	      
	          
	          System.out.println();
	          System.out.println();
	          expression = "/corsi/Corso";	        
	          query = xPath.compile(expression);
	          
	          nodeList = (NodeList) query.evaluate(doc, XPathConstants.NODESET);

	          System.out.println("Tipo\tTitolo\tOre");
	          //Iterating over NodeList
	          for (int i = 0; i < nodeList.getLength(); i++) {
	             Node node = nodeList.item(i);
	             //Querying the Elements	             
	             System.out.print(node.getAttributes().getNamedItem("type").getNodeValue());	             
	             System.out.print("\t");
//	             Node titolo = (Node) xPath.compile("Titolo").evaluate(node,XPathConstants.NODE);
//	             System.out.println(titolo.getTextContent());
	             System.out.print(findChild(node, "Titolo").getTextContent());	 
	             Node ore = (Node) xPath.compile("*/Ore").evaluate(node,XPathConstants.NODE);
	             System.out.print("\t");
	             System.out.println(ore.getTextContent());
	          }	          	      
	          
	          
	      } catch(Exception e) {
	    	  e.printStackTrace();
	      }
	   }
	   
	   public static Node findChild(Node n, String name) {
		   NodeList list = n.getChildNodes();
		   if(list == null) return null;
		   for (int i = 0; i < list.getLength(); i++) {
			   if(list.item(i).getNodeName().equals(name))
				   return list.item(i);
		   }
		   return null;
	   }
	   
}

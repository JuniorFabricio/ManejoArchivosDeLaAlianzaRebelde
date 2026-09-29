/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.sistemaarchivosdelaalianzarebelde;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.util.JAXBSource;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author jfabr
 */
public class SistemaArchivosDeLaAlianzaRebelde {

    public static void main(String[] args) {
        File archivo = new File("imperio.xml");
        System.out.println("=====Lectura con DOM=====");
        
        leerArchivoXMl(archivo);
        
        System.out.println("=====Lectura con SAX=====");
        leerArchivoSax(archivo);
        System.out.println("====Creacion de archivo rebelde.xml====");
        generalArchivoBinding();
            
    }

    public static void leerArchivoXMl(File archivo) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dc = builder.parse(archivo);
            dc.getDocumentElement().normalize();
            Element raiz=dc.getDocumentElement();
            System.out.println("Elemento Raiz "+raiz.getNodeName());
             NodeList listaNodos= dc.getElementsByTagName("nave");
             for (int i = 0; i < listaNodos.getLength(); i++) 
             {
                 Node nodo=listaNodos.item(i);
                 
                 if(nodo.getNodeType()==Node.ELEMENT_NODE)
                 {
                     Element nave=(Element) nodo;
                     String id=nave.getAttribute("id");
                     String nombre=nave.getElementsByTagName("nombre").item(0).getTextContent();
                     String piloto=nave.getElementsByTagName("piloto").item(0).getTextContent();
                     System.out.println("ID Nave :  "+id);
                     System.out.println("Nombre : "+nombre);
                     System.out.println("Piloto : "+piloto) ;
                     System.out.println("---------------------------------");
                      generalArchivoBinding();
                 }
             }
            //TransformerFactory transFactory = TransformerFactory.newDefaultInstance();
            //Transformer trans = transFactory.newTransformer();
            //trans.setOutputProperty(OutputKeys.INDENT, "yes");
            //trans.transform(new DOMSource(dc), new StreamResult(System.out));
            
            

        } catch (Exception ex) {
            System.err.println("Erro al leer el archivo : "+ex.getMessage());
        }
    }
    public static void generalArchivoBinding()
    {
        File archivoXml=new File("rebelde.xml");
        
        System.out.println("==1. Generando un archivo xml===");
        Personaje p1=new Personaje("1", "Luke Skywalker", "Alianza Rebelde", "Comandante");
        try {
            JAXBContext context= JAXBContext.newInstance(Personaje.class);
            Marshaller mars=context.createMarshaller();
            mars.setProperty(mars.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
            mars.marshal(p1, archivoXml);
            System.out.println("=== Archivo generado correctamente===");
            mars.marshal(p1, System.out);
        } catch (JAXBException ex) {
            System.err.println("Error al crear el archivo "+ex.getMessage());
        }
    }
    
    public static void leerArchivoSax(File archivo)
    {
        try {
            SAXParserFactory factory=SAXParserFactory.newDefaultInstance();
            SAXParser saxParser=factory.newSAXParser();
            
            DefaultHandler manejador=new DefaultHandler()
            {
                private boolean esNombre=false;
                private String claseActual="";
                
                
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException 
                {
                    if(qName.equalsIgnoreCase("nave"))
                    {
                        claseActual=attributes.getValue("clase");
                    }else if(qName.equalsIgnoreCase("nombre"))
                    {
                        esNombre=true;
                    }
                    
                    
                }
    
                @Override
                public void characters(char[] ch, int start, int length) throws SAXException 
                {
                 if(esNombre)
                 {
                     String nombreNave=new String(ch,start,length);
                     System.out.println("Clase: "+claseActual+"|Nombre "+nombreNave);
                     esNombre=false;
                 }
                
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException 
                {
     
                    // Control de fin de bloques si fuera necesario
           
                }

                
            };
            
            saxParser.parse(archivo, manejador);
            
            
        } catch (ParserConfigurationException ex) {
            System.err.println("");
        } catch (SAXException ex) {
            System.err.println("");
        } catch (IOException ex) {
            System.out.println("");
        }
    }
}

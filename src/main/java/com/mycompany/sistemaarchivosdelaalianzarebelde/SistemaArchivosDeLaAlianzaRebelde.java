/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistemaarchivosdelaalianzarebelde;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.IIOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;

/**
 *
 * @author jfabr
 */
public class SistemaArchivosDeLaAlianzaRebelde {

    public static void main(String[] args) {
       
        
        
        
    }
    
    public static void leerArchivoXMl()
    {
        File archivo=new File("imperio.xml");
        try {
            DocumentBuilderFactory factory= DocumentBuilderFactory.newDefaultInstance();
            DocumentBuilder builder= factory.newDocumentBuilder(); 
            Document dc= builder.parse(archivo);
            dc.getDocumentElement().normalize();
            TransformerFactory transFactory=TransformerFactory.newDefaultInstance();
            Transformer trans= transFactory.newTransformer();
            
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.transform(new DOMSource(dc), new StreamResult(System.out));
            
                    
            
            
            
            
            } catch (Exception ex) {
            Logger.getLogger(SistemaArchivosDeLaAlianzaRebelde.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

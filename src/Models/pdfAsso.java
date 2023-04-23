/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import ServiceAssociation.AssociationService;
import com.itextpdf.text.BadElementException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.time.LocalDateTime;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfDocument.Destination;
/**
 *
 * @author Fares CHAKROUN
 */
public class pdfAsso {
   
      public void GeneratePdf(String filename, Association p, int id) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException {

        Document document = new Document() {
        };
        PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
        document.open();

        AssociationService us = new AssociationService();
        document.add(new Paragraph("            Date  :"+LocalDateTime.now()));
        document.add(new Paragraph("            le voyage :"+p.getNom()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------"));

        document.add(new Paragraph("Numero de l'association:" + p.getNumero()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("mail de l'association :" + p.getMail()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("CodePostal de l'association:" + p.getCodePostal()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph(" Ville de l'association:" + p.getVille()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph(" Categorie de l'association :" + p.getCategorie()));
        document.add(new Paragraph("                      "));
        
        //document.add(new Paragraph("image :" + p.getImage()));
        document.add(new Paragraph("                      "));
       

        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        document.add(new Paragraph("                              Generaux                     "));
        document.close();
        Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename + ".pdf");
    }

  

   
}

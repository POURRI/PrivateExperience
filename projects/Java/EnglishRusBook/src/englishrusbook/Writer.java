/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishrusbook;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author POURRI
 */
public class Writer extends Central{
    
    public void create(String file, HashMap<String, String> wordTranslateMap) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            write(document, wordTranslateMap);
        } catch (FileNotFoundException | DocumentException e) {
            System.out.println("ERROR: " + e); 
        }
    }
        
    public void write(Document document, HashMap<String, String> wordTranslateMap) {
        document.open();
        String delimiter = " : ";
        try {
            for (Entry<String, String> entry: wordTranslateMap.entrySet()) {
                document.add(new Paragraph(entry.getKey() + delimiter + entry.getValue()));
            }
        } catch (DocumentException e) {
            System.out.println("ERROR in writing: " + e);
        } finally {
            document.close();
        }    
    }
}
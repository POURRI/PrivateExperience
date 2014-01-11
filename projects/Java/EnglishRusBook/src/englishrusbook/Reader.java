/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishrusbook;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author POURRI
 */
public class Reader extends Central{
    
    private PdfReader reader;
    
    public boolean isCanParsing(String file) {
        debugOut("function:isCanParsingPDF");
        boolean isCanParsingPDF = true;
        
        try {
            reader = new PdfReader(file);
        } catch (IOException e) {
            printOut("ERROR : Maybe file not exist");
            isCanParsingPDF = false;
        }
        
        return isCanParsingPDF;
    }
    
    public PdfReader getReader() {
        return reader;
    }
    
    public HashMap<Integer, String> getPageMap() {
        debugOut("function:getPDFPages");  
        HashMap<Integer, String> map = new HashMap<>();
        
        for (int i = 1, pageCount = reader.getNumberOfPages(); i <= pageCount; i++) {
            try {
                map.put(i, PdfTextExtractor.getTextFromPage(reader, i));
                debugOut("ShowPageOfNumber: " + String.valueOf(i));
            } catch (IOException e) {
                System.out.println("ERROR in reading: " + e);
            }
        }
        
        return map;
    }
    
    public void showInfo() {
        printOut("INFO: FileLength: " + String.valueOf(reader.getFileLength()));
        printOut("INFO: NumberOfPages: " + String.valueOf(reader.getNumberOfPages()));
    }
}

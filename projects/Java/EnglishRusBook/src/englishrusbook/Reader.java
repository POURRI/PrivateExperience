/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishrusbook;

import com.itextpdf.text.pdf.PdfReader;
import java.io.IOException;

/**
 *
 * @author POURRI
 */
public class Reader extends Central{
    
    private PdfReader reader;
    
    public boolean isCanParsingPDF(String file) {
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
}

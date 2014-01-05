/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishrusbook;

import com.itextpdf.text.pdf.PdfReader;
/**
 *
 * @author POURRI
 */
public class EnglishRusBook {

    /**
     * @param args the command line arguments
     */
    
    public static final String PDF = "test/data/mini.pdf";
    
    public static void main(String[] args) {
        parsePDF();
    }
    
    private static void parsePDF() {
        try {
            PdfReader reader = new PdfReader(PDF);
            
            debugOut("parsePDF");
        } catch (Exception e) {
            debugOut("We have error when create PdfReader");
            debugOut("if you see this - you must do something ok?");
        }
    }
    
    private static void debugOut(String debug) {
        System.out.println(debug);
    }
    
}

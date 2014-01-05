package englishrusbook;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

public class EnglishRusBook {

    private static String PDF;
    
    private static boolean isDebug;
    
    public static void main(String[] args) {
        initSettings();
        parsePDF();
    }
    
    private static void initSettings() {
        PDF = "test/data/mini.pdf";
        isDebug = true;
    }
    
    private static void parsePDF() {
        debugOut("function:parsePDF");
        try {
            PdfReader reader = new PdfReader(PDF);
            showPDFInfo(reader);
            showPDFPages(reader);  
        } catch (Exception e) {
            debugOut("We have error when create PdfReader");
            System.out.println(e);
            debugOut("if you see this - you must do something ok?");
        }
    }
    
    private static void showPDFInfo(PdfReader reader) {
        debugOut("function:showPDFInfo");
        printOut("FileLength: " + String.valueOf(reader.getFileLength()));
        printOut("NumberOfPages: " + String.valueOf(reader.getNumberOfPages()));
    }
    
    private static void showPDFPages(PdfReader reader) {
        debugOut("function:showPDFPages");
        
        for (int i = 0, count = reader.getNumberOfPages(); i < count; i++) {
            printOut("ShowPageOfNumber: " + String.valueOf(i));
            try {
                printOut("ShowPage: " + PdfTextExtractor.getTextFromPage(reader, i));
            } catch (Exception e) {
                debugOut("Can't show Page: " + String.valueOf(i));
                System.out.println(e);
            }
        }    
    }
    
    private static void debugOut(String debug) {
        if (!isDebug) return; 
        printOut(debug);
    }
    
    private static void printOut(String print) {
        System.out.println(print);
    }
}
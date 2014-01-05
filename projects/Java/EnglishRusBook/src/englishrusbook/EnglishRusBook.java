package englishrusbook;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.IOException;
import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.util.Vector;

public class EnglishRusBook {

    private static String PDFToRead = "test/data/TheTestRead.pdf";
    private static String PDFToWrite = "test/data/TheTestWrite.pdf";
    
    private static boolean isDebug = false;
    
    private static PdfReader reader;
    
    public static void main(String[] args) {
        if (!isCanParsingPDF()) return;
        
        showPDFInfo(reader);
        String lineList[] = getPDFPages(reader);
        createPDF(lineList);
    }
    
    private static void createPDF(String[] lineList) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(PDFToWrite));
            writeToPDF(document, lineList);
        } catch (FileNotFoundException | DocumentException e) {
            System.out.println("ERROR: " + e); 
        }
    }
    
    private static Vector <String> createVector(String[] lineList) {
        Vector <String> full = new Vector<String>();
        for (int i = 1; i < lineList.length; i++) {
            String[] words = lineList[i].split(" ");
            for (int j = 0; j < words.length; j++){
                full.add(words[j]);
            }
        }
        return full;
    }
    
    private static void writeToPDF(Document document, String[] lineList) {
        document.open();
        try {
            for (int i = 1, lineListCount = lineList.length; i < lineListCount; i++) {
                document.add(new Paragraph(lineList[i]));
            }
        } catch (DocumentException e) {
            System.out.println("ERROR in writing: " + e);
        } finally {
            document.close();
        }
    }
    
    private static boolean isCanParsingPDF() {
        debugOut("function:isCanParsingPDF");
        boolean isCanParsingPDF = true;
        
        try {
            reader = new PdfReader(PDFToRead);
        } catch (IOException e) {
            isCanParsingPDF = false;
        }
        
        return isCanParsingPDF;
    }
    
    private static void showPDFInfo(PdfReader reader) {
        printOut("INFO: FileLength: " + String.valueOf(reader.getFileLength()));
        printOut("INFO: NumberOfPages: " + String.valueOf(reader.getNumberOfPages()));
    }
    
    private static String[] getPDFPages(PdfReader reader) {
        debugOut("function:getPDFPages");
        
        int pageCount = reader.getNumberOfPages();
        
        // we have different start in Array and Page
        String lineList[] = new String[pageCount + 1];
        
        for (int i = 1; i <= pageCount; i++) {
            debugOut("ShowPageOfNumber: " + String.valueOf(i));
            try {
                lineList[i] = PdfTextExtractor.getTextFromPage(reader, i);
            } catch (IOException e) {
                System.out.println("ERROR in reading: " + e);
            }
        }
        
        return lineList;
    }
    
    private static void debugOut(String debug) {
        if (!isDebug) return; 
        printOut("DEBUG: " + debug);
    }
    
    private static void printOut(String print) {
        System.out.println(print);
    }
}
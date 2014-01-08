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
import java.util.HashSet;
import java.util.StringTokenizer;

public class EnglishRusBook {

    private final static String PDFToRead = "test/data/TheTestRead.pdf";
    private final static String PDFToWrite = "test/data/TheTestWrite.pdf";
    
    private final static boolean isDebug = true;
    
    private static PdfReader reader;
    
    public static void main(String[] args) {
        if (!isCanParsingPDF()) return;
        
        showPDFInfo(reader);
        String lineList[] = getPDFPages(reader);
        createPDF(getUnique(lineList));
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
    
    private static String[] getUnique(String[] lineList) {
        HashSet<String> full = new HashSet<String>();
        
        int debugAllCount = 0;
        
        String discargChars = " 1234567890~(){}[]%<>+=$#*!?.,:;-\'\"/_`";
        
        int minWordLength = 1;
        for (String line: lineList) {
            StringTokenizer token = new StringTokenizer(line, discargChars);
            while (token.hasMoreElements()) {
                debugAllCount++;
                String word = token.nextToken();
                if (word.length() > minWordLength) {
                    full.add(word.toLowerCase());
                }
            }
        }
        
        debugOut("AllCount = " + String.valueOf(debugAllCount));
        debugOut("UniqueCount = " + String.valueOf(full.size()));
        
        return full.toArray(new String[full.size()]);
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
        String lineList[] = new String[pageCount];
        
        for (int i = 1; i <= pageCount; i++) {
            debugOut("ShowPageOfNumber: " + String.valueOf(i));
            try {
                lineList[i - 1] = PdfTextExtractor.getTextFromPage(reader, i);
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
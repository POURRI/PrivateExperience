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
import java.util.ArrayList;

public class EnglishRusBook  extends Central{

    private final static String PDFToRead = "test/data/TheTestRead.pdf";
    private final static String PDFToWrite = "test/data/TheTestWrite.pdf";

    public static void main(String[] args) {
        isDebug = true;
        
        Reader parser = new Reader();
        if (!parser.isCanParsingPDF(PDFToRead)) return;
        
        PdfReader reader = parser.getReader();
        showPDFInfo(reader);
        createPDF(getUnique(getPDFPages(reader), true));
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
    
    private static String[] getUnique(ArrayList<String> lineList, boolean isPattern) {
        Parser parser = (isPattern) ? new ParserPattern() : new ParserTokenizer();
        
        parser.parsing(lineList);
        
        debugOut("AllCount = " + String.valueOf(parser.getDebugAllCount()));
        debugOut("UniqueCount = " + String.valueOf(parser.getUnique().size()));
        
        return parser.getUnique().toArray(new String[parser.getUnique().size()]);
    }
    
    private static void writeToPDF(Document document, String[] lineList) {
        document.open();
        try {
            for (String line: lineList) {
                document.add(new Paragraph(line));
            }
        } catch (DocumentException e) {
            System.out.println("ERROR in writing: " + e);
        } finally {
            document.close();
        }
    }
    
    private static void showPDFInfo(PdfReader reader) {
        printOut("INFO: FileLength: " + String.valueOf(reader.getFileLength()));
        printOut("INFO: NumberOfPages: " + String.valueOf(reader.getNumberOfPages()));
    }
    
    private static ArrayList<String> getPDFPages(PdfReader reader) {
        debugOut("function:getPDFPages");  
        ArrayList<String> lineList = new ArrayList<>();
        
        for (int i = 1, pageCount = reader.getNumberOfPages(); i <= pageCount; i++) {
            debugOut("ShowPageOfNumber: " + String.valueOf(i));
            try {
                lineList.add(PdfTextExtractor.getTextFromPage(reader, i));
            } catch (IOException e) {
                System.out.println("ERROR in reading: " + e);
            }
        }
        
        return lineList;
    }
}
/*  
                        План
   Задачі:
    1. Дати можливість зробити словник по сторінкам, як повністю всіх слів, так і нових.
    2. Показувати статистику приросту слів від сторінки.
    3. Зробити загальний словник для всіх книжок.
    4. Покращити експорт.
   Загадкові:
    1. Перенести на мобільні платформи.
    2. Зробити відкриту ліцензію, якщо це буде актуально.
*/

package englishrusbook;

import java.util.HashMap;
import java.util.HashSet;

public class EnglishRusBook  extends Central{

    private final static String PDFToRead = "test/data/TheTestRead.pdf";
    private final static String PDFToWrite = "test/data/TheTestWrite.pdf";
    private final static String FileToWriteDifferential = "test/data/differential.txt";

    public static void main(String[] args) {
        isDebug = true;
        
        Reader reader = new Reader();
        if (!reader.isCanParsing(PDFToRead)) return;
        
        reader.showInfo();
        HashMap<Integer, String> pageMap = reader.getPageMap();
        
        Parser parser = new ParserPattern();
        parser.setSource(pageMap);
        
        parser.parsing();
        
        HashSet<String> uniqueWordSet = parser.getUnique();
        HashMap<String, String> wordTranslateMap = (new TranslatorDefault()).tranlsate(uniqueWordSet);

        (new Writer()).create(PDFToWrite, wordTranslateMap);
        (new WriterDictionary()).create(FileToWriteDifferential, parser.getUniquePageMapDifferential(), wordTranslateMap);
    }
    
}
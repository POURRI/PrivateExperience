/*  
                        План
   Задачі:
    1. Дати можливість зробити словник по сторінкам, як повністю всіх слів, так і нових.
    2. Зробити загальний словник для всіх книжок.
    3. Покращити експорт.
    4. Підключити базу даних для збереження перекладів та оптимізації.
   Виконані:
    1. Статистика приросту слів від сторінки.
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
    private final static String FileToWrite = "test/data/easy.txt";
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
        
        TranslatorDefault translator = TranslatorDefault.getInstanse();
        HashMap<String, String> wordTranslateMap = translator.tranlsate(uniqueWordSet);

        Writer writerPPF = new Writer();
        writerPPF.create(PDFToWrite, wordTranslateMap);
        
        WriterDictionary writerFile = new WriterDictionary();
        writerFile.create(FileToWriteDifferential, parser.getUniquePageMapDifferential(), wordTranslateMap);
        writerFile.create(FileToWrite, parser.getUniquePageMap(), wordTranslateMap);
    }
    
}
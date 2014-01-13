/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishrusbook;

import com.gtranslate.Language;
import com.gtranslate.Translator;
import java.util.HashMap;
import java.util.HashSet;
/**
 *
 * @author POURRI
 */
public class TranslatorDefault extends Central{
    
    protected static boolean isDebug = true;
    
    private String languageInput = Language.ENGLISH;
    private String languageOutput = Language.UKRAINIAN;
    public void setLanguageInput(String language) {
        languageInput = language;
    }
    public void setLanguageOutput(String language) {
        languageOutput = language;
    }
    
    private static TranslatorDefault instanse = new TranslatorDefault();
    
    public static TranslatorDefault getInstanse() {
        return instanse;
    }
    
    public HashMap<String, String> tranlsate(HashSet<String> wordSet) {
        HashMap<String, String> map = new HashMap<>();
        
        TranslatorAutoThreadList autoThreadList = new TranslatorAutoThreadList(map, wordSet);
        
        autoThreadList.start();
        
        return map;
    }
    
    private boolean isMock = true;
    public String tranlsate(String word) {
        if (isMock) return word;
        Translator translator = Translator.getInstance();
        return translator.translate(word, languageInput, languageOutput);
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishrusbook;

import com.gtranslate.Language;
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
    
    public HashMap<String, String> tranlsate(HashSet<String> wordSet) {
        HashMap<String, String> map = new HashMap<>();
        
        TranslatorAutoThreadList autoThreadList = new TranslatorAutoThreadList(map, wordSet);
        
        autoThreadList.start();
        
        return map;
    }
}
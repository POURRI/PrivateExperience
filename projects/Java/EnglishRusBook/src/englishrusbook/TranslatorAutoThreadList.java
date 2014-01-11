/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishrusbook;

import com.gtranslate.Language;
import com.gtranslate.Translator;
import static englishrusbook.Central.debugOut;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author POURRI
 */
public class TranslatorAutoThreadList {
    
    private final HashMap<String, String> storage;
    
    private final HashSet<String> wordSet;
    
    private final ArrayList<Thread> processList = new ArrayList<>();
    
    private final static int maxProcessCount = 100;
    
    private int processCount = 0;
    
    TranslatorAutoThreadList(HashMap<String, String> storage, HashSet<String> wordSet) {
        this.storage = storage;
        this.wordSet = wordSet;
        RunnableWorld.setStorage(storage);
    }
    
    public void start() {
        for (String word : wordSet) {
            waiting();
            addProcess(word);
        }
        waitingWhileExist();
    }
    
    public void addProcess(String word) {
        Thread process = new Thread(new RunnableWorld(word));
        processList.add(process);
        process.start();
        processCount++;
    }
    
    private void waiting() {
        if (processCount < maxProcessCount) return;
        waitingWhileExist();   
    }
    
    private void waitingWhileExist() {
        int beginPosition = 0;
        while (!processList.isEmpty()) {
            Thread process = processList.get(beginPosition);
            while (process.isAlive());
            processList.remove(process);
        }
        processCount = 0;
    }
    
    public HashMap<String, String> getStorage() {
        return storage;
    }
}

class RunnableWorld extends Central implements Runnable{
    private static int debugProgress = 0;
    
    private final static String delimiter = " : ";
    
    private static String languageInput = Language.ENGLISH;
    private static String languageOutput = Language.UKRAINIAN;
    public static void setLanguageInput(String language) {
        languageInput = language;
    }
    public static void setLanguageOutput(String language) {
        languageOutput = language;
    }
    
    private static HashMap<String, String> storage;
    
    private final String word;
    
    RunnableWorld(String word) {
        this.word = word;
    }
    
    public static void setStorage(HashMap<String, String> map) {
        storage = map;
    }
    
    @Override
    public void run() {
        Translator translator = Translator.getInstance();
        String translate = translator.translate(word, languageInput, languageOutput);
        storage.put(word, translate);
        debugOut(String.valueOf(++debugProgress) + delimiter + word + delimiter + translate);
    }
}
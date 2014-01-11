/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishrusbook;

import static englishrusbook.Central.debugOut;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 *
 * @author POURRI
 */
abstract public class Parser {
    protected HashSet<String> unique = new HashSet<>();
    
    protected HashMap<Integer, HashSet<String>> uniquePageMap = new HashMap<>();
    
    protected HashMap<Integer, HashSet<String>> uniquePageMapDifferential = new HashMap<>();
    
    protected HashMap<Integer, String> source;
    
    protected int debugAllCount = 0;
    
    protected int minWordLength = 1;
    
    public void setSource(HashMap<Integer, String> source) {
        this.source = source;
    }
    
    public HashSet<String> getUnique() {
        return unique;
    }
    
    public HashMap<Integer, HashSet<String>> getUniquePageMap() {
        return uniquePageMap;
    }
    
    public HashMap<Integer, HashSet<String>> getUniquePageMapDifferential() {
        return uniquePageMapDifferential;
    }
    
    public int getDebugAllCount() {
        return debugAllCount;
    }
    
    protected void parsingDebugCount() {
        debugOut("AllCount = " + String.valueOf(getDebugAllCount()));
        debugOut("UniqueCount = " + String.valueOf(getUnique().size()));
    }
    
    abstract protected WorkerHorse getWorkerHorse(String line);
    
    public void parsing() {
        for(Map.Entry<Integer, String> entry : source.entrySet()) {
            WorkerHorse workerHorse = getWorkerHorse(entry.getValue().toLowerCase());
            
            HashSet<String> pageWordSet = new HashSet<>();
            HashSet<String> pageWordSetDifferential = new HashSet<>();
            
            while (workerHorse.hasNext()) {
                String word = workerHorse.getNext();
                if (word.length() > minWordLength) {
                    debugAllCount++;
                    pageWordSet.add(word);
                    
                    if (unique.contains(word)) continue;
                    
                    pageWordSetDifferential.add(word);
                    unique.add(word);
                }
            }
            
            uniquePageMap.put(entry.getKey(), pageWordSet);
            uniquePageMapDifferential.put(entry.getKey(), pageWordSetDifferential);
        }
        
        parsingDebugCount();
    }
}
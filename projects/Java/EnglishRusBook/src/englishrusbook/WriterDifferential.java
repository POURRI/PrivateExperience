/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishrusbook;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

/**
 *
 * @author POURRI
 */
public class WriterDifferential {
    private HashMap<Integer, HashSet<String>> source;
    
    private HashMap<String, String> translateMap;
    
    private BufferedWriter document;
    
    public void create(String file, HashMap<Integer, HashSet<String>> source, HashMap<String, String> translateMap) {
        this.source = source;
        this.translateMap = translateMap;
        try {
            document = new BufferedWriter(new FileWriter(file));
            write();
        } catch (IOException e) {
            System.out.println("ERROR in writing: " + e);
        }
    }
        
    public void write() {
        HashMap<Integer, Integer> differentialStatistic = new HashMap<>();
        try {
            for(Entry<Integer, HashSet<String>> entry : source.entrySet()) {
                HashSet<String> wordSet = entry.getValue();
                differentialStatistic.put(entry.getKey(), wordSet.size());
                for(String word: wordSet) {
                    document.write("word: " + word + " translate: " + translateMap.get(word));
                    document.newLine();
                }
            }
            
            for(Entry<Integer, Integer> entry : differentialStatistic.entrySet()) {
                document.write("NumberOfPage : " + String.valueOf(entry.getKey()));
                document.write(" SizeOfPage : " + String.valueOf(entry.getValue()));
                document.newLine();
            }
        } catch (IOException e) {
            System.out.println("ERROR in writing: " + e);
        } finally {
            try {
                document.close();
            } catch (IOException e) {
                System.out.println("ERROR in closing: " + e);
            }
        }      
    }
}
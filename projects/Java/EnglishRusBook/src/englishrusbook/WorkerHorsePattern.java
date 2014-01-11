/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishrusbook;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author POURRI
 */
public class WorkerHorsePattern extends WorkerHorse{
    protected static String settings = "[a-z]+";
    
    private final static Pattern pattern = Pattern.compile("[a-z]+");
    
    private final Matcher matcher; 
    
    WorkerHorsePattern(String source) {
        super(source);
        matcher = pattern.matcher(source);
    }
    
    public boolean hasNext() {
        return matcher.find();
    }
    
    public String getNext() {
        return matcher.group();
    }
}
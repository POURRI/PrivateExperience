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
    protected final static String settings;
    
    private final static Pattern pattern;
    
    static {
        settings = "[a-z]+";
        pattern = Pattern.compile(settings);
    }
    
    private final Matcher matcher; 
    
    WorkerHorsePattern(String source) {
        super(source);
        matcher = pattern.matcher(source);
    }
    
    @Override
    public boolean hasNext() {
        return matcher.find();
    }
    
    @Override
    public String getNext() {
        return matcher.group();
    }
}
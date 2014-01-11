/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishrusbook;

import java.util.StringTokenizer;

/**
 *
 * @author POURRI
 */
public class WorkerHorseTokenizer extends WorkerHorse{
    protected final static String settings = " 1234567890~(){}[]%<>+=$#*!?.,:;-\'\"/_`";
    
    private final StringTokenizer token;
    
    WorkerHorseTokenizer(String source) {
        super(source);
        token = new StringTokenizer(source, settings);
    }
    
    @Override
    public boolean hasNext() {
        return token.hasMoreElements();
    }
    
    @Override
    public String getNext() {
        return token.nextToken();
    }
}
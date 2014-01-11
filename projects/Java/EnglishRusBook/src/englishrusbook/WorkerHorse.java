/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishrusbook;

/**
 *
 * @author POURRI
 */
abstract public class WorkerHorse {
    abstract public boolean hasNext();
    
    abstract public String getNext();
    
    protected static String settings;
    
    protected String source;
    
    WorkerHorse(String source) {
        this.source = source;
    }
}
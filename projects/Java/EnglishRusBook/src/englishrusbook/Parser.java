/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishrusbook;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author POURRI
 */
abstract public class Parser {
    protected HashSet<String> unique = new HashSet<>();
    
    protected int debugAllCount = 0;
    
    protected int minWordLength = 1;
    
    public HashSet<String> getUnique() {
        return unique;
    }
    
    public int getDebugAllCount() {
        return debugAllCount;
    }
    
    abstract public void parsing(ArrayList<String> lineList);
}

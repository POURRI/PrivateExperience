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
public class Central {
    
    protected static boolean isDebug = false;
    
    protected static void debugOut(String debug) {
        if (!isDebug) return; 
        printOut("DEBUG: " + debug);
    }
    
    protected static void printOut(String print) {
        System.out.println(print);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishrusbook;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author POURRI
 */
public class ParserTokenizer extends Parser{
    public void parsing(ArrayList<String> lineList) {
        String discargChars = " 1234567890~(){}[]%<>+=$#*!?.,:;-\'\"/_`";
        
        for (String line: lineList) {
            StringTokenizer token = new StringTokenizer(line, discargChars);
            while (token.hasMoreElements()) {
                String word = token.nextToken();
                if (word.length() > minWordLength) {
                    debugAllCount++;
                    unique.add(word.toLowerCase());
                }
            }
        }
    }    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package englishrusbook;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author POURRI
 */
public class ParserPattern  extends Parser{
    public void parsing(ArrayList<String> lineList) {
        Pattern pattern = Pattern.compile("[a-z]+");
        
        for (String line: lineList) {
            Matcher matcher = pattern.matcher(line.toLowerCase());
            while (matcher.find()) {
                String word = matcher.group();
                if (word.length() > minWordLength) {
                    debugAllCount++;
                    unique.add(word);
                }
            }
        }
    
    } 
}

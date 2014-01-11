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
public class ParserTokenizer extends Parser{
    @Override
    protected WorkerHorse getWorkerHorse(String line) {
        return new WorkerHorseTokenizer(line);
    }   
}
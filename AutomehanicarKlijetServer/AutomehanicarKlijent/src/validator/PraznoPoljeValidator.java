/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

/**
 *
 * @author Rus
 */
public class PraznoPoljeValidator implements ComponentValidator {

    @Override
    public void validate(Object tekst) throws Exception {

        String s = String.valueOf(tekst).trim();
        if (s.length() == 0) {
            throw new Exception("Morate popuniti polje.");
        }

    }
}

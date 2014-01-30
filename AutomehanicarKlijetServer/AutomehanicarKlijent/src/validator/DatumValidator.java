/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rus
 */
public class DatumValidator implements ComponentValidator {

    @Override
    public void validate(Object tekst) throws Exception {
        try {
            Date datum = new SimpleDateFormat("dd.MM.yyyy").parse(tekst.toString());
        } catch (Exception e) {
            throw new Exception("Datum mora biti u formatu dd.MM.yyyy");
        }
    }
}

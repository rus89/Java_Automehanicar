/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

/**
 *
 * @author Rus
 */
public class JMBGValidator implements ComponentValidator {

    @Override
    public void validate(Object tekst) throws Exception {
        
        try{
            String jmbg = String.valueOf(tekst).trim();
            long br = Long.parseLong(jmbg);
            if (jmbg.trim().length()!=13)
                throw new Exception("JMBG mora sadrzati 13 cifara!");
        }catch(NumberFormatException e){
            throw new NumberFormatException("JMBG mora sadrzati samo cifre!");
        }
        
    }
    
}

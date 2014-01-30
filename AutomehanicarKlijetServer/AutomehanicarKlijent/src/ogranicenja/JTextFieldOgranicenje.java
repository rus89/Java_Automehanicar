/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ogranicenja;

import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Rus
 */
public class JTextFieldOgranicenje extends PlainDocument {
    
    
      private int ogranicenje;
    
    
      public JTextFieldOgranicenje(int ogranicenje) {
        super();
        this.ogranicenje = ogranicenje;
      }

//      public JTextFieldOgranicenje(int ogranicenje, boolean upper) {
//        super();
//        this.ogranicenje = ogranicenje;
//      }

        @Override
        public void insertString(int offs, String string, javax.swing.text.AttributeSet a) throws BadLocationException {


            if (string == null)
                
                return;

            if ((getLength() + string.length()) <= ogranicenje) {

                super.insertString(offs, string, a);

            }


        }
}

  
  
    
    


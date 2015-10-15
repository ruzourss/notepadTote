
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


/**
 * 
 * @author Tautvydas Bagocius
 * clase que actua sobre el JtextArea y revisa si se ha realizado algun cambio, implementamos la clase 
 * DocumentListener para utilizar los métodos para saber si se ha realizado algun cambio sobre el texto
 */
public class controlaCambiosTexto implements DocumentListener{
    public void controlador(int contador,JTextArea area){
       
        if(contador>0){
            int res = JOptionPane.showConfirmDialog(null, "Desea guardar antes de salir?", "Aviso", JOptionPane.YES_NO_OPTION);
            if(res==JOptionPane.OK_OPTION){
                guardar guardar = new guardar(new File("."),area);
                System.exit(0);
            }else{
                System.exit(0);
            }
        }else{
            System.exit(0);
        }   
    }
 /**
  * método que revisa si el contador es mayor que uno , eso quiere decir que se ha modificado el campo 
  * y nos pregunta que si lo queremos guardar antes de abrir otro fichero
  * @param contador le pasamos lo que vale el contador en ese momento y el jtextaea a modificar
  * @param area jtextaea a modificar
  */   
public void controladorAbrir(int contador, JTextArea area){
        if(contador>0){
            int res = JOptionPane.showConfirmDialog(null, "Desea guardar los cambios antes de abrir otro fichero?", "Aviso", JOptionPane.YES_NO_OPTION);
            if(res==JOptionPane.OK_OPTION){
                guardar guardar = new guardar(new File("."), area);
            }
        }
    }
/**
 * Métodos que son obligatorios sobreescribirlos 
 * de la clase DocumentListener. estos método captura cualqueir cambio sobre el jtextarea
 * y modifcia la variable estatica que es la que controla los cambios, si se ha modificado algo
 * le sumamos +1 a la variable estática que luego será gestionara por otro método.
 */
    @Override
    public void insertUpdate(DocumentEvent e) {
        int n = principal.getContador()+1;
        principal.setContador(n);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
       int n = principal.getContador()+1;
        principal.setContador(n);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        int n = principal.getContador()+1;
        principal.setContador(n);
    }

   
}


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * 
 * @author Tautvydas Bagocius
 * clase que hereda de Jfilechooser para guardar en un fichero los datos del jtextarea
 */
public class guardar extends JFileChooser{
    //atributo de tipo JTextArea
    private JTextArea txt;
    //constructor por defecto
    public guardar() {   
    }
    //constructor con dos atributos uno es directorio de donde va partir el JFileChooser y el otro el texto que le vamos a pasar que queremos guardar 
    public guardar(File currentDirectory,JTextArea texto) {
        super(currentDirectory);
        this.txt=texto;
        
        int num = this.showSaveDialog(this);
        
        if(num==JFileChooser.APPROVE_OPTION){
            /**
             * obtenemos el nombre del fichero seleccionado
             */
            String nombreFichero = this.getSelectedFile().getName();
            /**
             * obtenemos el directorio en que se encuentra
             */
            File directorioFileChooser = this.getCurrentDirectory();
            /**
             * guardamos todos los ficheros en un array de File
             */
            File [] lista = directorioFileChooser.listFiles();
            //variable de cntrol , que nos indica si el fichero se ha sobreescrito o no
            boolean contadorFi=false;
            //recorremos con un bucle el array de File para ver si el fichero seleccionado
            //ya existe
            for(int i=0;i<lista.length;i++){
                //si el fichero existe mostramos un mensaje en pantalla que nos avise si lo queremos sobre escribir
                if(lista[i].getName().equals(nombreFichero)){
                    //indicamos al contador que esta activado, para que sepa que arvhivo existe
                    contadorFi=true;
                    //salta el mensaje y si le hemos dado a que si realizamos la escritura del fichero
                    int res=JOptionPane.showConfirmDialog(this, "Desea sobreescribir el fichero?", "Aviso", JOptionPane.YES_NO_OPTION);
                    //si el fichero existe, res devuelve 0 si le hemos dado a si o 1 si le hemos dado que no
                    if(res==0){
                        sobreEscribeFichero(lista[i]);
                        principal.setContador(0);
                        //rompemos el bucle para que no siga porque el siguiente nombre no va existir y me sobreescribe sobre
                        //el mismo archivo, por lo que la lógica no estaría bien
                        break;
                    }else{
                        break;
                    }
                }
            }
            //Si el contador sigue en false eso quiere decir que no existe ningún fichero 
            //para sobreescribirlo y lo creamos con esta linea llamando al método crearFIchero
            if(contadorFi==false){
                crearFichero(this.getSelectedFile());
            }
            
        }   
    }
    /**
     * método que sobreescribe el fichero
     * @param fichero el fichero que queremos sobreescribir
     */
    private void sobreEscribeFichero(File fichero){
      
        try {
            //abrimos un flujo de salida hacia un fichero
            FileOutputStream out = new FileOutputStream(fichero);
            //creamos un flujo que escriba sobre el flujo de salida con codificacion UTF8
            OutputStreamWriter w = new OutputStreamWriter(out,"UTF8");
            //creamos un buffer donde irá guardando toda la información que se escribirá en el fichero
            //es más eficiente utilizar un buffer ya que primero lo guardamos en un buffer en una memoria
            //intermedia y luego de ese buffer realiza un acceso al fichero que almacena la infromación y así
            //nos ahoramos constantes conexiones de E/S
            BufferedWriter bf = new BufferedWriter(w);
            //obtenemos el texto de nuestro JtextField
            String dato = txt.getText();
            //reamplazamos los caracteres de salto de línea para que se visualize bien en el bloc de notas de windows
            String replace = dato.replace("\n", "\r\n");
            //escribimos lo reamplazado
            bf.write(replace);
            //forzamos al buffer que realize la escritura
            bf.flush();
            //cerrramos por último el flujo de salida
            bf.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(guardar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(guardar.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
    /**
     * en caso de que el fichero no existe nos lo crea con el nombre que le ponemos, el proceso
     * es básicamente el igual pero que solo nos crea el fichero y realiza la escritura de la información
     * @param fichero el fichero nuevo
     */
private void crearFichero(File fichero){
        
        try {
            //creamos el fichero primero
            fichero.createNewFile();
            //abrimos un flujo de salida hacia el fichero que acabamos de crear
            FileOutputStream out = new FileOutputStream(fichero);
            //creamos un buffer donde irá guardando toda la información que se escribirá en el fichero
            //es más eficiente utilizar un buffer ya que primero lo guardamos en un buffer en una memoria
            //intermedia y luego de ese buffer realiza un acceso al fichero que almacena la infromación y así
            //nos ahoramos constantes conexiones de E/S
            OutputStreamWriter w = new OutputStreamWriter(out,"UTF8");
            //creamos un buffer que es donde vamos a escribir nuestro texto
            BufferedWriter bf = new BufferedWriter(w);
            //obtenemos los datos de nuestro jtextArea
            String dato = txt.getText();
            //remplazamos los saltos de línea para que el bloc de notas lo abra bien
            String replace = dato.replace("\n", "\r\n");
            //escribimos los datos remplazados
            bf.write(replace);
            //forzamos la escritura
            bf.flush();
            //cerramos los flujos
            bf.close();
            
            
        } catch (IOException ex) {
            Logger.getLogger(guardar.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}

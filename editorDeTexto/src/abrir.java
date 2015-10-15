

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

/**
 * 
 * @author Tautvydas Bagocius
 * clase que se encarga de abrir un fichero y cargarlo en JTextArea y cambiar el titulo
 * del JFrame principal
 */
public class abrir extends JFileChooser{
    //declaramos los atributos de la clase que vamos a necesitar
    private String titulo="";
    //constructor por defecto
    public abrir() {
    }
    //contructor que abre el JFileChooser en el directorio que le pasamos y el area que queremos introducir los datos
    public abrir(File currentDirectory, JTextArea area) {
        super(currentDirectory);
        int seleccion = this.showDialog(this, "Abrir");
        //si le ha aceptar
        if(seleccion==JFileChooser.APPROVE_OPTION){
            //guardamos el path del fichero seleccionado
            String path = this.getSelectedFile().getAbsolutePath();
            
            try {
                //vaciamos el jtextArea para que no se valla acumulando los datos
                area.setText("");
                //modificamos el titulo del jframe y le ponemos la ruta del fichero como titulo
                setTitulo(path);
                //leemos el fichero mediante un buffer
                FileInputStream in = new FileInputStream(new File(path));
                InputStreamReader rd = new InputStreamReader(in,"UTF8");
                BufferedReader brd = new BufferedReader(rd);
                
                String dato;
                //leemos línea por línea del fichero mientras que no sea null
                while((dato=brd.readLine())!=null){
                    //obtenemos la información actual que tiene
                    String infoActual = area.getText();
                   //grabamos la informacion actual más la nueva y al final de linea un salto de línea
                    area.setText(infoActual+dato+"\n");
                }
                //ponemos el contador a 0 para que capture de nuevo si se han realizdo cambios en el texto
                principal.setContador(0);
                
                in.close();
                rd.close();
                brd.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(abrir.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(abrir.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * modifica el titulo de la clase abrir
     * @param titulo
     * @return 
     */
    private void setTitulo(String titulo){
        this.titulo=titulo;
    }
    /**
     * obtiene el titulo
     * @return titulo
     */
    public String getTitulo(){
        return this.titulo;
    }
    
    
    
}

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GestionadorExcepciones {


    public static void gestionar(Exception e, String mensajePersonalizado) {
        // TODO: aqui vendrá lo de escribir por pantalla y escribir en el fichero los errrores.
        // formato texto: e.getClass().getName() + " " e.getMesssage ó si quiero pasarle el texto
        String mensaje = e.getClass().getName() +" " + e.getMessage();
        System.out.println(mensaje);
        escribirError("Algo ha ocurrido, por favor vuelva a intentarlo");
    }



    public static void escribirError(String error){
        try {
            String filepath = ".\\error.log\\";
            File archivo = new File(filepath);

            System.out.println("Archivo Creado: " + archivo.getAbsolutePath()); //Compruebo donde he creado el archivo
            //Para escribir en el archivo
            FileWriter fw = new FileWriter(archivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("error\n");

            bw.close();
            fw.close();


        } catch (IOException e) {
            GestionadorExcepciones.gestionar(e,  "No se puede crear el archivo" + e.getMessage());
        }
    }

}
//Cada vez que haya un catch hay que enlazarlo con el GestionadorExcepciones y poner la letra e, luego sobre el tema de las excepciones se puede guardar en un documento
//Describir las excepciones de los catchs en este fichero o en un documento aparte

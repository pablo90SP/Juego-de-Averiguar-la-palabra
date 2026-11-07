import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class GestionFicheros {

    //METODO PARA COMPROBAR SI UN DIRECTORIO SE HA PODIDO CREAR BIEN

    public static void checkDirectorio(File file){
        if (!file.exists()) {
            if (file.mkdirs()) {
                System.out.println("El directorio se ha guardado correctamente");
            } else {
                System.out.println("No se pudo crear el directorio");
            }
        }
    }


    public static File escribirFicheroCreadorJuego(String clave, String palabras, String nombre) {
        File archivoFin = null;
        //Creo el directorio si no existe
        try {
            String filepath = ".\\juegoAdivinar\\" + nombre + "\\";
            File file = new File(filepath);
            checkDirectorio(file);
            String filename = "juego_de_" + nombre + ".txt";
            File archivo = new File(filepath, filename);
            if (archivo.createNewFile()) {
                System.out.println("Archivo Creado: " + archivo.getAbsolutePath()); //Compruebo donde he creado el archivo
                //Para escribir en el archivo
                FileWriter fw = new FileWriter(archivo);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write("Palabra clave: " + clave + "\n" + "Palabras: " + palabras);
                bw.close();
                fw.close();

                archivoFin = archivo;
                return archivoFin;
            } else {
                System.out.println("El archivo ya existe");
            }
        } catch (IOException e) {
            GestionadorExcepciones.gestionar(e,  "No se puede crear el archivo" + e.getMessage());
        }
        return archivoFin;
    }


    //ACABADO
    public static void mostrarJuegos(String nombreJugador) {
        //Me meto en la carpeto de los juegos creados y con el sout los imprimo
        File dir = new File(".\\juegoAdivinar\\");
        String[] archivosCreador = dir.list();
        Scanner sc = new Scanner(System.in);
        int juego;
        if (archivosCreador != null) {
            int i = 1;
            for (String archivo : archivosCreador) {
                System.out.println(i++ + ". " + archivo);

            }
        } else {
            System.out.println("No existen archivos");
        }


        do {
            System.out.println("* Seleccione un juego de la lista: ");
            juego = sc.nextInt();
            if (juego < 0 && juego >= archivosCreador.length) {
                System.out.println("Ese juego no está en la lista, vuelve a seleccionarlo.");
            }
        } while (juego <= 0 && juego > archivosCreador.length);

        empezarJugar(archivosCreador[juego - 1], nombreJugador); //Como la i empieza en 1, al introducir un juego, para buscarlo en el array debo quitarle 1

    }

    public static void empezarJugar(String juego, String nombreJugador) {
        String clave, palabrasFichero;

        ArrayList<String> palabrasFijas = new ArrayList<>();
        ArrayList<String> palabrasComparar = new ArrayList<>();
        File archivo = new File(".\\juegoAdivinar\\" + juego + "\\juego_de_" + juego + ".txt");
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String palabraClave = br.readLine(); //Obtengo la primera linea entera
            if (palabraClave != null && palabraClave.startsWith("Palabra clave: ")) {
                clave = palabraClave.substring(14); //Obtengo el texto a partir de la posicion 14 (despues de los ':')
                palabrasFichero = br.readLine();
                palabrasFichero = palabrasFichero.substring(10);


                for (String palabra : palabrasFichero.split(" ")) {
                    palabrasFijas.add(palabra); //Añado las palabras del fichero de texto en un array
                }

                System.out.println("La palabra para rendirse es: " + clave);
                pedirPalabras(palabrasComparar, palabrasFijas, clave, juego, nombreJugador);
            } else {
                System.out.println("Las palabras introducidas no son correctas.");
            }
        } catch (IOException e) {
            GestionadorExcepciones.gestionar(e,  "No se puede crear el archivo" + e.getMessage());
        }
    }

    //METODO PARA MIRAR CUANTAS PALABRAS SE HAN ACERTADO
    public static int comprobarPalabras(ArrayList<String> palabrasJuego, ArrayList<String> palabrasComparar) {
        int num = 0;
        for (String pjuego : palabrasJuego) { //p de "palabra"
            for (String pcomparar : palabrasComparar) {
                if (pjuego.equals(pcomparar)) {
                    num++;
                }
            }
        }

        return num;
    }

    public static void guardarRondas(String juego, String nombreJugador, ArrayList<String> palabrasComparar, int aciertos, int fallos) {
        try {
            String filepath = ".\\juegoAdivinar\\" + juego + "\\partidas\\";
            File file = new File(filepath);
            checkDirectorio(file);
            String filename = nombreJugador + ".txt";
            File archivo = new File(filepath, filename);

            System.out.println("Archivo Creado: " + archivo.getAbsolutePath()); //Compruebo donde he creado el archivo
            //Para escribir en el archivo
            FileWriter fw = new FileWriter(archivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Palabras Acertadas: " + aciertos + " /Fallos: " + fallos + "\n" + "Palabras: " + palabrasComparar + "\n");

            bw.close();
            fw.close();


        } catch (IOException e) {
            GestionadorExcepciones.gestionar(e,  "No se puede crear el archivo" + e.getMessage());
        }
    }


    public static void rendirse(String juego, String nombreJugador){
        try {
            String filepath = ".\\juegoAdivinar\\" + juego + "\\partidas\\";
            File file = new File(filepath);
            checkDirectorio(file);
            String filename = nombreJugador + ".txt";
            File archivo = new File(filepath, filename);

            System.out.println("Archivo Creado: " + archivo.getAbsolutePath()); //Compruebo donde he creado el archivo
            //Para escribir en el archivo
            FileWriter fw = new FileWriter(archivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("EL JUGADOR SE HA RENDIDO!!\n");

            bw.close();
            fw.close();


        } catch (IOException e) {
            GestionadorExcepciones.gestionar(e,  "No se puede crear el archivo" + e.getMessage());
        }
    }

    public static void pedirPalabras(ArrayList<String> palabrasComparar, ArrayList<String> palabrasFijas, String clave, String juego, String nombreJugador) {
        int numAciertos, numFallos;
        String palabrasUsuario;
        Scanner sc = new Scanner(System.in);
        boolean keepPlaying = true;
        boolean isTried;

        //TODO ANTONIO HACER CONTADOR DE VECES QUE SE INTENTA JUGAR ANTES DE RENDIRSE
        do {
            System.out.println("Introduce las palabras que creas que están en la lista: ");
            palabrasUsuario = sc.nextLine();


            JugadorException.validarJugador(nombreJugador, palabrasUsuario, intentos !=0 )
            if (!palabrasUsuario.trim().equalsIgnoreCase(clave.trim())) {
                palabrasComparar.clear();
                for (String palabra : palabrasUsuario.split(" ")) {
                    palabrasComparar.add(palabra.toUpperCase());
                }

                numAciertos = comprobarPalabras(palabrasFijas, palabrasComparar);
                numFallos = (palabrasFijas.size() - numAciertos);
                System.out.println("Has acertado " + numAciertos + " palabras.");
                System.out.println("Has fallado " + numFallos + " palabras.");

                guardarRondas(juego, nombreJugador, palabrasComparar, numAciertos, numFallos);  //AQUÍ LLAMO A UN METODO IMPORTANTE

                if (numFallos == 0) {
                    System.out.println("HAS GANADO!!!");
                    keepPlaying=false;
                }
            } else {
                System.out.println("TE HAS RENDIDO!! :(");
                rendirse(juego, nombreJugador);

                keepPlaying=false;
            }
        } while (keepPlaying);

    }
}

/**
 * Si hay alguna excepción, el programa no se termina, debe de continuar y avisar a su usuario
 */


/**
 * Clase para la gestión de las excepciones
 */


/**
 * String palabraClave = ("Palabra clave: " + clave ).substring(14); //readLine()
 * palabraClave.readLine();
 * String [] palabras = ("Palabras: " + palabras).substring(10).split(" ");//readLine
 */

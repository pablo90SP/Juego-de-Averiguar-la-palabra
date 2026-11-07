import java.io.IOException;
import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {

        String nombreCreador = null;

        int opcion;
        do {
            opcion = mostrarMenuGeneral();
            switch (opcion) {
                case 1:
                    menuJugador(nombreCreador);
                    break;
                case 2:
                    nombreCreador = menuCreadorJuego();
                    break;
                case 3:
                    System.out.println("Juego Finalizado");
            }
        } while(opcion != 3);

    }

    static int mostrarMenuGeneral() {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                System.out.println("******** MENU ********");
                System.out.println("1. Ingresar como jugador");
                System.out.println("2. Ingresar como creador de Juego");
                System.out.println("3. Dejar de jugar");
                System.out.println("*************************");
                System.out.print("Selecciona una opción: ");
                opcion = Integer.parseInt(sc.nextLine());

                if (opcion < 1 || opcion > 3) {
                    throw new IllegalArgumentException("La opción debe estar entre 1 y 3.");
                }

                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingresa un número válido.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                GestionadorExcepciones.gestionar(e,  "No se puede crear el archivo" + e.getMessage());
            }
        }
        return opcion;
    }

    static String menuCreadorJuego() {
        Scanner sc = new Scanner(System.in);
        System.out.println("***MENU CREADOR DE JUEGO***");
        System.out.print("Ingresa el nombre del usuario: ");
        String nombre = sc.nextLine();
        System.out.print("Ingresa la palabra CLAVE del usuario: ");
        String palabraClave = sc.nextLine();
        System.out.print("Ingresa las PALABRAS del usuario: ");
        String palabra = sc.nextLine();

        try {
            // Validación de los datos del creador, podría generar excepciones
            CreadorJuegoException.validarCreador(palabraClave, palabra, nombre);
            GestionFicheros.escribirFicheroCreadorJuego(palabraClave, palabra.toUpperCase(), nombre);

        } catch (Exception e) {
            GestionadorExcepciones.gestionar(e,  "No se puede crear el archivo" + e.getMessage());
        }
        return nombre;
    }

    //Validación de los datos del jugador, podría generar excepciones


    static void menuJugador(String nombreCreador) {
        try {

            Scanner sc = new Scanner(System.in);
            System.out.println("***MENU JUGADOR ***");
            System.out.print("Nombre del jugador: ");
            String nombreJugador = sc.nextLine();
            GestionFicheros.mostrarJuegos(nombreJugador);


        } catch (Exception e) {
            GestionadorExcepciones.gestionar(e,  "No se puede crear el archivo" + e.getMessage());
        }
    }
}

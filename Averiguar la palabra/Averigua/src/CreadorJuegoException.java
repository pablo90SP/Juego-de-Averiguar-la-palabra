import java.io.File;

// Excepción para errores del Creador del Juego
public class CreadorJuegoException extends Exception {
    public CreadorJuegoException(String mensaje) {
        super(mensaje);
    }


    // Validar al Creador del Juego con manejo de excepciones
    public static void validarCreador(String palabraClave, String palabras, String nombre) {
        try {
            if (palabraClave == null || palabraClave.trim().isEmpty()) {
                throw new CreadorJuegoException("La palabra clave no puede estar vacía.");
            }
            if (palabras == null || palabras.trim().isEmpty()) {
                throw new CreadorJuegoException("Debe ingresar al menos una palabra para adivinar.");
            }
            String[] palabrasArray = palabras.split(" ");
            if (palabrasArray.length < 2) {
                throw new CreadorJuegoException("Debe ingresar al menos dos palabras para adivinar.");
            }

            if(nombre.isBlank()) {
                throw new CreadorJuegoException("El nombre del creador no debe ser vacío.");
            }
            if (nombreExiste(nombre)) {
                throw new CreadorJuegoException("El nombre del creador ya existe. Use otro nombre.");
            }
        } catch (CreadorJuegoException e) {
            System.out.println("Error del creador: " + e.getMessage());
        }
    }

    // Verificar si un nombre de creador ya existe
    private static boolean nombreExiste(String nombre) {
        File directorio = new File(".\\juegoAdivinar\\" + nombre);
        return directorio.exists();
    }
}
import java.io.File;

// Excepción para errores del Jugador
public class JugadorException extends Exception {
    public JugadorException(String mensaje) {
        super(mensaje);
    }


    // Validar al Jugador con manejo de excepciones
    public static void validarJugador(String nombre, String palabrasUsuario, boolean intentadoAdivinar) throws JugadorException {
        if (nombre.isBlank()) {
            throw new JugadorException("El nombre del jugador no puede estar vacio.");
        }
        if (palabrasUsuario == null || palabrasUsuario.trim().isEmpty()) {
            throw new JugadorException("Debe ingresar palabras para intentar adivinar.");
        }
        if (!intentadoAdivinar) {// todo: falta comparar con la palabra clave
            throw new JugadorException("No puede rendirse sin intentar adivinar al menos una vez.");
        }

    }
}

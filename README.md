Juego por consola desarrollado en Java en el que un creador define una lista de palabras secretas y los jugadores intentan adivinarlas. Los juegos y las partidas se guardan en ficheros de texto.

Funcionalidades

Creador de juego
- Crea un juego indicando su nombre, una palabra clave para rendirse y una lista de al menos dos palabras.
- El juego se guarda en juegoAdivinar/<nombre>/juego_de_<nombre>.txt.
- Valida que los datos no estén vacíos y que el nombre no exista.

Jugador
- Elige un juego de la lista de juegos creados.
- Introduce las palabras que cree que están en la lista, separadas por espacios.
- En cada ronda se muestran las palabras acertadas y falladas.
- Puede rendirse escribiendo la palabra clave.
- Cada ronda se guarda en juegoAdivinar/<juego>/partidas/<jugador>.txt.

Gestión de errores
- Excepciones personalizadas: CreadorJuegoException y JugadorException.
- Clase GestionadorExcepciones para mostrar los errores y registrarlos en un fichero de log.

Tecnologías
- Java
- Lectura y escritura de ficheros (File, FileReader, BufferedReader, FileWriter, BufferedWriter)
- Excepciones personalizadas
- ArrayList y Scanner

Estructura del proyecto
- Main.java: menú principal (jugador, creador de juego y salir).
- GestionFicheros.java: creación de juegos, lectura de palabras y guardado de partidas.
- CreadorJuegoException.java: validación de los datos del creador.
- JugadorException.java: validación de los datos del jugador.
- GestionadorExcepciones.java: gestión centralizada de errores.

Ejecución
javac *.java
java Main

Las rutas de los ficheros están escritas en formato Windows, por lo que se recomienda ejecutarlo en ese sistema.

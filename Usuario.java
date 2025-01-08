/**
 * Clase Usuario - Representa un usuario del sistema de biblioteca
 * 
 * Esta clase maneja las operaciones relacionadas con los usuarios de la biblioteca,
 * incluyendo el préstamo y devolución de libros.
 * 
 * @author Roberto SALAZAR MARQUEZ
 * @version 1.1
 */
import java.util.*;

public class Usuario extends Persona {
    private List<Libro> librosPrestados;
    private Set<String> historialPrestamos;
    
    /**
     * Constructor para objetos de la clase Usuario
     * 
     * @param id El identificador único del usuario
     * @param nombre El nombre completo del usuario
     */
    public Usuario(String nombre, String id) {
        super(nombre, id);
        this.librosPrestados = new ArrayList<>();
        this.historialPrestamos = new HashSet<>();
    }
    
    /**
     * Constructor para objetos de la clase Usuario
     * 
     * @param otro Manejador al objeto  usuario
     */
    public Usuario(Usuario usuario) {
        super(usuario.getNombre(), usuario.getId());
        this.librosPrestados = usuario.getLibrosPrestado();
    }
    
    
    /**
     * Solicita el préstamo de un libro
     * 
     * @param libro El libro que se desea pedir prestado
     * @return true si el préstamo fue exitoso, false en caso contrario
     */
    public boolean solicitarPrestamo(Libro libro) {
        if (!libro.isPrestado() && libro.prestarLibro()) {
            librosPrestados.add(libro);
            historialPrestamos.add(libro.getIsbn());
            return true;
        }
        return false;
    }
    
    
    /**
     * Devuelve el libro actualmente prestado
     * 
     * @return true si la devolución fue exitosa, false si no hay libro prestado
     */
    public boolean devolverLibro(Libro libro) {    
        if (librosPrestados.contains(libro)) {
            libro.devolverLibro();
            librosPrestados.remove(libro);
            return true;
        }
        return false;
    }
    
    /**
     * Deprecado, para eliminación: Este elemento de la API será eliminado en una versión futura.
     * Obtiene una copia del libro prestado actualmente 
     * 
     * @return Una copia del libro prestado o null si no hay préstamos activos
     */
    
    public Libro getLibrosPrestados() {
        if(librosPrestados.size() > 0 )
            return new Libro(librosPrestados.get(0)); // Retorna una copia de la lista
        else
            return null;
    }
    
    /**
     * Obtiene una copia de la lista de libros prestados.
     * @return una nueva ArrayList conteniendo los libros prestados
     */
    public List<Libro> getLibrosPrestado() {
        return new ArrayList<>(librosPrestados);
    }
    
    /**
     * Retorna el tipo específico de esta clase.
     * Este método sobreescribe el método obtenerTipo() de la clase padre Persona
     * para proporcionar una identificación específica para los objetos Usuario.
     * 
     * @return "Usuario" - Una cadena que identifica esta clase como tipo Usuario
     */
    public String obtenerTipo() {
        return "Usuario";
    }
    
    /**
     * Genera una representación en texto del usuario
     * 
     * @return String con la información del usuario y su préstamo actual
     */
    public String toString() {
        String cad = "ID: " + getId() + ", " + "Nombre: " + getNombre() + ". ";
        if (librosPrestados.size() > 0)
            cad += "Tiene en préstamo" + librosPrestados.toString() +  " libros.";
        else
            cad += "No tiene en préstamo un libro.";
        return cad;
    }
}
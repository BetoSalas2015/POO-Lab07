/**
 * Clase que representa una biblioteca y gestiona sus operaciones principales.
 * Esta clase maneja la interacción entre libros, usuarios y empleados,
 * proporcionando funcionalidades básicas de una biblioteca.
 * 
 * La clase utiliza colecciones de Java para almacenar y gestionar:
 * - Libros (List<Libro>)
 * - Usuarios (Set<Usuario>)
 * - Empleados (Map<String, Empleado>)
 * 
 * @author Roberto Salazar Marquez
 * @version 1.1
 */
import java.util.*;

public class Biblioteca {
    /** Almacena la denominación oficial de la institución. */
    private String nombre;
    /** Contiene la dirección o localización donde se encuentra la biblioteca. */
    private String ubicacion;
    /** Colección de libros de la biblioteca. */
    private List<Libro> libros;
    /** Conjunto de usuarios registrados en la biblioteca.*/
    private Set<Usuario> usuarios;
    /** Mapa de empleados de la biblioteca.*/
    private Map<String, Empleado> empleados;

    /**
     * Constructor de la biblioteca.
     * Inicializa las colecciones y establece los datos básicos.
     * 
     * @param nombre El nombre de la biblioteca
     * @param ubicacion La ubicación física de la biblioteca
     */
    public Biblioteca(String nombre, String ubicacion) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.empleados = new HashMap<>();
        this.libros = new ArrayList<>();
        this.usuarios = new HashSet<>();
    }

    /**
     * Agrega un nuevo empleado al sistema.
     * 
     * @param empleado El empleado a agregar
     */
    public void agregarEmpleado(Empleado empleado) {
        empleados.put(empleado.getId(), empleado);
    }

    /**
     * Elimina un empleado del sistema usando su ID.
     * 
     * @param id El ID del empleado a eliminar
     */
    public void eliminarEmpleado(String id) {
        empleados.remove(id);
    }

    /**
     * Obtiene un empleado por su ID.
     * 
     * @param id El ID del empleado a buscar
     * @return El empleado encontrado o null si no existe
     */
    public Empleado obtenerEmpleado(String id) {
        return empleados.get(id);
    }
    
    
    /**
     * Agrega un nuevo libro a la colección de la biblioteca.
     * 
     * @param libro El libro a agregar
     */
    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    /**
     * Elimina un libro de la colección de la biblioteca.
     * 
     * @param libro El libro a eliminar
     */
    public void eliminarLibro(Libro libro) {
        libros.remove(libro);
    }

    /**
     * Busca libros por título, ignorando mayúsculas y minúsculas.
     * 
     * @param titulo El título o parte del título a buscar
     * @return Lista de libros que coinciden con el criterio de búsqueda
     */
    public List<Libro> buscarLibrosPorTitulo(String titulo) {
        List<Libro> resultados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                resultados.add(libro);
            }
        }
        return resultados;
    }
    
    /**
     * Obtiene el nombre de la biblioteca.
     * 
     * @return El nombre de la biblioteca
     */
    public String getNombre() { 
        return new String(nombre); 
    }
    
    /**
     * Obtiene la ubicación de la biblioteca.
     * 
     * @return La ubicación de la biblioteca
     */
    public String getUbicación() {
        return new String(ubicacion);
    }

    /**
     * Agrega un nuevo usuario al sistema.
     * 
     * @param usuario El usuario a agregar
     */
    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    /**
     * Elimina un usuario del sistema.
     * 
     * @param usuario El usuario a eliminar
     */
    public void eliminarUsuario(Usuario usuario) {
        usuarios.remove(usuario);
    }
    
    /**
     * Busca un usuario por su ID.
     * 
     * @param id El ID del usuario a buscar
     * @return El usuario encontrado o null si no existe
     */
    public Usuario buscarUsuarioPorId(String id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId().equals(id)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Procesa el préstamo de un libro a un usuario.
     * 
     * @param isbn El ISBN del libro a prestar
     * @param idUsuario El ID del usuario que solicita el préstamo
     * @param idEmpleado El ID del empleado que procesa el préstamo
     * @return true si el préstamo fue exitoso, false en caso contrario
     */
    public boolean prestarLibro(String isbn, String idUsuario, String idEmpleado) {
        Libro libro = null;
        for (Libro l : libros) {
            if (l.getIsbn().equals(isbn)) {
                libro = l;
                break;
            }
        }
        
        Usuario usuario = buscarUsuarioPorId(idUsuario);
        Empleado empleado = empleados.get(idEmpleado);
    
        if (libro != null && usuario != null && empleado != null && !libro.isPrestado()) {
            return empleado.procesarPrestamo(libro, usuario);
        }
        return false;
    }

    
    /**
     * Procesa la devolución de un libro.
     * 
     * @param isbn El ISBN del libro a devolver
     * @param idEmpleado El ID del empleado que procesa la devolución
     * @return true si la devolución fue exitosa, false en caso contrario
     */
    public boolean devolverLibro(String isbn, String idEmpleado) {
        // Buscar el libro manualmente
        Libro libro = null;
        for (Libro l : libros) {
            if (l.getIsbn().equals(isbn)) {
                libro = l;
                break;
            }
        }
        
        Empleado empleado = empleados.get(idEmpleado);
    
        if (libro != null && empleado != null && libro.isPrestado()) {
            libro.devolverLibro();
            empleado.devolverPrestamo();
            return true;
        }
        return false;
    }
    
    /**
     * Obtiene una lista de todos los libros disponibles para préstamo.
     * 
     * @return Lista de libros no prestados
     */
    public List<Libro> getLibrosDisponibles() {
        List<Libro> disponibles = new ArrayList<>();
        for (Libro libro : libros) {
            if (!libro.isPrestado()) {
                disponibles.add(libro);
            }
        }
        return disponibles;
    }
    
    /**
     * Obtiene una lista de todos los libros actualmente prestados.
     * 
     * @return Lista de libros prestados
     */
    public List<Libro> getLibrosPrestados() {
        List<Libro> prestados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.isPrestado()) {
                prestados.add(libro);
            }
        }
        return prestados;
    }
    
    /**
     * Genera una representación en texto del estado actual de la biblioteca.
     * Incluye información sobre libros, usuarios y empleados.
     * 
     * @return String con el estado detallado de la biblioteca
     */
    public String toString() {
        String estado = "";
        estado += "Biblioteca: " + nombre + "\n";
        estado += "Ubicación: " + ubicacion + "\n";
        estado += "Total de libros: " + libros.size() + "\n";
        estado += "Libros disponibles: " + getLibrosDisponibles().size() + "\n";
        estado += "Libros prestados: " + getLibrosPrestados().size() + "\n";
        estado += "Total de usuarios registrados: " + usuarios.size() + "\n";
        estado += "Total de empleados: " + empleados.size() + "\n";
        
        // Información detallada de libros prestados
        estado += "\nLibros actualmente prestados:\n";
        for (Libro libro : getLibrosPrestados()) {
            estado += "- " + libro.getTitulo() + "\n";
        }
        
        return estado;
    }
}
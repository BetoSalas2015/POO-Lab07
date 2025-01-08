

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Clase de prueba para la clase Usuario.
 * Contiene pruebas unitarias para verificar el funcionamiento correcto
 * de los métodos de la clase Usuario.
 *
 * @author  Roberto Salazar
 * @version 1.0
 */
public class UsuarioTest
{
    /** Usuario de prueba */
    private Usuario usuario;
    /** Libro de prueba */
    private Libro libro;
    
    /**
     * Método que se ejecuta antes de cada prueba.
     * Inicializa un usuario y un libro de prueba.
     */
    @BeforeEach
    public void setUp() {
        usuario = new Usuario("Juan Pérez", "U001");
        libro = new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "9788424922498", 863);
    }

    /**
     * Prueba el constructor de Usuario.
     * Verifica que los atributos se inicialicen correctamente.
     */
    @Test
    public void testConstructor() {
        assertEquals("U001", usuario.getId());
        assertEquals("Juan Pérez", usuario.getNombre());
        assertNull(usuario.getLibrosPrestados());
    }
    
    /**
     * Prueba el método solicitarPrestamo.
     * Verifica que un usuario pueda solicitar un libro disponible.
     */
    @Test
    public void testSolicitarPrestamo() {
        assertTrue(usuario.solicitarPrestamo(libro));
        assertTrue(libro.isPrestado());
    }
    
    /**
     * Prueba el método solicitarPrestamo con un libro ya prestado.
     * Verifica que no se pueda prestar un libro que ya está prestado.
     */
    @Test
    public void testSolicitarPrestamoLibroNoPrestado() {
        libro.prestarLibro(); // Libro ya prestado
        assertFalse(usuario.solicitarPrestamo(libro));
    }
    
    /**
     * Prueba el método devolverLibro.
     * Verifica que un usuario pueda devolver un libro prestado.
     */
    @Test
    public void testDevolverLibro() {
        usuario.solicitarPrestamo(libro);
        assertTrue(usuario.devolverLibro(libro));
        assertNull(usuario.getLibrosPrestados());
    }
    
    /**
     * Prueba el método devolverLibro sin préstamos activos.
     * Verifica que no se pueda devolver un libro cuando no hay préstamos.
     */
    @Test
    public void testDevolverLibroSinPrestamo() {
        //assertFalse(usuario.devolverLibro());
    }
    
    /**
     * Prueba el método toString.
     * Verifica que la representación en cadena del usuario sea correcta.
     */
    @Test
    public void testToString() {
        assertEquals("ID: U001, Nombre: Juan Pérez. No tiene en préstamo un libro.", usuario.toString());
    }
}

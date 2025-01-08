/**
 * Clase de pruebas para la clase Empleado.
 * Esta clase contiene pruebas unitarias para verificar el correcto funcionamiento
 * de todos los métodos y funcionalidades de la clase Empleado.
 * 
 * @author Roberto SALAZAR MARQUEZ
 * @version 1.1
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;


public class EmpleadoTest
{
    /** Variable de instancia para el empleado que se utilizará en las pruebas */
    private Empleado empleado;
    /** Variable de instancia para el usuario que se utilizará en las pruebas de préstamo */
    private Usuario usuario;
    /** Variable de instancia para el libro que se utilizará en las pruebas de préstamo */
    private Libro libro;

    /**
     * Configura el escenario de pruebas antes de cada método.
     * Crea nuevas instancias de Empleado, Usuario y Libro para usar en las pruebas.
     */
    @BeforeEach
    public void setUp() {
        empleado = new Empleado("Juan Pérez", "EMP001", 25000.0, "Bibliotecario");
        usuario = new Usuario("Ana García", "U001");
        libro = new Libro("1984", "George Orwell", "9788499890944", 326);
    }
    
    /**
     * Prueba el constructor de la clase Empleado.
     * Verifica que todos los atributos se inicialicen correctamente.
     */
    @Test
    public void testConstructor() {
        assertEquals("Juan Pérez", empleado.getNombre());
        assertEquals("EMP001", empleado.getId());
        assertEquals(25000.00, empleado.getSalario());
        assertEquals("Bibliotecario", empleado.getPuesto());
    }
    
    /**
     * Prueba los métodos setter y getter de la clase Empleado.
     * Verifica que los valores se establezcan y recuperen correctamente.
     */
    @Test
    public void testSettersAndGetters() {
        empleado.setPuesto("Supervisor");
        empleado.setSalario(25000.0);
        empleado.setTurno(Empleado.MATUTINO);

        assertEquals("Supervisor", empleado.getPuesto());
        assertEquals(25000.0, empleado.getSalario(), 0.01);
        assertEquals(Empleado.MATUTINO, empleado.getTurno());
    }

    /**
     * Prueba el método obtenerTipo().
     * Verifica que retorne correctamente el tipo "Empleado".
     */
    @Test
    public void testObtenerTipo() {
        assertEquals("Empleado", empleado.obtenerTipo());
    }

    /**
     * Prueba el método generarId().
     * Verifica que genere IDs únicos y con el formato correcto.
     */
    @Test
    public void testGenerarId() {
        String id1 = Empleado.generarId();
        String id2 = Empleado.generarId();
        
        assertTrue(id1.startsWith("P"));
        assertTrue(id2.startsWith("P"));
        assertNotEquals(id1, id2);
    }
    
    @Test
    public void testDevolverPrestamo() {
        empleado.procesarPrestamo(libro, usuario);
        assertTrue(empleado.devolverPrestamo());
        
        // Verificar que la cola está vacía después de la devolución
        Queue<Prestamo> prestamos = empleado.getPrestamosEnProceso();
        assertTrue(prestamos.isEmpty());
    }

    @Test
    public void testDevolverPrestamoSinPrestamosActivos() {
        assertFalse(empleado.devolverPrestamo());
    }

    /**
     * Prueba el procesamiento exitoso de un préstamo.
     * Verifica el comportamiento cuando el libro está prestado.
     */
    @Test
    public void testProcesarPrestamoExitoso() {
        assertTrue(empleado.procesarPrestamo(libro, usuario));
        
        // Verificar que el préstamo se agregó a la cola
        Queue<Prestamo> prestamos = empleado.getPrestamosEnProceso();
        assertFalse(prestamos.isEmpty());
        assertEquals(1, prestamos.size());
        
        // Verificar que se agregó al historial
        List<Prestamo> historial = empleado.getHistorialPrestamos();
        assertFalse(historial.isEmpty());
        assertEquals(1, historial.size());
    }

    /**
     * Prueba el procesamiento de préstamo con parámetros nulos.
     * Verifica que el método maneje correctamente los casos de entrada nula.
     */
    @Test
    public void testProcesarPrestamoConParametrosInvalidos() {
        assertFalse(empleado.procesarPrestamo(null, usuario));
        assertFalse(empleado.procesarPrestamo(libro, null));
        
        // Intentar prestar un libro ya prestado
        empleado.procesarPrestamo(libro, usuario);
        assertFalse(empleado.procesarPrestamo(libro, usuario));
    }

    /**
     * Prueba el método toString().
     * Verifica que la representación en cadena del empleado incluya todos los atributos relevantes.
     */
    @Test
    public void testToString() {
        empleado.setSalario(25000.0);
        empleado.setTurno(Empleado.MATUTINO);
        
        String resultado = empleado.toString();
        
        assertTrue(resultado.contains("Juan Pérez"));
        assertTrue(resultado.contains("EMP001"));
        assertTrue(resultado.contains("Bibliotecario"));
        assertTrue(resultado.contains("25000.0"));
        assertTrue(resultado.contains("0")); // turno MATUTINO
    }

    /**
     * Limpia el escenario de pruebas después de cada método.
     */
    @AfterEach
    public void tearDown()
    {
    }
}

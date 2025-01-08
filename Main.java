
/**
 * Write a description of class Main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
/**
 * Clase principal para probar la funcionalidad de la biblioteca.
 * 
 * @author Roberto Salazar Marquez
 * @version 1.1
 */
public class Main {
    public static void main(String[] args) {
        // Crear una nueva biblioteca
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central", "Av. Universidad 3000");
        
        // Crear y agregar empleados
        Empleado emp1 = new Empleado("Juan Pérez", "E001", 16000.00, "Bibliotecario");
        Empleado emp2 = new Empleado("María García", "E002", 8000.00, "Asistente");
        biblioteca.agregarEmpleado(emp1);
        biblioteca.agregarEmpleado(emp2);
        
        // Crear y agregar libros
        Libro libro1 = new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", "9788424922498", 863);
        Libro libro2 = new Libro("Cien años de soledad", "Gabriel García Márquez", "9780307474728", 417);
        Libro libro3 = new Libro("El Principito", "Antoine de Saint-Exupéry", "9788498381498", 96);
        Libro libro4 = new Libro("1984", "George Orwell", "9788499890944", 326);
        Libro libro5 = new Libro("Orgullo y Prejuicio", "Jane Austen", "9788491052050", 424);
        Libro libro6 = new Libro("La Odisea", "Homero", "9788467028621", 448);
        Libro libro7 = new Libro("Fahrenheit 451", "Ray Bradbury", "9788445073192", 192);
        Libro libro8 = new Libro("La Metamorfosis", "Franz Kafka", "9788420651361", 128);
        Libro libro9 = new Libro("Moby Dick", "Herman Melville", "9788491051322", 752);
        biblioteca.agregarLibro(libro1);
        biblioteca.agregarLibro(libro2);
        biblioteca.agregarLibro(libro3);
        biblioteca.agregarLibro(libro4);
        biblioteca.agregarLibro(libro5);
        biblioteca.agregarLibro(libro6);
        biblioteca.agregarLibro(libro7);
        biblioteca.agregarLibro(libro8);
        biblioteca.agregarLibro(libro9);
        
        // Crear y agregar usuarios
        Usuario user1 = new Usuario("Ana López", "U001");
        Usuario user2 = new Usuario("Carlos Ruiz", "U002");
        biblioteca.agregarUsuario(user1);
        biblioteca.agregarUsuario(user2);
        
        // Probar búsqueda de libros
        System.out.println("Búsqueda de libros con 'don':");
        for (Libro libro : biblioteca.buscarLibrosPorTitulo("don")) {
            System.out.println(libro.getTitulo());
        }
        
        // Probar préstamo de libro
        System.out.println("\nProbando préstamo de libro:");
        if (biblioteca.prestarLibro("9788498381498", "U001", "E001")) {
            System.out.println("Préstamo realizado con éxito");
        } else {
            System.out.println("No se pudo realizar el préstamo");
        }
        
        // Probar préstamo de libro
        System.out.println("\nProbando préstamo de libro:");
        if (biblioteca.prestarLibro("9788445073192", "U001", "E001")) {
            System.out.println("Préstamo realizado con éxito");
        } else {
            System.out.println("No se pudo realizar el préstamo");
        }
        
        // Mostrar libros prestados
        System.out.println("\nLibros prestados:");
        for (Libro libro : biblioteca.getLibrosPrestados()) {
            System.out.println(libro.getTitulo());
        }
        
        // Probar devolución de libro
        System.out.println("\nProbando devolución de libro:");
        if (biblioteca.devolverLibro("9788498381498", "E001")) {
            System.out.println("Devolución realizada con éxito");
        } else {
            System.out.println("No se pudo realizar la devolución");
        }
        
        // Mostrar estado final de la biblioteca
        System.out.println("\nEstado final de la biblioteca:");
        System.out.println(biblioteca.toString());
    }
}

package org.example;

import org.example.connection.DatabaseConnection;
import org.example.controllers.LibroDAO;
import org.example.controllers.PersonaDAO;
import org.example.controllers.PrestamoDAO;
import org.example.controllers.UsuarioDAO;
import org.example.data.Data;
import org.example.entities.Libro;
import org.example.entities.Persona;
import org.example.entities.Prestamo;
import org.example.entities.Usuario;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        DatabaseConnection dbconnect = new DatabaseConnection();
        dbconnect.connectDb();
        Data addData = new Data();
        addData.enterData();

        Scanner scanner = new Scanner(System.in);

        //////Persona
        System.out.println("Ingrese los datos para crear la persona:");
        Persona persona = new Persona();

        System.out.println("Ingrese el nombre:");
        String nombre = scanner.nextLine();
        persona.setNombre(nombre);

        System.out.println("Ingrese el apellido:");
        String apellido = scanner.nextLine();
        persona.setApellido(apellido);

        System.out.println("Ingrese el sexo:");
        String sexo = scanner.nextLine();
        persona.setSexo(sexo);

        // Crear instancia del DAO y guardar la persona
        PersonaDAO personaDao = new PersonaDAO();
        personaDao.crearPersona(persona); // Método para guardar la persona en la base de datos

        System.out.println("Persona guardada exitosamente.");

        //////Usuario
        System.out.println("Ingrese los datos para crear el usuario:");
        Usuario usuario = new Usuario();

        System.out.println("Ingrese el rol:");
        String rol = scanner.nextLine();
        usuario.setRol(rol);

        usuario.setIdPersona(persona);

        // Crear instancia del DAO y guardar el usuario
        UsuarioDAO usuarioDao = new UsuarioDAO();
        usuarioDao.crearUsuario(usuario); // Método para guardar el usuario en la base de datos

        System.out.println("Usuario guardado exitosamente.");

        //////Libro
        System.out.println("Ingrese los datos para crear el libro:");
        Libro libro = new Libro();

        System.out.println("Ingrese el título:");
        String titulo = scanner.nextLine();
        libro.setTitulo(titulo);

        System.out.println("Ingrese el Autor del libro:");
        String autor = scanner.nextLine();
        libro.setAutor(autor);

        System.out.println("Ingrese el isbn del libro:");
        String isbn = scanner.nextLine();
        libro.setIsbn(isbn);


        // Crear instancia del DAO y guardar el libro
        LibroDAO libroDao = new LibroDAO();
        libroDao.crearLibro(libro); // Método para guardar el libro en la base de datos

        System.out.println("Libro guardado exitosamente.");

        //////Actualizar libro
        System.out.println("Ingrese el Id del libro a actualizar:");
        String libroIdStr = scanner.nextLine();
        int libroId = Integer.parseInt(libroIdStr);

        // Obtener el libro desde la base de datos
        Libro libroActualizar = libroDao.obtenerLibroId(libroId);

        if (libroActualizar != null) {
            // Modificar los atributos del libro
            System.out.println("Ingrese el nuevo título :");
            String nuevoTitulo = scanner.nextLine();
            if (!nuevoTitulo.isEmpty()) {
                libroActualizar.setTitulo(nuevoTitulo);
            }

            System.out.println("Ingrese el nuevo autor:");
            String nuevoAutor = scanner.nextLine();
            if (!nuevoAutor.isEmpty()) {
                libroActualizar.setAutor(nuevoAutor);
            }

            System.out.println("Ingrese el nuevo ISBN:");
            String nuevoIsbn = scanner.nextLine();
            if (!nuevoIsbn.isEmpty()) {
                libroActualizar.setIsbn(nuevoIsbn);
            }

            // Guardar los cambios en la base de datos
            libroDao.actualizarLibro(libroActualizar);

            System.out.println("Libro actualizado exitosamente.");
        } else {
            System.out.println("No se encontró el libro con el ID especificado.");
        }


        //////Eliminar Libro
        System.out.println("Ingrese el ID del libro a eliminar:");
        String libroIdStr2 = scanner.nextLine();
        int libroId2 = Integer.parseInt(libroIdStr2); // Convertir el ID a int

        LibroDAO libroDao2 = new LibroDAO();
        libroDao.eliminarLibro(libroId2);

        //////Prestamo
        System.out.println("Ingrese los datos para el préstamo:");
        Prestamo prestamo = new Prestamo();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("Fecha del préstamo:");
        String fechaPrestamo = scanner.nextLine();
        prestamo.setFechaPrestamo(dateFormat.parse(fechaPrestamo));

        System.out.println("Ingrese la fecha de devolución:");
        String devolucion = scanner.nextLine();
        prestamo.setFechaDevolucion(dateFormat.parse(devolucion));

        prestamo.setIdUsuario(usuario);

        prestamo.setIdLibro(libro);

        // Crear instancia del DAO y guardar el prestamo
        PrestamoDAO prestamoDao = new PrestamoDAO();
        prestamoDao.crearPrestamo(prestamo); // Método para guardar el prestamo en la base de datos.

        System.out.println("Prestamo creado exitosamente.");



    }
}
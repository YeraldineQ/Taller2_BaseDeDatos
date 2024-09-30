package org.example.data;

import org.example.entities.Libro;
import org.example.entities.Persona;
import org.example.entities.Prestamo;
import org.example.entities.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class Data {
    private SessionFactory sessionFactory;
    private boolean dataInit;
    private List<Object> entities;

    public Data() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void enterData() throws ParseException {
        if (dataInit) {
            System.out.println("Los datos ya han sido creados");
            return;
        }

        Session session = sessionFactory.openSession();
        try {
            // Verificar si ya existen registros en la tabla Persona
            long countPersonas = (long) session.createQuery("SELECT COUNT(p.id) FROM Persona p").uniqueResult();
            if (countPersonas > 0) {
                System.out.println("Los datos ya existen en la base de datos.");
                dataInit = true;
                session.close();
                return;
            }

            session.beginTransaction();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            //Persona
            Persona persona1 = new Persona();
            persona1.setNombre("Yeraldine");
            persona1.setApellido("Quintero");
            persona1.setSexo("Femenino");

            Persona persona2 = new Persona();
            persona2.setNombre("Katherine");
            persona2.setApellido("Hurtado");
            persona2.setSexo("Femenino");

            Persona persona3 = new Persona();
            persona3.setNombre("Daniel");
            persona3.setApellido("Amariles");
            persona3.setSexo("Masculino");

            Persona persona4 = new Persona();
            persona4.setNombre("Juan Pablo");
            persona4.setApellido("Morales");
            persona4.setSexo("Masculino");

            Persona persona5 = new Persona();
            persona5.setNombre("Juliana");
            persona5.setApellido("Gómez");
            persona5.setSexo("Femenino");

            //Usuario
            Usuario usuario1 = new Usuario();
            usuario1.setIdPersona(persona1);
            usuario1.setRol("Bibliotecario");

            Usuario usuario2 = new Usuario();
            usuario2.setIdPersona(persona2);
            usuario2.setRol("Bibliotecario");

            Usuario usuario3 = new Usuario();
            usuario3.setIdPersona(persona3);
            usuario3.setRol("Cliente");

            Usuario usuario4 = new Usuario();
            usuario4.setIdPersona(persona4);
            usuario4.setRol("Cliente");

            Usuario usuario5 = new Usuario();
            usuario5.setIdPersona(persona5);
            usuario5.setRol("Cliente");


            //Libro
            Libro libro1 = new Libro();
            libro1.setAutor("Gabriel García Márquez");
            libro1.setTitulo("Cien años de soledad");
            libro1.setIsbn("23");

            Libro libro2 = new Libro();
            libro2.setAutor("Don Quijote de la Mancha");
            libro2.setTitulo("Miguel de Cervantes");
            libro2.setIsbn("24");

            Libro libro3 = new Libro();
            libro3.setAutor("El código Da Vinci");
            libro3.setTitulo("Dan Brown");
            libro3.setIsbn("25");

            Libro libro4 = new Libro();
            libro4.setAutor("Diez Negritos");
            libro4.setTitulo("Agatha Christie");
            libro4.setIsbn("26");

            Libro libro5 = new Libro();
            libro5.setAutor("Las Aventuras de Sherlock Holmes");
            libro5.setTitulo("Arthur Conan Doyle");
            libro5.setIsbn("27");

            //prestamo
            Prestamo prestamo1 = new Prestamo();
            prestamo1.setIdUsuario(usuario1);
            prestamo1.setIdLibro(libro1);
            prestamo1.setFechaPrestamo(formato.parse("2024-01-01"));
            prestamo1.setFechaDevolucion(formato.parse("0000-00-00"));
            prestamo1.setActivo(true);

            Prestamo prestamo2 = new Prestamo();
            prestamo2.setIdUsuario(usuario2);
            prestamo2.setIdLibro(libro2);
            prestamo2.setFechaPrestamo(formato.parse("2024-02-01"));
            prestamo2.setFechaDevolucion(formato.parse("2024-03-01"));
            prestamo2.setActivo(false);

            Prestamo prestamo3 = new Prestamo();
            prestamo3.setIdUsuario(usuario3);
            prestamo3.setIdLibro(libro3);
            prestamo3.setFechaPrestamo(formato.parse("2024-03-01"));
            prestamo3.setFechaDevolucion(formato.parse("2024-04-01"));
            prestamo3.setActivo(false);

            Prestamo prestamo4 = new Prestamo();
            prestamo4.setIdUsuario(usuario4);
            prestamo4.setIdLibro(libro4);
            prestamo4.setFechaPrestamo(formato.parse("2024-04-01"));
            prestamo4.setFechaDevolucion(formato.parse("0000-00-00"));
            prestamo4.setActivo(true);

            Prestamo prestamo5 = new Prestamo();
            prestamo5.setIdUsuario(usuario5);
            prestamo5.setIdLibro(libro5);
            prestamo5.setFechaPrestamo(formato.parse("2024-05-01"));
            prestamo5.setFechaDevolucion(formato.parse("2024-06-01"));
            prestamo5.setActivo(false);

            List<Object> entities = Arrays.asList(persona1, persona2, persona3, persona4, persona5, usuario1, usuario2, usuario3, usuario4, usuario5, libro1, libro2, libro3, libro4, libro5, prestamo1, prestamo2, prestamo3, prestamo4, prestamo5);

            for (Object entity : entities) {
                session.persist(entity);
            }

            session.getTransaction().commit();
            dataInit = true;
            System.out.println("Los datos han sido agregados correctamente.");
        } catch (HibernateException e) {
            System.out.println("El error es: " + e.getMessage());
        } finally {
            session.close();
        }
    }
}

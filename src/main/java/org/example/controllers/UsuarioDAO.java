package org.example.controllers;

import org.example.connection.DatabaseConnection;
import org.example.entities.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UsuarioDAO {
    private DatabaseConnection databaseConnection;

    public UsuarioDAO(){
        this.databaseConnection = new DatabaseConnection();
    }

    //Método para guardar datos nuevos
    public void crearUsuario(Usuario usuario){
        Transaction transaction = null;

        try (Session session = databaseConnection.getSession()){
            transaction = session.beginTransaction();
            session.save(usuario);
            transaction.commit();
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //Método para obtener la usuario por id
    public Usuario obtenerUsuarioId(int id){
        try(Session session = databaseConnection.getSession()){
            return session.get(Usuario.class, id);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Método para obtener todas las personas
    public List<Usuario> obtenerUsuario(){
        try(Session session = databaseConnection.getSession()){
            return session.createQuery("FROM usuario", Usuario.class).list();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //Método para actualizar usuario
    public void actualizarUsuario(Usuario usuario){
        Transaction transaction = null;
        try(Session session = databaseConnection.getSession()){
            transaction = session.beginTransaction();
            session.update(usuario);
            transaction.commit();

        }catch (Exception e) {
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    //Método para eliminar usuario
    public void eliminarUsuario(int id){
        Transaction transaction = null;
        try(Session session = databaseConnection.getSession()){
            transaction = session.beginTransaction();
            Usuario usuario = session.get(Usuario.class, id);
            if(usuario != null){
                session.delete(usuario);
                transaction.commit();
            }
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

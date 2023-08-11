package com.DOA;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.Modal.Task;

import java.util.List;

public class TaskDAO implements EntityAccessor<Task> {
    private final SessionFactory sessionFactory;

    public TaskDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    //------------------------------------------adding task to the table------------------
    @Override
    public void addEntity(Task task) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(task);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//----------------------------updating the existing table data's ------------------------------ 
    @Override
    public void updateEntity(Task task) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(task);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//--------------------------------deleting the table data's from the task table where the id matches-------------
    @Override
    public void deleteEntity(int taskId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Task task = session.get(Task.class, taskId);
            if (task != null) {
                session.delete(task);
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//-----------------------------------displaying the task where the id matches ----------------------------------
    @Override
    public Task getEntityById(int taskId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Task.class, taskId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//----------------------------------------displaying the all the data from the task table----------------------
    @Override
    public List<Task> getAllEntities() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Task", Task.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

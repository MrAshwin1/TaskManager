package com.DOA;

import com.Modal.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class DepartmentDAO implements EntityAccessor<Department> {
    private final SessionFactory sessionFactory;

    public DepartmentDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
//-------------------------adding the department data into the department table -----------------------
    @Override
    public void addEntity(com.Modal.Department department) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(department);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//-------------------------updating the department data where the id matches --------------------------
    @Override
    public void updateEntity(Department department) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(department);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  //-------------------------deleting the department data where the id matchers -----------------------
    @Override
    public void deleteEntity(int deptId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Department department = session.get(Department.class, deptId);
            if (department != null) {
                session.delete(department);
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//-----------------------------------displaying the departments data where the id matches ------------------------------------
    @Override
    public Department getEntityById(int deptId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Department.class, deptId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
//----------------------------------------displaying the all the departments from the departments table----------------------------
    @Override
    public List<Department> getAllEntities() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Department", Department.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package de.test;

import java.io.PrintStream;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.test.entities.Emp;

@Stateless
@Remote(IEmpService.class)
@LocalBean
public class EmpService implements IEmpService {

  @PersistenceContext // (unitName = "ReadingUnit")
  private EntityManager em;

  public void myFunction(String name) {
    System.out.println("hallo " + name);
  }

  public void greet(PrintStream to, String name) {
    to.println(createGreeting(name));
  }

  public String createGreeting(String name) {
    return "Hello, " + name + "!";
  }

  public void removeAllEmps() {
    em.createQuery("DELETE FROM Emp").executeUpdate();
  }

  public List<Emp> getAllEmps() {
    return em.createQuery("FROM Emp", Emp.class).getResultList();
  }
}

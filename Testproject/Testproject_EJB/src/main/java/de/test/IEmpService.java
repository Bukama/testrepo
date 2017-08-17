package de.test;

import java.util.List;

import javax.ejb.Remote;

import de.test.entities.Emp;

@Remote
public interface IEmpService {

  void removeAllEmps();

  List<Emp> getAllEmps();

}

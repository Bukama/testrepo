package de.test.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the EMP database table.
 * 
 */
@Entity
@NamedQuery(name = "Emp.findAll", query = "SELECT e FROM Emp e")
@Table(name = "Emp", schema = "bish")
public class Emp implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(name = "EMP_EMPNO_GENERATOR", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMP_EMPNO_GENERATOR")
  private long empno;

  private BigDecimal comm;

  private String ename;

  @Temporal(TemporalType.DATE)
  private Date hiredate;

  private String job;

  private BigDecimal sal;

  // bi-directional many-to-one association to Emp
  @ManyToOne
  @JoinColumn(name = "MGR")
  private Emp emp;

  // bi-directional many-to-one association to Emp
  @OneToMany(mappedBy = "emp")
  private List<Emp> emps;

  public Emp() {
  }

  public long getEmpno() {
    return this.empno;
  }

  public void setEmpno(long empno) {
    this.empno = empno;
  }

  public BigDecimal getComm() {
    return this.comm;
  }

  public void setComm(BigDecimal comm) {
    this.comm = comm;
  }

  public String getEname() {
    return this.ename;
  }

  public void setEname(String ename) {
    this.ename = ename;
  }

  public Date getHiredate() {
    return this.hiredate;
  }

  public void setHiredate(Date hiredate) {
    this.hiredate = hiredate;
  }

  public String getJob() {
    return this.job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public BigDecimal getSal() {
    return this.sal;
  }

  public void setSal(BigDecimal sal) {
    this.sal = sal;
  }

  public Emp getEmp() {
    return this.emp;
  }

  public void setEmp(Emp emp) {
    this.emp = emp;
  }

  public List<Emp> getEmps() {
    return this.emps;
  }

  public void setEmps(List<Emp> emps) {
    this.emps = emps;
  }

  public Emp addEmp(Emp emp) {
    getEmps().add(emp);
    emp.setEmp(this);

    return emp;
  }

  public Emp removeEmp(Emp emp) {
    getEmps().remove(emp);
    emp.setEmp(null);

    return emp;
  }

}
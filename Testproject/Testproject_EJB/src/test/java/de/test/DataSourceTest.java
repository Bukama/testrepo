package de.test;

import java.io.File;
import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import de.test.entities.Emp;

@RunWith(Arquillian.class)
public class DataSourceTest {

  @Deployment
  public static JavaArchive createDeployment() {
    JavaArchive jar = ShrinkWrap.create(JavaArchive.class);

    jar.addPackages(true, "de.test");
    jar.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    jar.addAsResource(new File("src/main/resources/META-INF/ejb-jar.xml"), "META-INF/ejb-jar.xml");
    jar.addAsResource(new File("src/main/resources/META-INF/persistence.xml"), "META-INF/persistence.xml");

    System.out.println(jar.toString(true));

    return jar;
  }

  @EJB
  EmpService testclass;

  @Test
  public void should_create_greeting() {
    Assert.assertEquals("Hello, Earthling!", testclass.createGreeting("Earthling"));
    testclass.greet(System.out, "Earthling");
  }

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  // @ApplyScriptBefore("prepare_test.sql")
  @UsingDataSet("empBefore.xml")
  @Test
  public void GetAllCustomers() {
    List<Emp> allEmps = testclass.getAllEmps();

    Assert.assertEquals(2, allEmps.size());
  }

  @UsingDataSet("empBefore.xml")
  @Test
  public void DeleteAllCustomers() {
    // thrown.expect(EJBException.class); // Äußere Exception
    // thrown.expectCause(CoreMatchers.isA(SQLGrammarException.class)); // Gekapselte Exception

    testclass.removeAllEmps();

  }

}

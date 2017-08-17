package de.test;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
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

  @Inject
  EmpService testclass;

  @Test
  public void should_create_greeting() {
    Assert.assertEquals("Hello, Earthling!", testclass.createGreeting("Earthling"));
    testclass.greet(System.out, "Earthling");
  }

  // @ApplyScriptBefore("prepare_test.sql")
  @UsingDataSet("empBefore.xml") // Ohne Dataset läuft es
  @Test
  public void GetAllCustomers() {
    List<Emp> allEmps = testclass.getAllEmps();

    // Standardmäßig sind 14 Datensätze in der DB
    // Durch das PrepareStatement sollten es mehr sein
    Assert.assertEquals(16, allEmps.size());
  }

  @Test
  public void DeleteAllCustomers() {
    // Wirft exception wegen unzurecihender Recht.Bin ur gerade zu doof das Beispiel zu finden wo wir die "inneren"
    // Exceptions ausgelesen haben
    testclass.removeAllEmps();

  }

}

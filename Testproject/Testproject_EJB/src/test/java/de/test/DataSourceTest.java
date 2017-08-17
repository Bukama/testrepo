package de.test;

import java.io.File;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

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

  /*
   * @Test public void GetAllCustomers() { List<Emp> allEmps = testclass.getAllEmps();
   *
   * Assert.assertEquals(14, allEmps.size()); }
   */
}

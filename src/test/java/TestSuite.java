import junit.framework.TestCase;
import managers.ClientManagerTest;
import managers.MovieManagerTest;
import model.AddressTest;
import model.ClientTest;
import model.MovieTest;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import repositories.AbstractRepository;

@RunWith(Suite.class)
@Suite.SuiteClasses({ClientManagerTest.class, MovieManagerTest.class, MovieTest.class, ClientTest.class, AddressTest.class})
public class TestSuite extends TestCase {
    @AfterClass
    public static void closeSession() {
        AbstractRepository.getSession().close();
    }
}

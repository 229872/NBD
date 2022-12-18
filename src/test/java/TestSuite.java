import junit.framework.TestCase;
import managers.ClientManagerTest;
import managers.MovieManagerTest;
import managers.TicketManagerTest;
import model.AddressTest;
import model.ClientTest;
import model.MovieTest;
import model.TicketTest;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import repositories.AbstractRepository;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClientManagerTest.class,
        MovieManagerTest.class,
        TicketManagerTest.class,
        MovieTest.class,
        ClientTest.class,
        AddressTest.class,
        TicketTest.class})
public class TestSuite extends TestCase {
    @AfterClass
    public static void closeSession() {
        AbstractRepository.getSession().close();
    }
}

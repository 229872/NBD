package consumers;

import org.junit.jupiter.api.Test;

public class TicketConsumerTest {

    @Test
    void ticketConsumerTest(){
        TicketConsumer ticketConsumer = new TicketConsumer();
        ticketConsumer.consume();
    }

}

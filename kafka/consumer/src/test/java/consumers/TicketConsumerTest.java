package consumers;

import org.junit.jupiter.api.Test;

public class TicketConsumerTest {

    @Test
    void ticketConsumerTest(){
        TicketConsumer ticketConsumer = new TicketConsumer();
        ticketConsumer.consume();
    }

    @Test
    void ticketDoubleConsumerTest() {
        TicketConsumer ticketConsumer = new TicketConsumer();
        ticketConsumer.consume();
        TicketConsumer ticketConsumer2 = new TicketConsumer();
        ticketConsumer2.consume();
    }

}

package consumers;

import com.google.gson.Gson;
import model.Ticket;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.serialization.StringDeserializer;
import repositories.TicketRepository;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class TicketConsumer {
    private TicketRepository ticketRepository = new TicketRepository();
    private final String CONSUMER_GROUP_NAME = "TicketConsumerGroup";
    private Properties properties;


    public TicketConsumer() {
        this.properties = new Properties();
        this.properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9192,kafka1:9292,kafka3:9392");
    }

    public void consume() {
        try(Admin admin = Admin.create(this.properties)) {
            admin.deleteConsumerGroups(List.of(CONSUMER_GROUP_NAME));
        }

//        printConsumerGroupInfo();

        Properties consumerConfig = new Properties();
        consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerConfig.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerConfig.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP_NAME);//dynamiczny przydział
        consumerConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka1:9192,kafka2:9292,kafka3:9392");

        try (KafkaConsumer consumer = new KafkaConsumer(consumerConfig)) {
            consumer.subscribe(List.of("tickets-topic"));

            printConsumerGroupInfo();

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, String> record : records) {
                    Ticket ticket = new Gson().fromJson(record.value(), Ticket.class);
                    System.out.println(ticket);
                    ticketRepository.add(ticket);
                    consumer.commitSync();
                }
            }
        }
    }

    private void printConsumerGroupInfo(){
        try(Admin admin = Admin.create(this.properties)){
            DescribeConsumerGroupsResult describeConsumerGroupsResult =
                    admin.describeConsumerGroups(List.of(CONSUMER_GROUP_NAME));
            Map<String, KafkaFuture<ConsumerGroupDescription>> describedGroups = describeConsumerGroupsResult.describedGroups();
            for (Future<ConsumerGroupDescription> group : describedGroups.values()) {
                ConsumerGroupDescription consumerGroupDescription = group.get();
                System.out.println(consumerGroupDescription);
                for(MemberDescription member : consumerGroupDescription.members()){
                    System.out.println(member);
                }
            }
        } catch (ExecutionException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

}


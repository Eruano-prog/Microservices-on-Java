package JTechLabs.Lab5.APIService.DLL;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    private String bootstrapServers="localhost:9092";

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic CatPutTopic() {
        return TopicBuilder
                .name("cat.put")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic CatSaveTopic() {
        return TopicBuilder
                .name("cat.save")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic CatDeleteTopic() {
        return TopicBuilder
                .name("cat.delete")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic HostPutTopic() {
        return TopicBuilder
                .name("host.put")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic HostSaveTopic() {
        return TopicBuilder
                .name("host.save")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic HostDeleteTopic() {
        return TopicBuilder
                .name("host.delete")
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic HostGetByColor() {
        return TopicBuilder
                .name("cat.getByColor")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
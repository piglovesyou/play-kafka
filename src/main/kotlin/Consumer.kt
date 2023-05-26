import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.time.Duration
import java.util.Properties

fun createConsumer(): Consumer<String, String> {
    val props = Properties()
    props.setProperty("bootstrap.servers", "localhost:29092")
    props.setProperty("group.id", "piglovesyou")
    props.setProperty("enable.auto.commit", "true")
    props.setProperty("auto.commit.interval.ms", "1000")
    props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    return KafkaConsumer(props)
}

fun Consumer<String, String>.consumeMessages(topic: String) {
    subscribe(listOf(topic))
    while (true) {
        val messages: ConsumerRecords<String, String> = poll(Duration.ofMillis(5000))
        if (!messages.isEmpty) {
            for (message: ConsumerRecord<String, String> in messages) {
                println("Consumer reading message: ${message.value()}")
            }
            commitAsync { offsets, exception ->
                for ((partition, metadata) in offsets) {
                    println("Committed offset for topic: ${partition.topic()}, partition: ${partition.partition()}, offset: ${metadata.offset()}")
                }
            }
        } else {
            println("No messages to read and poll timeout reached")
        }
    }
}
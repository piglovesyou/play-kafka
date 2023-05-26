import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerRecord
import java.time.LocalDateTime
import java.util.Properties

fun createProducer(): Producer<String, String> {
    val props = Properties()

    props["bootstrap.servers"] = "localhost:29092"
    props["acks"] = "all"
    props["retries"] = 0
    props["linger.ms"] = 1
    props["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
    props["value.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"

    return KafkaProducer(props)
}

fun Producer<String, String>.produceMessages(topic: String) {
    val time = LocalDateTime.now()
    val message = ProducerRecord(
        topic, // topic
        time.toString(), // key
        "Message sent at ${LocalDateTime.now()}" // value
    )
    println("Producer sending message: $message")
    this@produceMessages.send(message)
}

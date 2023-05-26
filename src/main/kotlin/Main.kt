import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands

class MainCommand : CliktCommand() {
    override fun run() {}
}

class ProduceCommand : CliktCommand() {
    override fun run() {
        val producer = createProducer()
        producer.produceMessages("my-topic")
    }
}

class ConsumeCommand : CliktCommand() {
    override fun run() {
        createConsumer().consumeMessages("my-topic")
    }
}

fun main(args: Array<String>) = MainCommand()
    .subcommands(
        ProduceCommand(),
        ConsumeCommand(),
    )
    .main(args)
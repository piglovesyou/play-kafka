import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.subcommands

class MainCommand : CliktCommand() {
    override fun run() {}
}

class ProvideCommand : CliktCommand() {
    override fun run() {
        println("provide")
    }
}

class ConsumeCommand : CliktCommand() {
    override fun run() {
        println("consume")
    }
}

fun main(args: Array<String>) = MainCommand()
    .subcommands(
        ProvideCommand(),
        ConsumeCommand(),
    )
    .main(args)
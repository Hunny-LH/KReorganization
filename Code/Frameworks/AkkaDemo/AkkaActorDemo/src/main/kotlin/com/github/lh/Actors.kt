package com.github.lh

import akka.actor.AbstractActor
import akka.actor.ActorRef
import akka.actor.Props
import akka.event.Logging
import akka.event.LoggingAdapter
import akka.japi.pf.ReceiveBuilder

/**
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 17-12-27
 */
class Greeter(
        val printerActor: ActorRef,
        val message: String
) : AbstractActor() {

    lateinit var greeting: String

    override fun createReceive(): Receive = ReceiveBuilder()
            .match(WhoToGreet::class.java) {
                greeting = "$message , ${it.who}"
            }
            .match(Greet::class.java) {
                printerActor.tell(Greeting(greeting), self)
            }
            .build()

    companion object {
        fun props(printerActor: ActorRef, message: String): Props =
                Props.create(Greeter::class.java) {
                    Greeter(printerActor = printerActor, message = message)
                }
    }
}

class Printer : AbstractActor() {

    private val log: LoggingAdapter = Logging.getLogger(context.system, this)

    override fun createReceive(): Receive = ReceiveBuilder()
            .match(Greeting::class.java) {
                log.info(it.message)
            }
            .build()

    companion object {
        fun props(): Props =
                Props.create(Printer::class.java) {
                    Printer()
                }
    }
}

data class WhoToGreet(
        val who: String
)

class Greet

data class Greeting(
        val message: String
)
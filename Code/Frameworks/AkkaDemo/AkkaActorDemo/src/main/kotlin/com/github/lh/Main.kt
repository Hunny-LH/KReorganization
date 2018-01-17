package com.github.lh

import akka.actor.ActorRef
import akka.actor.ActorSystem


/**
 * @author <a href="mailto:393803588@qq.com">HanL(liuhan3)</a>
 * @date 17-12-27
 */
fun main(args: Array<String>) {
    ActorSystem.create("helloakk").apply {
        try {
            val printerActor = actorOf(Printer.props(), "printerActor")
            val howdyGreeter = actorOf(Greeter.props(
                    printerActor = printerActor,
                    message = "Howdy"
            ), "howdyGreeter")
            val helloGreeter = actorOf(Greeter.props(
                    printerActor = printerActor,
                    message = "Hello"
            ), "helloGreeter")
            val goodDayGreeter = actorOf(Greeter.props(
                    printerActor = printerActor,
                    message = "Good Day"
            ), "goodDayGreeter")

            howdyGreeter.tell(WhoToGreet("akka"), ActorRef.noSender())
            howdyGreeter.tell(Greet(), ActorRef.noSender())

            howdyGreeter.tell(WhoToGreet("Lightbend"), ActorRef.noSender())
            howdyGreeter.tell(Greet(), ActorRef.noSender())

            helloGreeter.tell(WhoToGreet("Java"), ActorRef.noSender())
            helloGreeter.tell(Greet(), ActorRef.noSender())

            goodDayGreeter.tell(WhoToGreet("Play"), ActorRef.noSender())
            goodDayGreeter.tell(Greet(), ActorRef.noSender())

            println(">>> Press ENTER to exit <<<")
            readLine()
        } catch (e: Exception) {
        } finally {
            println(">>> Terminate <<<")
            terminate()
        }
    }
}
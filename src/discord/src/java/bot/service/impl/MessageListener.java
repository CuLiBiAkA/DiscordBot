package bot.service.impl;

import bot.service.customLogic.ProcessorsMassage;
import discord4j.core.object.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

public abstract class MessageListener {

    @Autowired
    ProcessorsMassage processorsMassage;


    public Mono<Void> processCommand(Message eventMessage) {
        return Mono.just(eventMessage)
//                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false)) //проверяет что это пишет не бот
                .map(message -> processorsMassage.getProcessor(message)) // Каждое смс прогоняет через мой обработчик
                .filter(message -> processorsMassage.stringProcessor()!=null) // Проверяет если команды не валидны
                .flatMap(Message::getChannel) // берет все каналы дискорд
                .flatMap(channel -> (channel.createMessage(processorsMassage.stringProcessor()))) // если все пиздато до этого было выдает сообщение исходя из входящих данных в месенжере
                .then();
    }
}
package bot.service.impl;

import bot.service.interfaces.EventListener;
import discord4j.core.event.domain.message.ReactionAddEvent;
import reactor.core.publisher.Mono;

public class ReactionAddListener implements EventListener<ReactionAddEvent> {
    @Override
    public Class<ReactionAddEvent> getEventType() {
        return ReactionAddEvent.class;
    }

    @Override
    public Mono<Void> execute(ReactionAddEvent event) {
        return Mono.just(event).then();
    }
}

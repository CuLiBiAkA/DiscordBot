package bot.service.impl;

import bot.service.interfaces.EventListener;
import discord4j.core.event.domain.VoiceStateUpdateEvent;
import reactor.core.publisher.Mono;

public class VoiceStateUpdateListener implements EventListener<VoiceStateUpdateEvent> {
    @Override
    public Class<VoiceStateUpdateEvent> getEventType() {
        return VoiceStateUpdateEvent.class;
    }

    @Override
    public Mono<Void> execute(VoiceStateUpdateEvent event) {
        return Mono.just(event).then();
    }
}

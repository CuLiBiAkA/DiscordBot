package bot.service.impl;

import bot.service.interfaces.EventListener;
import discord4j.core.event.domain.VoiceStateUpdateEvent;
import discord4j.core.object.entity.channel.VoiceChannel;
import discord4j.voice.VoiceConnection;
import reactor.core.publisher.Mono;

public class VoiceStateUpdateListener implements EventListener<VoiceStateUpdateEvent> {
    @Override
    public Class<VoiceStateUpdateEvent> getEventType() {
        return VoiceStateUpdateEvent.class;
    }

    @Override
    public Mono<Void> execute(VoiceStateUpdateEvent event) {
        return Mono.just(event)
                .flatMap(voiceStateUpdateEvent -> voiceStateUpdateEvent.getCurrent().getChannel())
                .flatMap(VoiceChannel::getVoiceConnection)
                .then();
    }
}

package bot.config;



import bot.service.interfaces.EventListener;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.object.presence.Activity;
import discord4j.core.object.presence.ClientActivity;
import discord4j.core.object.presence.ClientPresence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.util.Logger;
import reactor.util.Loggers;

import java.util.List;

@Configuration
public class BotConfiguration {
    private String token =

    @Bean
    public GatewayDiscordClient gatewayDiscordClient() {
        return DiscordClientBuilder.create(token)
                .build()
                .gateway()
                .setInitialPresence(s->ClientPresence.doNotDisturb(ClientActivity.of(Activity.Type.STREAMING, "КАК ЕБУТ ТВОЮ МАМУ","https://www.youtube.com/watch?v=rPXZxAMEdn4")))
                .login()
                .block();
    }


    @Bean
    public <T extends Event> GatewayDiscordClient gatewayDiscordClient(List<EventListener<T>> eventListeners) {
        GatewayDiscordClient client = DiscordClientBuilder.create(token)
                .build()
                .gateway()
                .setInitialPresence(s->ClientPresence.doNotDisturb(ClientActivity.of(Activity.Type.STREAMING, "КАК ЕБУТ ТВОЮ МАМУ","https://www.youtube.com/watch?v=rPXZxAMEdn4")))
                .login()
                .block();

        for (EventListener<T> listener : eventListeners) {
            client.on(listener.getEventType())
                    .flatMap(listener::execute)
                    .onErrorResume(listener::handleError)
                    .subscribe();
        }
        return client;
    }
}
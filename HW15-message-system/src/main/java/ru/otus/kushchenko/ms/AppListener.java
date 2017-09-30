package ru.otus.kushchenko.ms;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.otus.kushchenko.ms.message_system.MessageSystem;

@Component
public class AppListener {

    @EventListener({ContextRefreshedEvent.class})
    public void handleContextRefresh(ContextRefreshedEvent event) {
        MessageSystem messageSystem = event.getApplicationContext().getBean(MessageSystem.class);
        messageSystem.start();
    }
}

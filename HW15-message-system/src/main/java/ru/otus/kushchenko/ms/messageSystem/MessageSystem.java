package ru.otus.kushchenko.ms.messageSystem;

import ru.otus.kushchenko.ms.messageSystem.addressee.Addressee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class MessageSystem {
    private static final int DEFAULT_STEP_TIME = 10;
    private final Map<Address, ConcurrentLinkedQueue<Message>> messageMap = new HashMap<>();
    private final List<Addressee> addresseeList = new ArrayList<>();


    public void addAddressee(Addressee addressee) {
        addresseeList.add(addressee);
        messageMap.put(addressee.getAddress(), new ConcurrentLinkedQueue<>());
    }

    public void sendMessage(Message message) {
        messageMap.get(message.getTo()).add(message);
    }


    @SuppressWarnings("InfiniteLoopStatement")
    public void start() {
        for (Addressee addressee : addresseeList) {
            new Thread(() -> {
                while (true) {
                    ConcurrentLinkedQueue<Message> queue = messageMap.get(addressee.getAddress());

                    while (!queue.isEmpty()) {
                        Message message = queue.poll();
                        message.exec(addressee);
                    }

                    try {
                        Thread.sleep(MessageSystem.DEFAULT_STEP_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

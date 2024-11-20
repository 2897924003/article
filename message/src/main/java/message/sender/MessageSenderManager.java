package message.sender;

import org.springframework.stereotype.Service;

@Service
public class MessageSenderManager {
    private final MessageSenderRegistry messageSenderRegistry;
    public MessageSenderManager(MessageSenderRegistry messageSenderRegistry) {
        this.messageSenderRegistry = messageSenderRegistry;
    }


}

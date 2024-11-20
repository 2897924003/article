package message.sender;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MessageSenderRegistry implements MessageSenderSelector {

    private final Map<String, MessageSender> senderMap;

    public MessageSenderRegistry(List<MessageSender> senders) {
        this.senderMap = senders.stream()
                .collect(Collectors.toMap(MessageSender::getType, sender -> sender));
    }

    @Override
    public MessageSender selectSender(String type) {
        MessageSender sender = senderMap.get(type);
        if (sender == null) {
            throw new UnsupportedOperationException("Unsupported message type: " + type);
        }
        return sender;
    }
}
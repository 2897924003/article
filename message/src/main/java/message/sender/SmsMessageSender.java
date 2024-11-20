package message.sender;


import message.domain.Message;
import org.springframework.web.client.RestClient;


public class SmsMessageSender implements MessageSender {

    private final RestClient restClient = RestClient.create();

    @Override
    public void sendMessage(Message message) {

    }
}

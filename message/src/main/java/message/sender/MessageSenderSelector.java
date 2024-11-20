package message.sender;

public interface MessageSenderSelector {
    MessageSender selectSender(String type);
}

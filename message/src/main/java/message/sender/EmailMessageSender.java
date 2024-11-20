package message.sender;

import message.domain.Message;
import message.domain.MessageContent;
import message.domain.MessageTransmissionInfo;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class EmailMessageSender implements MessageSender {

    private final JavaMailSender mailSender;

    public EmailMessageSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * 通过邮箱发送消息
     * @param message
     */
    @Override
    public void sendMessage(Message message) {

        MessageContent messageContent = message.getMessageContent();
        MessageTransmissionInfo messageTransmissionInfo = message.getMessageTransmissionInfo();
        mailSender.send(mimeMessage ->
        {
            mimeMessage.setFrom(messageTransmissionInfo.getFrom());
            mimeMessage.setRecipients(jakarta.mail.Message.RecipientType.TO,messageTransmissionInfo.getTo());
            mimeMessage.setSubject(messageContent.getSubject());
            mimeMessage.setText(messageContent.getMessage());
        }
        );
    }
}

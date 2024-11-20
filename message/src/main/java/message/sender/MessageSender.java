package message.sender;


import message.domain.Message;

public interface MessageSender {
    void sendMessage(Message message);
   default String getType(){
       return "message";
   }
}

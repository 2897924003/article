package message.code_service.service;


import message.domain.MessageBusiness;
import message.code_service.domain.Code;

public interface CodeService extends MessageBusiness {
    String type();
    boolean support(Code code);
    void sendCode(Code code);
    boolean verify(Code code);
}

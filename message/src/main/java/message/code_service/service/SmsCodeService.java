package message.code_service.service;


import message.code_service.domain.Code;

public class SmsCodeService implements CodeService {


    @Override
    public String type() {
        return "";
    }

    @Override
    public boolean support(Code code) {
        return false;
    }

    @Override
    public void sendCode(Code code) {

    }

    @Override
    public boolean verify(Code code) {
        return false;
    }
}

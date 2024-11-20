package message.code_service.service;

import message.code_service.VerificationType;
import message.code_service.domain.Code;
import message.sender.EmailMessageSender;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
public class EmailCodeService implements CodeService {
    public final String type = VerificationType.Email.name();
    private final EmailMessageSender emailMessageSender;
    private final StringRedisTemplate redisTemplate;

    public EmailCodeService(EmailMessageSender emailMessageSender, StringRedisTemplate redisTemplate) {
        this.emailMessageSender = emailMessageSender;
        this.redisTemplate = redisTemplate;
    }

    /**
     *调用消息服务，发送验证码到用户邮箱。同时调用将code存入redis。
     * @param code
     */
    @Override
    public void sendCode(Code code) {
        redisTemplate.opsForValue().set(code.getKey(),code.getCode(),5, TimeUnit.MINUTES);
        emailMessageSender.sendMessage(code);
    }


    @Override
    public String type() {
        return type;
    }

    /**
     * 判断该code是否被该服务支持
     * @param code 验证码
     * @return true if support
     */
    @Override
    public boolean support(Code code) {
        return code.getType().equals("Email");
    }


    /**
     * 校验验证码，默认实现是从redis中获取真实验证码
     * @param code
     * @return
     */
    @Override
    public boolean verify(Code code) {
        if (!support(code)){
            return false;
        }
        code.generateKey();
        Object o = redisTemplate.opsForValue().get(code.getKey());

        return code.getCode().equals(o);
    }
}

package message.inbound.messaging;
import com.fasterxml.jackson.databind.ObjectMapper;
import message.code_service.domain.Code;
import message.code_service.service.CodeManager;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class CodeMessageListener {

    private final CodeManager codeManager;

    public CodeMessageListener(CodeManager codeManager) {
        this.codeManager = codeManager;
    }

    // 监听发送验证码命令的队列
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "send_code_queue", durable = "true"),
            exchange = @Exchange(value = "code", type = ExchangeTypes.TOPIC),
            key = "send.code"
    ))
    public void receiveSendCodeCO(@Payload String json) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Code code = objectMapper.readValue(json, Code.class);

            codeManager.sendCode(code);
        } catch (Exception e) {
            System.out.println("=======================================解析出错" + json);
        }

    }
    // 监听验证验证码命令的队列
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "verify_code_queue", durable = "true"),
            exchange = @Exchange(value = "code", type = ExchangeTypes.TOPIC),
            key = "verify.code"
    ))
    public String receiveVerifyCodeCO(@Payload String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Code code = objectMapper.readValue(json, Code.class);
            String s = codeManager.verifyCode(code) ? "true" : "false";
            System.out.println(s);
            return s;
        }catch (Exception e) {

            e.printStackTrace();

        }
        return "false";
    }
}

package message.inbound.rest;

import message.code_service.domain.Code;
import message.code_service.service.CodeManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/code")
public class CodeController {

    private final CodeManager codeManager;

    public CodeController(CodeManager codeManager) {
        this.codeManager = codeManager;
    }

    // 发送验证码的 REST API
    @PostMapping("/send")
    public void sendCode(@RequestBody Code code) {
        codeManager.sendCode(code);
    }

    // 验证验证码的 REST API
    @PostMapping("/verify")
    public boolean verifyCode(@RequestBody Code code) {
        return codeManager.verifyCode(code);
    }
}
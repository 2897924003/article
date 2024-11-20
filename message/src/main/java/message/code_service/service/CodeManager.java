package message.code_service.service;

import message.code_service.CodeGenerator;
import message.code_service.domain.Code;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CodeManager {
    private final Map<String, CodeService> codeServiceMap;

    private CodeGenerator codeGenerator;
    /**
     * 初始化验证码发送策略图-引用注入
     * @param codeServices
     */
    public CodeManager(List<CodeService> codeServices) {
        this.codeServiceMap = codeServices.stream().collect(Collectors.toMap(CodeService::type, codeService -> codeService));
    }

    /**
     * 发送验证码
     */
    public void sendCode(Code code) {
        code.setCode(CodeGenerator.generateVerificationCode(6));
        code.writeMessageContentAndTransmissionInfo();
        code.generateKey();

        CodeService codeService = matchCodeService(code);
        codeService.sendCode(code);
    }

    /**
     * 验证验证码
     */
    public boolean verifyCode(Code code) {
       CodeService codeService = matchCodeService(code);
       code.writeMessageContentAndTransmissionInfo();
        return codeService.verify(code);
    }

    /**
     * 动态获取验证码发送策略-键值匹配
     * @return CodeService 对应发送策略
     */
    private CodeService matchCodeService(Code code){
        // 从 Map 中快速找到支持的 CodeService
        CodeService codeService = codeServiceMap.get(code.getType());
        if (codeService == null) {
            throw new UnsupportedOperationException("No CodeService found to support the verification type: " + code.getType());
        }
        return codeService;
    }


}

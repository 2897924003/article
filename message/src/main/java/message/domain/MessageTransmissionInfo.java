package message.domain;

import lombok.Data;

@Data
public class MessageTransmissionInfo {
    private String from = "2897924003@qq.com";//发送人
    private String to;//接收人
}

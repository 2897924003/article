package article.atcl.auth.outbound;


import feign.Body;
import feign.RequestLine;

/**
 * 基于http协议实现的RESTFul风格的RPC远程过程调用
 */
public interface AuthorizeServiceClient {
    @RequestLine("POST /authorize")
    @Body("%7B\"token\":\"%s\"%7D") // 使用 %s 占位符来传递 accessToken
    String authorize(String accessToken);

    @RequestLine("POST /authorize/actorid")
    @Body("%7B\"token\":\"%s\"%7D") // 使用 %s 占位符来传递 accessToken
    String bauthorize(String accessToken);
}

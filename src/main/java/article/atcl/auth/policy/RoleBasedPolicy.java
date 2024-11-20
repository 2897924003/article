package article.atcl.auth.policy;


import article.atcl.auth.outbound.AuthorizeServiceClient;
import com.auth0.jwt.JWT;
import com.auth0.jwt.impl.JWTParser;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import org.springframework.stereotype.Service;

@Service
public class RoleBasedPolicy implements AuthorizePolicy {
    AuthorizeServiceClient feign = Feign.builder()
            .target(AuthorizeServiceClient.class, "http://localhost:8080");

    @Override
    public boolean authorize(String accessToken) {

        return Boolean.parseBoolean(feign.authorize(accessToken));
    }

    @Override
    public long authorize(String accessToken, Boolean needActorId) {
        String bauthorize = feign.bauthorize(accessToken);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Long l = objectMapper.readValue(bauthorize, Long.class);
            return l;
        }catch (Exception e){}
        return 1L;
    }
}

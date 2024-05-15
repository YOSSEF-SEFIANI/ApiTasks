package net.youss.fontendreactclient.config.converter;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.keycloak")
@Getter
@Setter
@ToString
public class keycloakProprities {

    private String authServerUrl;
    private String realm;
    private String resource;
    private String principalAttribute;
    private Credentials credentials;

    @Getter @Setter @ToString
    public static class Credentials {
        private String secret;
    }
}
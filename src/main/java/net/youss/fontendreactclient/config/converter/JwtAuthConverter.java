package net.youss.fontendreactclient.config.converter;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private static final String ROLE_PREFIX = "ROLE_";
    private static final String REALM_ACCESS ="realm_access";
    private static final String ROLES ="roles";
    private final keycloakProprities keycloakProprities;

    private final JwtGrantedAuthoritiesConverter
            jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();


    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        Collection<GrantedAuthority> authorities = Stream.concat(jwtGrantedAuthoritiesConverter.convert(source).stream(),
                extractResourceRoles(source)
        ).collect(Collectors.toSet());
        String name = Optional.ofNullable(source.getClaimAsString(
                keycloakProprities.getPrincipalAttribute())).orElse(source.getSubject());
        return new JwtAuthenticationToken(source, authorities, name);
    }

    private Stream<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
        return Optional.ofNullable(jwt.getClaimAsMap(REALM_ACCESS))
                .map(ra -> (Collection<String>) ra.get(ROLES)).stream().flatMap(roles ->
                        roles.stream().map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role)));
    }

}
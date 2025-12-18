package asembly.auth_service.security;

import asembly.auth_service.client.UserClient;
import asembly.type.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class CustomUserDetailsService  implements UserDetailsService {

    @Autowired
    private UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = userClient.getUserByUsername(username).getBody();

        log.info("User object: {}",user);

        return new User(
                user.username(),
                user.password(),
                getAuthorities(user.roles())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles)
    {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role: roles)
        {
            log.info("Role getName: {}", role.name());
            authorities.add(new SimpleGrantedAuthority(role.name()));
        }
        log.info("Roles: {}", authorities);
        return authorities;
    }

}
package etf.nwt.authorizationserver;

import etf.nwt.authorizationserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        etf.nwt.authorizationserver.model.User user = userRepository.findByUsername(username);
        if (user != null) {
            List<OAuth2UserAuthority> authorities = Arrays.stream(user.getRoles().split(","))
                    .map(role -> new OAuth2UserAuthority(role, Collections.singletonMap(
                            "attribute", "value"
                    )))
                    .collect(Collectors.toList());
            return new User(
                    username,
                    passwordEncoder.encode(user.getPassword()),
                    authorities
            );
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}


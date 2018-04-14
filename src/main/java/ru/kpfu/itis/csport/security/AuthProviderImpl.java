package ru.kpfu.itis.csport.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.csport.service.UserService;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    private final UserService userService;

    @Autowired
    public AuthProviderImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        UserDetails userDetails = userService.getUser(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found");
        }

        String password = authentication.getCredentials().toString();
        if (!encoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

}

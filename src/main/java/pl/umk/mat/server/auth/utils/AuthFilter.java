package pl.umk.mat.server.auth.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.umk.mat.server.auth.ApiLoginEntries;
import pl.umk.mat.server.auth.ApiLoginEntriesRepository;
import pl.umk.mat.server.utils.exceptions.TokenExpired;
import pl.umk.mat.server.utils.exceptions.UserAuth;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class AuthFilter extends OncePerRequestFilter {
    private UserDetailsServiceImpl userDetailsService;
    private ApiLoginEntriesRepository apiLoginEntriesRepository;

    public AuthFilter(UserDetailsServiceImpl userDetailsService, ApiLoginEntriesRepository apiLoginEntriesRepository) {
        this.userDetailsService = userDetailsService;
        this.apiLoginEntriesRepository = apiLoginEntriesRepository;
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        return request.getHeader("Token");
    }

    @Override
    @Transactional
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = getTokenFromRequest(request);

        if (token != null) {
            ApiLoginEntries apiLogin = apiLoginEntriesRepository.findByToken(token);
            try {
                if (apiLogin.getExpiredAt().isBefore(Instant.now())) {
                    throw new TokenExpired();
                }
                UserDetails userDetails = userDetailsService.loadUserByUsername(apiLogin.getUser().getEmail());

                SecurityContextHolder.getContext().setAuthentication(new TokenBasedAuthentication(userDetails, token));
            } catch (UsernameNotFoundException e) {
                throw new UserAuth(e.getMessage());
            }
            apiLogin.setExpiredAt(Instant.now().plus(1, ChronoUnit.DAYS));
        }
        filterChain.doFilter(request, response);
    }

}

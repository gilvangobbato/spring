package com.github.gilvangobbato.security;

import com.github.gilvangobbato.domain.entity.User;
import com.github.gilvangobbato.service.imp.UserServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    public final JWtService jWtService;
    public final UserServiceImpl userService;

    public JwtAuthFilter(JWtService jWtService, UserServiceImpl userService) {
        this.jWtService = jWtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader("Authorization");
        if (header != null && !header.isEmpty() && header.startsWith("Bearer")) {
            String token = header.split(" ")[1];
            if (jWtService.isTokenValid(token)) {
                UserDetails user = userService.loadUserByUsername(jWtService.getUsername(token));
                UsernamePasswordAuthenticationToken upt = new UsernamePasswordAuthenticationToken(
                        jWtService.getUsername(token)
                        , null
                        , user.getAuthorities());
                upt.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(upt);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

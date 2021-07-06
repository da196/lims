package tz.go.tcra.lims.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import tz.go.tcra.licensing.exception.AuthException;
import tz.go.tcra.licensing.service.implementation.UserDetailsServiceImpl;
import tz.go.tcra.licensing.utility.JwtUtility;

@Component
public class JwtConfig extends OncePerRequestFilter {

    @Autowired
    private JwtUtility jwtUtil;
    
    @Autowired
    private UserDetailsServiceImpl service;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try{
        
            String authorizationHeader = httpServletRequest.getHeader("Authorization");

            String token = null;
            String userName = null;

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                
                token = authorizationHeader.substring(7);
                userName = jwtUtil.extractUsername(token);
            }
//            else{
//                
//                throw new AuthException("Unauthorized Access");
//            }

            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = service.loadUserByUsername(userName);
                
                if (!jwtUtil.validateToken(token, userDetails)) {
                   
                    throw new AuthException("Unauthorized Access");
                }
                
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
//            else{
//                
//                throw new AuthException("Unauthorized Access");
//            }
            
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }catch(AuthException e){
        
            throw new AuthException(e.getMessage());
        }
    }
}

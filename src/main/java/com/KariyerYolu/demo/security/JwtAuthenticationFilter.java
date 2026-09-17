package com.KariyerYolu.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
// OncePerRequestFilter: Bize gelen HER istekte bu sınıf 1 kere çalışır. (Gerçek güvenlik görevlisi)
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // 1. Gelen isteğin başlığında (Header) "Authorization" yazısı var mı?
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

         if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

         jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);  

        //   Email boş değilse ve adam henüz sistemde yetkilendirilmemişse
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            
            
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            //   hala geçerli mi? Sahte değil mi?
            if (jwtService.isTokenValid(jwt, userDetails)) {
                
                //   onayladık. Sisteme "Bu adama güvenebilirsin, kimliğini doğruladım" diyoruz.
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                //   (Giriş yaptı olarak kaydet)
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        
        //  
        filterChain.doFilter(request, response);
    }
}
package com.makarenko.tasktool.security;

import static com.makarenko.tasktool.security.SecurityConstants.HEADER_STRING;
import static com.makarenko.tasktool.security.SecurityConstants.TOKEN_PREFIX;

import com.makarenko.tasktool.domain.User;
import com.makarenko.tasktool.services.impl.CustomUserDetailService;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private JwtTokenProvider jwtTokenProvider;
  private CustomUserDetailService customUserDetailService;

  @Autowired
  public void setJwtTokenProvider(JwtTokenProvider jwtTokenProvider) {
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Autowired
  public void setCustomUserDetailService(
      CustomUserDetailService customUserDetailService) {
    this.customUserDetailService = customUserDetailService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    try {
      String jwt = getJwtFromRequest(httpServletRequest);
      if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
        Long userId = jwtTokenProvider.getUserIdFromJwt(jwt);
        User userById = customUserDetailService.loadUserById(userId);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            userById, null, Collections.emptyList()
        );
        authentication
            .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (Exception e) {
      logger.error("Could not set user authentication in security context", e);
    }
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }

  private String getJwtFromRequest(HttpServletRequest httpServletRequest) {
    String header = httpServletRequest.getHeader(HEADER_STRING);
    if (StringUtils.hasText(header) && header.startsWith(TOKEN_PREFIX)) {
      return header.substring(7);
    }
    return null;
  }
}

package com.example.SpringRestAPI.aop;

import com.example.SpringRestAPI.domain.User;
import com.example.SpringRestAPI.exceptions.VerificationException;
import com.example.SpringRestAPI.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;


@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class VerificationAspect {

    private final UserService userService;

    @Before("@annotation(AuthorVerification)")
    public void beforeUpdate(JoinPoint joinPoint) {
        log.info("Before execution of: {}", joinPoint.getSignature().getName());

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        String userId = request.getRequestURI().split("/api/user/")[1];

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            String username = userDetails.getUsername();
            userVerify(username, userId);
        }
        log.info("Authenticated user: {}", principal.toString());
    }

    private void userVerify(String username, String userId){
        User user = userService.findByUsername(username);
        Long userRequestID = Long.parseLong(userId);

        if (user.hasOnlyRoleUser() && !Objects.equals(user.getId(), userRequestID)){
            throw new VerificationException("Вы можете получить только свои данные пользователя.");
        }
    }
}

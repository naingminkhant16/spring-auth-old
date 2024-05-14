package com.moe.exception_handling.demo.middleware;

import com.moe.exception_handling.demo.entity.Token;
import com.moe.exception_handling.demo.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class RequestInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

    private final TokenRepository tokenRepository;

    public RequestInterceptor(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //get token from request header
        String token = request
                .getHeader("Authorization");//Bearer Token : Bearer 099c0844-8f82-4848-9433-63634d03e6c9
        logger.info("Bearer Token : " + token);

        return processAuthentication(token, response);
    }

    private boolean processAuthentication(String token, HttpServletResponse response) throws Exception {
        if (token == null) {//No Token in request header
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "AUTHENTICATION FAILED!"
            );
            return false;

        } else {//Token exists in request header

            //find the provided token in Token table
            Token tokenExist = tokenRepository
                    .findByToken(
                            token.replaceFirst("Bearer ", "")//replace "Bearer " with ""
                    );

            if (tokenExist != null) {// token exists in Token table
                logger.info("Authentication Success.");
                return true;

            } else {//provided token doesn't exist Token table
                //send auth fail response
                logger.info("Authentication Failed!");
                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "AUTHENTICATION FAILED!"
                );
                return false;

            }
        }
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("In postHandle method!");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}

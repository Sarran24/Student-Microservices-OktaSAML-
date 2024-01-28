package com.samlOkta.config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggingFilter extends OncePerRequestFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);
	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterchain) throws IOException, ServletException {
		
		ContentCachingRequestWrapper cachingRequestWrapper = new ContentCachingRequestWrapper(request);
		ContentCachingResponseWrapper cachingResponseWrapper = new ContentCachingResponseWrapper(response);
		
		long startTime = System.currentTimeMillis();
		
		filterchain.doFilter(cachingRequestWrapper, cachingResponseWrapper);
		
		long timeTaken = System.currentTimeMillis() - startTime;
		
		String requestBody = getStringValue(cachingRequestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
		String responseBody = getStringValue(cachingRequestWrapper.getContentAsByteArray(), request.getCharacterEncoding());
		
		logger.info("filter logs : METHOD = {}; REQUESTURI = {}; REQUEST BODY = {}; RESPONSE CODE = {}; RESPONSE BODY = {}; TIME TAKEN = {}",
				request.getMethod(),request.getRequestURI(), requestBody, response.getStatus(),responseBody, timeTaken);
		
		cachingResponseWrapper.copyBodyToResponse();
	}

	private String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
		try {
			return new String(contentAsByteArray, 0, contentAsByteArray.length, characterEncoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	

}

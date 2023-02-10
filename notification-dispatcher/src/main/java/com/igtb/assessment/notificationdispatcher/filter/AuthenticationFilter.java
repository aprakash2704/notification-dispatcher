package com.igtb.assessment.notificationdispatcher.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

/**
 * The Class AuthenticationFilter - authenticate the username and password from Authorization header in request.
 */
@Component
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final String credentials = httpRequest.getHeader("Authorization");
        if (credentials == null || !credentials.startsWith("Basic")) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header is missing or invalid");
            return;
        }
        final String encodedCredentials = credentials.substring("Basic".length()).trim();
        final byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
        final String decodedCredentials = new String(decodedBytes, StandardCharsets.UTF_8);
        final String[] split = decodedCredentials.split(":");
        final String username = split[0];
        final String password = split[1];
        // Perform authentication using the username and password
        if (!authenticate(username, password)) {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Credentials. Please check username or password");
            return;
        }
        chain.doFilter(request, response);
    }

    /**
	 * Authenticate the username and password.
	 *
	 * @param username
	 *                     the username
	 * @param password
	 *                     the password
	 * @return true, if successful
	 */
    private boolean authenticate(final String username, final String password) {
        // this just validates against below hard-coded credentials 
    	// in real-time this could be different and validation can against the integrated security system
    	if("iGTBUser".equals(username) && "iGTBPassword".equals(password)) {
    		return true;
    	}
        return false;
    }

    /**
	 * Inits the.
	 *
	 * @param filterConfig
	 *                         the filter config
	 * @throws ServletException
	 *                              the servlet exception
	 */
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        // No initialization required
    }

    /**
	 * Destroy.
	 */
    @Override
    public void destroy() {
        // No cleanup required
    }
}
package org.sid.sec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
response.addHeader("Access-Control-Allow-Origin", "*");
response.addHeader("Access-Control-Allow-Headers", "Origin, Accpet, X-Requested-With,ContentType, Access-Control-Request-Method, Access-Control-Request-Headers, authorization");
response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Orgin,Access-control-Allow-Credentials, authorization");
if(request.getMethod().equals("OPTIONS")) {
	response.setStatus(HttpServletResponse.SC_OK);
}

else {

		
		
		
		
		String jwtToken= request.getHeader(SecurityParams.HEADER_NAME);
		System.out.println("Token="+jwtToken);
		if(jwtToken==null || !jwtToken.startsWith(SecurityParams.HEADER_PREFIX))
		{
			filterChain.doFilter(request, response);
			return;
		}
		JWTVerifier verifier= JWT.require(Algorithm.HMAC256(SecurityParams.SECRET)).build();
		DecodedJWT decodedJWT=verifier.verify(jwtToken.substring(SecurityParams.HEADER_PREFIX.length()));
		String username=decodedJWT.getSubject();
		List<String> roles=decodedJWT.getClaims().get("roles").asList(String.class);
		Collection<GrantedAuthority> authorities= new ArrayList<>();
		roles.forEach(rn->{
			authorities.add(new SimpleGrantedAuthority(rn));
		});
		UsernamePasswordAuthenticationToken user=
				new UsernamePasswordAuthenticationToken(username,null,authorities);
			SecurityContextHolder.getContext().setAuthentication(user);
			filterChain.doFilter(request, response);
	}}

	

	

}

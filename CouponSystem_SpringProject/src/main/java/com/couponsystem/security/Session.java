package com.couponsystem.security;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Session {

	// fields
	public final String token; // identifier of a session
	private Map<String, Object> attributes = new HashMap<>(); // session state
	private long lastAccessed; // needed for managing session expiration
	@Value("${session.max.inactive.interval:2000}") // injection happens after CTOR
	private long maxInactiveInterval; // in milliseconds - needed for managing session expiration
	private static final int TOKEN_MAX_LENGTH = 15;
	
	// Initialiser (runs before CTOR) - first step after object creation
	{
		this.token = UUID.randomUUID().toString().replace("-", "").substring(0, TOKEN_MAX_LENGTH);
		resetLastAccessed();
	}

	@PostConstruct // runs after CTOR
	private void init() {
		// fix the time units - for convenience
		maxInactiveInterval = TimeUnit.MINUTES.toMillis(maxInactiveInterval);
	}

	// reset the time of the session each time the client was active
	public void resetLastAccessed() {
		this.lastAccessed = System.currentTimeMillis();
	}

	// this is needed for session context to decide on session invalidation
	public long getLastAccessed() {
		return lastAccessed;
	}

	public void setAttribute(String attrKey, Object attrVal) {
		attributes.put(attrKey, attrVal);
	}

	public Object getAttribute(String attrKey) {
		return attributes.get(attrKey);
	}

	// this is needed for session context to decide on session invalidation
	public long getMaxInactiveInterval() {
		return maxInactiveInterval;
	}

	/**
	 * @param maxInactiveInterval given in mills
	 */
	//TODO - DELETE? DO YOU USE IT? IF SO, SET THE TIME WITH THE RELEVENT METHOD YOU HAVE ABOVE. (:
	public void setMaxInactiveInterval(long maxInactiveInterval) {
		this.maxInactiveInterval = maxInactiveInterval;
	}

}

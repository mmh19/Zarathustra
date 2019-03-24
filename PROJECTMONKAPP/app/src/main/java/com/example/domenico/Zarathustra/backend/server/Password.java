package com.example.domenico.Zarathustra.backend.server;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password implements Serializable {
	
	private byte[] password;
	
	public Password(String password) {
		try {
			this.password = MessageDigest.getInstance("SHA-256").digest(password.getBytes(StandardCharsets.UTF_8));
		} catch (NoSuchAlgorithmException e) {
			this.password = new byte[32];
		}
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		else if(obj instanceof String)
			return new Password((String) obj).equals(this);
		else if(obj instanceof Password) {
			for(int i = 0; i < password.length; i++)
				if(password[i] == ((Password) obj).password[i])
					return false;
			return true;
		} else
			return false;
	}
}

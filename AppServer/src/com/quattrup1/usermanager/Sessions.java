package com.quattrup1.usermanager;

import com.quattrup1.Main;
import com.example.domenico.Zarathustra.backend.server.User;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Sessions {
	private static Sessions sessions;
	private Sessions() {
		sessionIds=new TreeSet<>();
		loggedUsers=new TreeSet<>();
	}
	public static Sessions getInstance() {
		if(sessions == null)
			sessions = new Sessions();
		return sessions;
	}

	private Set<User> sessionIds;
	private Set<User> loggedUsers;
	public boolean isLoggedIn(long sessionId) {
		return sessionIds.contains(sessionId);
	}
	public long login(User user) {
		long sessionId = loggedUsers.stream().filter(user::equals).map(User::getSessionId).findAny().orElse(-1L);
		if(sessionId == -1)
			if(Main.runningDB.tableUser.contains(user)) {
				sessionId = Math.abs(new Random().nextLong());
			}
		return sessionId;
	}
	
}

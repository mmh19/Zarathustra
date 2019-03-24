package com.example.domenico.Zarathustra.backend.server;

import java.io.Serializable;

public class Request implements Serializable {
	private static final long serialVersionUID = 4185075305246051556L;
	private long sessionId;
	private RequestType type;
	private Serializable[] args;
	
	public Request(long sessionId, RequestType type, Serializable[] args) {
		this.sessionId = sessionId;
		this.type = type;
		this.args = args;
	}
	public RequestType getType() {
		return type;
	}
	public Object getArg(int index) {
		return args[index];
	}
	
}

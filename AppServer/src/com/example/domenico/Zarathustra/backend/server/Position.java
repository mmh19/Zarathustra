package com.example.domenico.Zarathustra.backend.server;

import java.io.Serializable;

public class Position implements Serializable {
	public final int x, y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}

package com.quattrup1.interfaces;

import com.example.domenico.Zarathustra.backend.server.Response;
import com.example.domenico.Zarathustra.backend.server.Request;

@FunctionalInterface
public interface RequestHandler {
	Response compute(Request request);
}

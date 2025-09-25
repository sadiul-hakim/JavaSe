package org.javase.from21To25;

public sealed interface ServerResponse {
    record Response(int code, String json) implements ServerResponse {
    }

    record NotFount(int code) implements ServerResponse {
    }

    record ServerError(int code, String message) implements ServerResponse {
    }
}

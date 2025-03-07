package ru.bogdsvn.recommendation.errors;

public class PreferenceNotFoundException extends RuntimeException {
    public PreferenceNotFoundException(String message) {
        super(message);
    }
}

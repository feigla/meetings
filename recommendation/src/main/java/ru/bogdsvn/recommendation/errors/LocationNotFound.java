package ru.bogdsvn.recommendation.errors;

public class LocationNotFound extends RuntimeException{
    public LocationNotFound(String message) {
        super(message);
    }
}

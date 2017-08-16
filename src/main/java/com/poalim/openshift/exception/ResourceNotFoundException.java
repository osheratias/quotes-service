package com.poalim.openshift.exception;

/**
 * Created by osher on 19/7/17.
 */

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

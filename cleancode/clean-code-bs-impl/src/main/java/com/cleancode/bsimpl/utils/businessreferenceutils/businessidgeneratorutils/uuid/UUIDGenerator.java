package com.cleancode.bsimpl.utils.businessreferenceutils.businessidgeneratorutils.uuid;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.UUID.randomUUID;

// To move in business logic module
public class UUIDGenerator {

    private static final Logger LOGGER = Logger.getLogger(UUIDGenerator.class.getName());

    public static UUID generateUUID(){
        LOGGER.log(Level.INFO, "Generating business reference.");
        UUID newRandomBusinessReference = randomUUID();
        LOGGER.log(Level.INFO, "Business Reference ".concat(newRandomBusinessReference.toString()).concat(" Successfully generated."));
        return newRandomBusinessReference;
    }
}

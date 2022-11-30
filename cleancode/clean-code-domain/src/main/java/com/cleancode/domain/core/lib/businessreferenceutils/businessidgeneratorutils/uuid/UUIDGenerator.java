package com.cleancode.domain.core.lib.businessreferenceutils.businessidgeneratorutils.uuid;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.UUID.randomUUID;

// To move in business logic module
public class UUIDGenerator {

    private static final Logger LOGGER = Logger.getLogger(UUIDGenerator.class.getName());

    public static UUID generateUUID(){
        LOGGER.log(Level.INFO, "Generating UUID business reference.");
        UUID newRandomBusinessReference = randomUUID();
        LOGGER.log(Level.INFO, "UUID Business Reference ".concat(newRandomBusinessReference.toString()).concat(" Successfully generated."));
        return newRandomBusinessReference;
    }
}

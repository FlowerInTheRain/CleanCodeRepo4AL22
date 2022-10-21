package com.cleancode.cleancodedbimpl.generators;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.UUID.randomUUID;

// To move in business logic module
public class UUIDGenerator {

    private static final Logger LOGGER = Logger.getLogger(UUIDGenerator.class.getName());
    public static final UUID generatedUUID = randomUUID();

    public static String generateUUIDWithoutUnionTrails(){
        LOGGER.log(Level.INFO, "Generating business reference.");
        String newBusinessReferenceToBindWithoutUnionTrails = generatedUUID.toString();
        newBusinessReferenceToBindWithoutUnionTrails = String.join("", newBusinessReferenceToBindWithoutUnionTrails.split("-"));
        LOGGER.log(Level.INFO, "Business Reference ".concat(newBusinessReferenceToBindWithoutUnionTrails).concat(" Successfully generated."));
        return newBusinessReferenceToBindWithoutUnionTrails;
    }
}

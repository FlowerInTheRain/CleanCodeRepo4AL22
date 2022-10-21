package com.esgi.arlo.generators;

import java.util.UUID;

import static java.util.UUID.randomUUID;

public class UUIDGenerator {
    public static final UUID generatedUUID = randomUUID();

    public static String generateUUIDWithoutUnionTrails(){
        String newBusinessReferenceToBindWithoutUnionTrails = generatedUUID.toString();
        newBusinessReferenceToBindWithoutUnionTrails = String.join("", newBusinessReferenceToBindWithoutUnionTrails.split("-"));
        return newBusinessReferenceToBindWithoutUnionTrails;
    }
}
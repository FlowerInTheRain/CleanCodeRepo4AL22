package com.cleancode.domain.core.lib.userserviceutils;

import com.cleancode.domain.pojo.CardCollection;
import com.cleancode.domain.pojo.UserAccount;
import com.cleancode.domain.core.lib.businessreferenceutils.businessidgeneratorutils.uuid.UUIDGenerator;
import com.cleancode.domain.core.lib.formatutils.uuidformatterutils.UUIDFormatter;

import java.util.ArrayList;

public class UserAccountOperationUtils {
    public static void handleInitBusinessUserCardCollection(String cardCollectionName, UserAccount userClientInfo) {
        String formattedUUIDToBindToCardCollectionReference = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true, "");
        // Functional trickier
        userClientInfo.setUserCardCollection(new CardCollection(null, cardCollectionName, formattedUUIDToBindToCardCollectionReference, new ArrayList<>()));
    }

    public static void handleBusinessUserReferenceCreation(UserAccount userClientInfo) {
        String formattedUUIDToBind = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true, "");
        if (formattedUUIDToBind.isEmpty()) {
            throw new RuntimeException();
        }
        // Can do functional
        userClientInfo.setBusinessReference(formattedUUIDToBind);
    }

    public static void revertReferenceAndCreationDateAttributionOnDbErrorForNonExistingUsers(UserAccount userClientInfo) {
        userClientInfo.setUserCardCollection(null);
        userClientInfo.setBusinessReference(null);
        userClientInfo.setClientCreationDate(null);
    }
}

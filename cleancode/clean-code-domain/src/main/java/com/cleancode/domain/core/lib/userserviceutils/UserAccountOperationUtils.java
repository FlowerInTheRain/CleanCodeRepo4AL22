package com.cleancode.domain.core.lib.userserviceutils;

import com.cleancode.domain.dto.cardcollection.CardCollection;
import com.cleancode.domain.dto.user.BusinessUserClientInfo;
import com.cleancode.domain.core.lib.businessreferenceutils.businessidgeneratorutils.uuid.UUIDGenerator;
import com.cleancode.domain.core.lib.formatutils.uuidformatterutils.UUIDFormatter;

import java.util.ArrayList;
import java.util.Optional;

public class UserAccountOperationUtils {
    public static void handleInitBusinessUserCardCollection(String cardCollectionName, BusinessUserClientInfo businessUserClientInfo) {
        Optional<String> formattedUUIDToBindToCardCollectionReference = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true, "");
        if (formattedUUIDToBindToCardCollectionReference.isEmpty()) {
            throw new RuntimeException();
        }
        // Functional trickier
        businessUserClientInfo.setUserCardCollection(new CardCollection(cardCollectionName, formattedUUIDToBindToCardCollectionReference.get(), new ArrayList<>()));
    }

    public static void handleBusinessUserReferenceCreation(BusinessUserClientInfo businessUserClientInfo) {
        Optional<String> formattedUUIDToBind = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true, "");
        if (formattedUUIDToBind.isEmpty()) {
            throw new RuntimeException();
        }
        // Can do functional
        formattedUUIDToBind.ifPresent(businessUserClientInfo::setBusinessReference);
    }

    public static void revertReferenceAndCreationDateAttributionOnDbErrorForNonExistingUsers(BusinessUserClientInfo businessUserClientInfo) {
        businessUserClientInfo.setUserCardCollection(null);
        businessUserClientInfo.setBusinessReference(null);
        businessUserClientInfo.setClientCreationDate(null);
    }
}

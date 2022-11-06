package com.cleancode.bsimpl.services.impl.user.userserviceutils;

import com.cleancode.bsimpl.dto.cardcollection.CardCollection;
import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;
import com.cleancode.bsimpl.utils.businessreferenceutils.businessidgeneratorutils.uuid.UUIDGenerator;
import com.cleancode.bsimpl.utils.formatutils.uuid.UUIDFormatter;

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

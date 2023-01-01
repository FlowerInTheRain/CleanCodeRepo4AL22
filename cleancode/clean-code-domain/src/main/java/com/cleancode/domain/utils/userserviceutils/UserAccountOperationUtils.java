package com.cleancode.domain.utils.userserviceutils;

import com.cleancode.domain.dto.cardcollection.CardCollection;
import com.cleancode.domain.dto.user.BusinessUserClientInfo;
import com.cleancode.domain.utils.businessreferenceutils.businessidgeneratorutils.uuid.UUIDGenerator;
import com.cleancode.domain.utils.formatutils.uuidformatterutils.UUIDFormatter;

import java.util.ArrayList;

public class UserAccountOperationUtils {
    public static void handleInitBusinessUserCardCollection(String cardCollectionName, BusinessUserClientInfo businessUserClientInfo) {
        String formattedUUIDToBindToCardCollectionReference = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true, "");
        businessUserClientInfo.setUserCardCollection(new CardCollection(cardCollectionName, formattedUUIDToBindToCardCollectionReference, new ArrayList<>()));
    }

    public static void handleBusinessUserReferenceCreation(BusinessUserClientInfo businessUserClientInfo) {
        String formattedUUIDToBind = UUIDFormatter.formatUUIDSequence(UUIDGenerator.generateUUID(), true, "");
        //
    }

    public static void revertReferenceAndCreationDateAttributionOnDbErrorForNonExistingUsers(BusinessUserClientInfo businessUserClientInfo) {
        businessUserClientInfo.setUserCardCollection(null);
        businessUserClientInfo.setBusinessReference(null);
        businessUserClientInfo.setClientCreationDate(null);
    }
}

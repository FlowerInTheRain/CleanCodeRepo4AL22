package com.cleancode.bsimpl.utils.userserviceutils;

import com.cleancode.bsimpl.dto.cardcollection.CardCollection;
import com.cleancode.bsimpl.dto.user.BusinessUserClientInfo;
import com.cleancode.bsimpl.utils.businessreferenceutils.businessidgeneratorutils.uuid.UUIDGenerator;
import com.cleancode.bsimpl.utils.formatutils.uuidformatterutils.UUIDFormatter;

import java.util.ArrayList;
import java.util.Optional;

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

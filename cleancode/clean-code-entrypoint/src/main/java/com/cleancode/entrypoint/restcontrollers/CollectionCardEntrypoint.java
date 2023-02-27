package com.cleancode.entrypoint.restcontrollers;

import com.cleancode.cleancodeapi.dto.card.CardRequest;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.pojo.CardCollectionCard;
import com.cleancode.domain.ports.in.collectioncard.CollectionCardFinder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collectionCard")
@CrossOrigin
@Api
public class CollectionCardEntrypoint {

    private final CollectionCardFinder collectionCardFinder;

    public CollectionCardEntrypoint(CollectionCardFinder collectionCardFinder){
        this.collectionCardFinder = collectionCardFinder;
    }

    @ApiOperation(value = "Find all cards",
            response = CardRequest.class)
    @ApiResponse(code=200, message="Card found")
    @GetMapping(value = "/findAllCards", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CardCollectionCard> findAllCards() throws CleanCodeException {
        return collectionCardFinder.findAllCards();
    }

    @ApiOperation(value = "Find one card by reference",
            response = CardRequest.class)
    @ApiResponse(code=200, message="Card found")
    @GetMapping(value = "/findByCardCollectionCardReference", produces = MediaType.APPLICATION_JSON_VALUE)
    public CardCollectionCard findByCardCollectionCardReference(@Parameter String reference) throws CleanCodeException {
        return collectionCardFinder.findByCardCollectionCardReference(reference);
    }
}

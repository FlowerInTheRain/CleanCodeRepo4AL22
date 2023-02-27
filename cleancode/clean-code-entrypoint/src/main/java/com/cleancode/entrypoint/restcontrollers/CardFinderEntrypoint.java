package com.cleancode.entrypoint.restcontrollers;

import com.cleancode.cleancodeapi.dto.card.CardRequest;
import com.cleancode.cleancodeapi.mappers.CardMapper;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.ports.in.card.CardFinder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/card/find")
@CrossOrigin
@Api
public class CardFinderEntrypoint {
    private final CardFinder cardFinder;

    public CardFinderEntrypoint(CardFinder cardFinder) {
        this.cardFinder = cardFinder;
    }

    @ApiOperation(value = "Find all cards",
            response = CardRequest.class)
    @ApiResponse(code=200, message="Card found")
    @GetMapping(value = "/findAllCards", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CardRequest> findAllCards() throws CleanCodeException {
        var response = cardFinder.findAllCards();
        return CardMapper.INSTANCE.fromDomain(response);
    }
}

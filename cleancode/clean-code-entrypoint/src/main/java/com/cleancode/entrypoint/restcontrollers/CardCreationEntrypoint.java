package com.cleancode.entrypoint.restcontrollers;

import com.cleancode.cleancodeapi.dto.card.Card;
import com.cleancode.cleancodeapi.mappers.withdomain.cards.CardMapper;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.ports.in.card.CardCreator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card/create")
@CrossOrigin
@Api
public class CardCreationEntrypoint {

    private final CardCreator cardCreator;

    public CardCreationEntrypoint(CardCreator cardCreator){
        this.cardCreator = cardCreator;
    }

    @ApiOperation(value = "Create a card",
            response = Card.class,
            notes = "Customer must not exist")
    @ApiResponse(code=200, message="Card Created")
    @PostMapping(value = "/saveCard", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Card saveCard(@RequestBody Card card) throws CleanCodeException {
        return CardMapper.INSTANCE.fromBsToApi(cardCreator.saveCard(CardMapper.INSTANCE.fromApiToBs(card)));
    }


}

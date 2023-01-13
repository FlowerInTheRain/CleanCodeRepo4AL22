package com.cleancode.entrypoint.restcontrollers;

import com.cleancode.cleancodeapi.mappers.withdomain.cards.CardMapper;
import com.cleancode.cleancodeapi.dto.card.Card;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.ports.in.card.CardCreator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("CardOperationRestController")
@RequestMapping("/card/")
@CrossOrigin
@Api
public class CardOperations {

    private final CardCreator cardCreator;

    public CardOperations(CardCreator cardCreator){
        this.cardCreator = cardCreator;
    }

    @ApiOperation(value = "Create a card",
            response = Card.class,
            notes = "Customer must not exist")
    @ApiResponse(code=200, message="Card Created")
    @PutMapping(value = "/saveCard", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Card saveCard(@RequestBody Card card) throws CleanCodeException {
        return CardMapper.INSTANCE.fromBsToApi(cardCreator.saveCard(CardMapper.INSTANCE.fromApiToBs(card)));
    }

    @ApiOperation(value = "Find all cards",
            response = Card.class)
    @ApiResponse(code=200, message="Card found")
    @GetMapping(value = "/findAllCards", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Card> findAllCards() throws CleanCodeException {
        var response = cardCreator.findAllCards();
        System.out.println(response);
        return CardMapper.INSTANCE.fromListBsToListApi(response);
    }
}

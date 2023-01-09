package com.cleancode.cleancodespringrest.entrypoints.restservice.restcontrollers.cardoperationscontrollers;

import com.cleancode.cleancodeapi.apibsmappers.cards.CardMapper;
import com.cleancode.cleancodeapi.dto.card.Card;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.ports.in.card.CardOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController("CardOperationRestController")
@RequestMapping("/card/")
@CrossOrigin
@Api
public class CardOperationRestController {

    private static final Logger LOGGER = Logger.getLogger(CardOperationRestController.class.getName());
    private CardOperation cardOperation;

    @Autowired
    private void setCardBusinessService(CardOperation cardOperation){
        this.cardOperation = cardOperation;
    }

    @ApiOperation(value = "Create a card",
            response = Card.class,
            notes = "Customer must not exist")
    @ApiResponse(code=200, message="Card Created")
    @PutMapping(value = "/saveCard", produces = MediaType.APPLICATION_JSON_VALUE)
    public Card saveCard(@RequestBody Card card) throws CleanCodeException {
        return CardMapper.INSTANCE.fromBsToApi(cardOperation.saveCard(CardMapper.INSTANCE.fromApiToBs(card)));
    }

    @ApiOperation(value = "Find all cards",
            response = Card.class)
    @ApiResponse(code=200, message="Card found")
    @GetMapping(value = "/findAllCards", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Card> findAllCards() throws CleanCodeException {
        return CardMapper.INSTANCE.fromListBsToListApi(cardOperation.findAllCards());
    }

    @ApiOperation(value = "Find one card by reference",
            response = Card.class)
    @ApiResponse(code=200, message="Card found")
    @GetMapping(value = "/findOneCardByReference", produces = MediaType.APPLICATION_JSON_VALUE)
    public Card findOneCardByReference(String cardReference) throws CleanCodeException {
        return CardMapper.INSTANCE.fromBsToApi(cardOperation.findOneCardByReference(cardReference));
    }
}
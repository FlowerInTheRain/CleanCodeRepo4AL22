package com.cleancode.cleancodespringrest.entrypoints.restservice.restcontrollers.cardoperationscontrollers;

import com.cleancode.cleancodeapi.apibsmappers.cards.CardMapper;
import com.cleancode.cleancodeapi.dto.card.Card;
import com.cleancode.domain.core.lib.exceptionsmanagementutils.exceptions.CleanCodeException;
import com.cleancode.domain.usecases.card.CardBusinessUserCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/card/")
@CrossOrigin
@Api
public class CardOperationRestController {

    private static final Logger LOGGER = Logger.getLogger(CardOperationRestController.class.getName());
    private CardBusinessUserCase cardBusinessUserCase;

    @Autowired
    private void setCardBusinessService(CardBusinessUserCase cardBusinessUserCase){
        this.cardBusinessUserCase = cardBusinessUserCase;
    }

    @ApiOperation(value = "Create a card",
            response = Card.class,
            notes = "Customer must not exist")
    @ApiResponse(code=200, message="Card Created")
    @PutMapping(value = "/saveCard", produces = MediaType.APPLICATION_JSON_VALUE)
    public com.cleancode.domain.dto.card.Card saveCard(@RequestBody Card card) throws CleanCodeException {
        return CardMapper.INSTANCE.fromBsToApi(cardBusinessUserCase.saveCard(CardMapper.INSTANCE.fromApiToBs(card)));
    }
}

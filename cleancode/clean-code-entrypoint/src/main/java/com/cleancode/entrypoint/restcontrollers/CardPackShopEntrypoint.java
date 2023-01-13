package com.cleancode.entrypoint.restcontrollers;

import com.cleancode.cleancodeapi.mappers.withdomain.cardcollectioncards.CardCollectionCardsMapper;
import com.cleancode.cleancodeapi.dto.cardpacks.CardPackResponse;
import com.cleancode.domain.ports.in.cardpack.CardPackOpener;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController("CardPackController")
@RequestMapping("/cardPackShop")
@CrossOrigin
@Api
public class CardPackShopEntrypoint {

    private final Logger LOGGER = Logger.getLogger(CardPackShopEntrypoint.class.getName());
    private final CardPackOpener cardPackOpener;

    public CardPackShopEntrypoint(CardPackOpener cardPackOpener){
        this.cardPackOpener = cardPackOpener;
    }

    @ApiOperation(value = "Buy a silver card pack for 1 CC coin",
            response = CardPackResponse.class,
            notes = "User must have enough moula")
    @ApiResponse(code=200, message="Pack bought")
    @PutMapping(value = "/buySilverCardPack", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CardPackResponse> createUserAccount(String userName) {
        LOGGER.log(Level.INFO, "Calling card pack shop");
        final var silverCardPack = cardPackOpener.openSilverCardPack(userName);
        LOGGER.log(Level.INFO, "User " + userName + " successfully bought a silver pack");
        return CardCollectionCardsMapper.INSTANCE.fromDomain(silverCardPack);

    }
}
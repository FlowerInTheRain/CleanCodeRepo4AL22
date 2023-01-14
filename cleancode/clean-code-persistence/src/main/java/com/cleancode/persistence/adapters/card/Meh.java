package com.cleancode.persistence.adapters.card;

import org.mapstruct.Mapping;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.CLASS)
@Mapping(target = "collectionCardList", ignore = true)
public @interface Meh {
}

package com.cleancode.cleancodedbimpl.generators.formatters;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class UUIDFormatter {
    public static Optional<String> formatUUIDSequenceWithPrefixAndSuffixOptions(UUID uuidToFormat, boolean withUnionTrailRemoval, CharSequence joiner, boolean withPrefix,
                                                                            String prefix,
                                                      boolean withSuffix, String suffix){
        if(uuidToFormat == null){
            throw new IllegalArgumentException("UUID can not be null");
        }

        String newBusinessReferenceToBindWithoutUnionTrails = uuidToFormat.toString();
        if(withPrefix){
            newBusinessReferenceToBindWithoutUnionTrails = prefix.concat(newBusinessReferenceToBindWithoutUnionTrails);
        }

        if(withSuffix){
            newBusinessReferenceToBindWithoutUnionTrails = newBusinessReferenceToBindWithoutUnionTrails.concat(suffix);
        }

        if(!withUnionTrailRemoval){
            return Optional.ofNullable(newBusinessReferenceToBindWithoutUnionTrails);
        }

        return Optional.of(String.join(Objects.requireNonNullElse(joiner, ""), newBusinessReferenceToBindWithoutUnionTrails));
    }

    public static Optional<String> formatUUIDSequence(UUID uuidToFormat, boolean withUnionTrailRemoval, CharSequence joiner){
        if(uuidToFormat == null){
            throw new IllegalArgumentException("UUID can not be null");
        }

        String newBusinessReferenceToBindWithoutUnionTrails = uuidToFormat.toString();

        if(!withUnionTrailRemoval){
            return Optional.ofNullable(newBusinessReferenceToBindWithoutUnionTrails);
        }

        return Optional.of(String.join(Objects.requireNonNullElse(joiner, ""), newBusinessReferenceToBindWithoutUnionTrails));
    }
}

package com.fetchAward.demo.Exception;

import java.util.UUID;

public class ItemNotFoundException extends RuntimeException{

    public ItemNotFoundException(UUID id) {
        super("The Item id" + id + " does not exist in our records ");
    }
}

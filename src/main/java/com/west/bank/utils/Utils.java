package com.west.bank.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by alex on 12/2/16.
 */
public class Utils {



    public static Authentication getAuth(){
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth;
    }
}

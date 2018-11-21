package ru.kpfu.itis.csport.controller;

import org.springframework.stereotype.Controller;

import java.lang.annotation.*;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 4/26/18 11:49 PM
 *
 * Annotation indicating controller handles authenticated requests
 */
@Inherited
@Controller
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface AuthController {
}

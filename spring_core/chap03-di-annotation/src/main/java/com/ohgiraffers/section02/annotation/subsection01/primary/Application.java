package com.ohgiraffers.section02.annotation.subsection01.primary;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext applicationContext
                // ioc container 에서 bean(@Component 붙은 클래스)으로 등록
                = new AnnotationConfigApplicationContext("com.ohgiraffers.section02");

        PokemonService pokemonService = applicationContext.getBean("pokemonServicePrimary", PokemonService.class);
        pokemonService.pokemonAttack();
    }
}

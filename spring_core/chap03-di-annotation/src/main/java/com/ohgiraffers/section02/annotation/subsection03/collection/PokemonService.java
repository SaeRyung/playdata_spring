package com.ohgiraffers.section02.annotation.subsection03.collection;

import com.ohgiraffers.section02.common.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("pokemonServiceCollection")
public class PokemonService {

    /* 컬렉션 타입으로 의존성 주입을 받게 되면 해당 타입의 등록 된 빈이 모두 주입 된다.*/
//    private List<Pokemon> pokemons;
//
//    @Autowired
//    public PokemonService(List<Pokemon> pokemons) {
//        this.pokemons = pokemons;
//    }
//
//    public void pokemonAttack(){
//        pokemons.forEach(Pokemon::attack);
//    }

    private Map<String, Pokemon> pokemons;

    public PokemonService(Map<String, Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public void pokemonAttack(){
        pokemons.forEach((k,v) -> {
            System.out.println(k + " : " + v);
            v.attack();
        });
    }
}

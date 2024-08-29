package com.ohgiraffers.section02.annotation.subsection01.primary;

import com.ohgiraffers.section02.common.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pokemonServicePrimary")
public class PokemonService {

    // Pokemon 생성자 선언 후
    private Pokemon pokemon;

    // @Autowired: 타입을 기준으로 가져온다. -> Charmander에 @Primary 작성함
    @Autowired
    // 생성자로 의존성 주입
    public PokemonService(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    // 확인을 위한 메소드
    public void pokemonAttack(){
        pokemon.attack();
    }
}

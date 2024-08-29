package com.ohgiraffers.section02.annotation.subsection02.qualifier;

import com.ohgiraffers.section02.common.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("pokemonServiceQualifier")
public class PokemonService {

    /*1. 필드 주입의 경우*/
//    @Autowired //타입우선하여 가져옴
//    @Qualifier("pikachu")
    private Pokemon pokemon;


/* 2. 생성자 주입의 경우*/
/* @Qualifier : 여러 개의 빈 객체 중 특정 빈 객체를 이름으로 지정하는 어노테이션
 * @Primary와 같이 쓰인 경우 @Qualifier가 우선시 된다. */
    @Autowired
    public PokemonService(@Qualifier("pikachu") Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public void pokemonAttack(){
        pokemon.attack();
    }
}

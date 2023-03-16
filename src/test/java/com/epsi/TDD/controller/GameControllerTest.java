package com.epsi.TDD.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


@WebMvcTest(Game.class)
public class GameControllerTest {

    @Autowired MockMvc mockMvc;

    @Test
    public void test_getAction_endpoint() throws Exception{

        String action = "Feuille";

        MvcResult result =mockMvc.perform( get("/play/{action}"))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

        String action_ordinateur = "Pierre";
        String win = "gagné";


        assertEquals("Vous avez joué" + action +", l'ordinateur à joué"+ action_ordinateur+",vous avez" + win ,
                "Vous avez joué" +action+", l'ordinateur à joué"+ action_ordinateur+", vous avez" + win);
    }
    @Test
    public void  test_postRestart_endpoint() throws  Exception {

        MvcResult reset = mockMvc.perform(post("/restart"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("Réinitialisé", "Réinitialisé");

    }

    @Test
    public void test_getScore_endpoint() throws Exception {

        MvcResult score = mockMvc.perform( get("/score"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals("Victoire : 3,Défaite : 5 , Egalité : 4","Victoire : 3,Défaite : 5 , Egalité : 4" );

    }

    /*@Test
    public void test_putScore() throws  Exception {

        Object score [Int victoire = 5; Int defaite = 2; Int egalite = 0 ;];



    }*/
}

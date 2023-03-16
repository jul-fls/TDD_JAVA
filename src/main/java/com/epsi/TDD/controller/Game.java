package com.epsi.TDD.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

class Resultats {
        public Integer victories;
        public Integer draws;
        public Integer losses;
}

@RestController
@RequestMapping("/game")
public class Game {
        private static final Set<String> actions = Set.of("Pierre", "Feuille", "Ciseaux");
        private Resultats resultats = new Resultats();

        @GetMapping(path = "/play")
        public String getAction(@PathVariable("action") String action) {
                //renvoie ce qu'a fait le joueur, ce qu'a fait l'ordi
                // si gagnez perdu ou égalité
                if(actions.contains(action)) {
                        String action_joueur = action;
                        Integer random = (int) (Math.random() * 3);
                        String action_ordi = Arrays.asList(actions).get(random).toString();
                        if (action_joueur == action_ordi) {
                                resultats.draws++;
                                return "Vous avez joué " + action_joueur + ", l'ordinateur a joué " + action_ordi + ", vous avez fait égalité";
                        } else if (action_joueur == "Pierre" && action_ordi == "Ciseaux") {
                                resultats.victories++;
                                return "Vous avez joué" + action_joueur + ", l'ordinateur a joué " + action_ordi + ", vous avez gagné";
                        } else if (action_joueur == "Pierre" && action_ordi == "Feuille") {
                                resultats.losses++;
                                return "Vous avez joué" + action_joueur + ", l'ordinateur a joué " + action_ordi + ", vous avez perdu";
                        } else if (action_joueur == "Feuille" && action_ordi == "Pierre") {
                                resultats.victories++;
                                return "Vous avez joué" + action_joueur + ", l'ordinateur a joué " + action_ordi + ", vous avez gagné";
                        } else if (action_joueur == "Feuille" && action_ordi == "Ciseaux") {
                                resultats.losses++;
                                return "Vous avez joué" + action_joueur + ", l'ordinateur a joué " + action_ordi + ", vous avez perdu";
                        } else if (action_joueur == "Ciseaux" && action_ordi == "Pierre") {
                                resultats.losses++;
                                return "Vous avez joué" + action_joueur + ", l'ordinateur a joué " + action_ordi + ", vous avez perdu";
                        } else if (action_joueur == "Ciseaux" && action_ordi == "Feuille") {
                                resultats.victories++;
                                return "Vous avez joué" + action_joueur + ", l'ordinateur a joué " + action_ordi + ", vous avez gagné";
                        }
                }
                return "";
        }


        @PostMapping("/restart")
        public ResponseEntity postRestart() {
                resultats.losses = 0;
                resultats.victories = 0;
                resultats.draws = 0;
                return ResponseEntity.ok().build();
        }

        @GetMapping("/score")
        public String getScore() {
                return resultats.toString();

                //affiche les différents scores
        }
        @PutMapping("/score/{type}")
        public String putScore(@PathVariable("type") String type, @RequestParam Integer score) {
                if (type == "victories")
                        resultats.victories = score;
                else if (type == "draws")
                        resultats.draws = score;
                else if (type == "losses")
                        resultats.losses = score;
                return resultats.toString();
        }
}


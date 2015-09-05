/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.controllers;
import java.util.List;
import lydichris.smashbracket.exceptions.TournamentNotFoundException;
import lydichris.smashbracket.models.Match;
import lydichris.smashbracket.services.BracketService;
import lydichris.smashbracket.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author cgmcandrews
 */
@RestController
public class MatchController {
    
    @Autowired MatchService matchService;
    @Autowired BracketService bracketService;
    
    public void setMatchService(MatchService matchService){
        this.matchService = matchService;
    }
    
    @RequestMapping(value = "/matches", method = RequestMethod.GET)
    Match getMatch(@RequestParam String uuid)
            throws TournamentNotFoundException {
	return matchService.getMatch(uuid);
    }
    
    @RequestMapping(value = "/matches", method = RequestMethod.POST)
    List<Match> createMatches(@RequestParam String tournamentUuid){
	return bracketService.createBrackets(tournamentUuid);
    }
}

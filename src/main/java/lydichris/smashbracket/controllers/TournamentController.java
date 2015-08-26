/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.controllers;

import java.util.Date;
import lydichris.smashbracket.enums.TournamentType;
import lydichris.smashbracket.exceptions.TournamentCreationException;
import lydichris.smashbracket.exceptions.TournamentNotFoundException;
import lydichris.smashbracket.models.Tournament;
import lydichris.smashbracket.services.TournamentService;
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
public class TournamentController {
   
    @Autowired TournamentService tournamentService;
    
    public void setTournamentService(TournamentService tournamentService){
        this.tournamentService = tournamentService;
    }
    
    @RequestMapping(value = "/tournaments", method = RequestMethod.GET)
    Tournament getTournament(@RequestParam String uuid)
            throws TournamentNotFoundException {
	return tournamentService.getTournament(uuid);
    }
    
    @RequestMapping(value = "/tournaments", method = RequestMethod.POST)
    Tournament createTournament(@RequestParam String name,
            @RequestParam String description,
            @RequestParam(value = "maxEntrants", required = false) int maxEntrants,
            @RequestParam String game,
            @RequestParam TournamentType tournamentType,
            @RequestParam Date startTime,
            @RequestParam String hostId)
            throws TournamentCreationException {
        
        //Should take in userName for host somehow
        //Consider adding location
	return tournamentService.createTournament(name, description, maxEntrants, game, tournamentType, startTime, hostId);
    }
        
    @RequestMapping(value = "/tournaments", method = RequestMethod.PUT)
    Tournament editTournament(@RequestParam Tournament tournament,
            @RequestParam boolean seed)
            throws TournamentCreationException {
        
	return tournamentService.editTournament(tournament, seed);
    }
}

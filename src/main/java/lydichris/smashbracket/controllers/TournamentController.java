/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lydichris.smashbracket.enums.TournamentType;
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
    Tournament getTournament(@RequestParam String uuid) {
	return tournamentService.getTournament(uuid);
    }
    
    @RequestMapping(value = "/tournaments", method = RequestMethod.POST)
    Tournament createTournament(@RequestParam String name,
            @RequestParam String description,
            @RequestParam(value = "maxEntrants", required = false) int maxEntrants,
            @RequestParam String game,
            @RequestParam TournamentType tournamentType,
            @RequestParam String startTime,
            @RequestParam String hostId) throws ParseException {
       
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
        Date startTimeDate = formatter.parse(startTime);
        //Should take in userName for host somehow
        //Consider adding location
	return tournamentService.createTournament(name, description, maxEntrants, game, tournamentType, startTimeDate, hostId);
    }
        
    @RequestMapping(value = "/tournaments", method = RequestMethod.PUT)
    Tournament editTournament(
            @RequestParam String uuid,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam(value = "maxEntrants", required = false) int maxEntrants,
            @RequestParam String game,
            @RequestParam TournamentType tournamentType,
            @RequestParam String startTime,
            @RequestParam String hostId,
            @RequestParam boolean seed) throws ParseException {
        
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
        Date startTimeDate = formatter.parse(startTime);
        
	return tournamentService.editTournament(uuid, name, description, maxEntrants, game, tournamentType, startTimeDate, hostId, seed);
    }
}

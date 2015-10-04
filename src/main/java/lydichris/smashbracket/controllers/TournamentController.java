/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import lydichris.smashbracket.enums.TournamentType;
import lydichris.smashbracket.models.Entrant;
import lydichris.smashbracket.models.Match;
import lydichris.smashbracket.models.Tournament;
import lydichris.smashbracket.services.EntrantService;
import lydichris.smashbracket.services.MatchService;
import lydichris.smashbracket.services.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired EntrantService entrantService;
    @Autowired MatchService matchService;
            
    public void setTournamentService(TournamentService tournamentService){
        this.tournamentService = tournamentService;
    }
    
    @RequestMapping(value = "/tournaments/{uuid}", method = RequestMethod.GET)
    Tournament getTournament(@PathVariable String uuid) {
	return tournamentService.getTournament(uuid);
    }
    
    @RequestMapping(value = "/tournaments/{tournamentUuid}/entrants", method = RequestMethod.GET)
    List<Entrant> getEntrantsInTournament(@PathVariable String tournamentUuid) {
	return entrantService.getEntrantsInTournament(tournamentUuid);
    }
    
    @RequestMapping(value = "/tournaments/{tournamentUuid}/matches", method = RequestMethod.GET)
    List<Match> getMatchesInTournament(@PathVariable String tournamentUuid) {
	return matchService.getMatchesInTournament(tournamentUuid);
    }
    
    @RequestMapping(value = "/tournaments", method = RequestMethod.GET)
    List<Tournament> getTournaments(@RequestParam int offset,
                              @RequestParam int size,
                              @RequestParam String query) {
        
	return tournamentService.getTournament(offset, size, query);
    }
    
    @RequestMapping(value = "/tournaments", method = RequestMethod.DELETE)
    void deleteTournament(@RequestParam String uuid) {
	tournamentService.deleteTournament(uuid);
    }
    
    @RequestMapping(value = "/tournaments", method = RequestMethod.POST)
    Tournament createTournament(@RequestParam String name,
            @RequestParam String description,
            @RequestParam(value = "maxEntrants", required = false, defaultValue="0") int maxEntrants,
            @RequestParam String game,
            @RequestParam TournamentType tournamentType,
            @RequestParam String startTime,
            @RequestParam String hostId) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:m:s zzz", Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
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
        
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:m:s zzz", Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date startTimeDate = formatter.parse(startTime);
        
	return tournamentService.editTournament(uuid, name, description, maxEntrants, game, tournamentType, startTimeDate, hostId, seed);
    }
}

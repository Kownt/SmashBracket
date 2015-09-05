/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import lydichris.smashbracket.models.Entrant;
import lydichris.smashbracket.models.Match;
import lydichris.smashbracket.models.SeedComparator;
import lydichris.smashbracket.models.Tournament;
import lydichris.smashbracket.persistence.MatchPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author cgmcandrews
 */
@Service("bracketService")
public class BracketService {

    @Autowired
    TournamentService tournamentService;
    @Autowired
    EntrantService entrantService;
    @Autowired
    MatchPersistence matchPersistence;
    
    Entrant bye = new Entrant();

    public List<Match> createBrackets(String tournamentUuid) {
        Tournament tournament = tournamentService.getTournament(tournamentUuid);
        return generateBracket(tournament);
    }

    private List<Match> generateBracket(Tournament tournament) {
        switch (tournament.getTournamentType()) {
            case SINGLE_ELIMINATION:
                return generateSingleElimationTournament(tournament);
            default:
                return null;
        }
    }

    private List<Match> generateSingleElimationTournament(Tournament tournament) {
        List<Entrant> entrants = entrantService.getEntrantsInTournament(tournament.getId());
        Collections.sort(entrants, new SeedComparator());
        int entrantsSizeToFillWithByesUntil = 0;

        entrantsSizeToFillWithByesUntil = calculateHigherPowerOfTwo(entrants.size());

        while (entrants.size() < entrantsSizeToFillWithByesUntil) {
            entrants.add(bye);
        }

        List<Match> rounds = generateFirstRounds(entrants);
        rounds.addAll(generateRemainingRounds(rounds, 1));
        return matchPersistence.persistMatches(rounds);
    }

    private int calculateHigherPowerOfTwo(Integer size) {
        if ((size & (size - 1)) == 0) {
            return (size);
        }

        while ((size & (size - 1)) != 0) {
            size = size & (size - 1);
        }

        size = size << 1;
        return size;
    }

    private List<Match> generateFirstRounds(List<Entrant> entrants) {
        //Defect 1 is a power of 2 :/ need to prevent that
        //Todo the first rounds need to forward reference the next
        List<Entrant> toPlace = new ArrayList<>(entrants);
        List<Match> matches = new ArrayList<>();
        int topSeed = 0;
        int round = 1;
        while (toPlace.size() > 0) {
            int bottomSeed = toPlace.size() - 1;
            Match match = new Match();
            match.setId(UUID.randomUUID().toString());
            match.setEntrantOne(toPlace.get(topSeed).getUuid());
            match.setEntrantTwo(toPlace.get(bottomSeed).getUuid());
            match.setRoundNumber(round);
            matches.add(match);
            toPlace.remove(bottomSeed);
            toPlace.remove(topSeed);
        }
        return matches;
    }

    private List<Match> generateRemainingRounds(List<Match> priorRounds, int roundNumber) {
        List<Match> currentRounds = generateCurrentRound(priorRounds, roundNumber);
        
        if(currentRounds.size() > 1) {
            currentRounds.addAll(generateRemainingRounds(currentRounds, roundNumber + 1));
        }
        return currentRounds;
    }

    private List<Match> generateCurrentRound(List<Match> priorRounds, int roundNumber) {
        List<Match> toPlace = new ArrayList<>(priorRounds);
        List<Match> currentRound = new ArrayList<>();
        int topMatch = 0;
        int bottomMatch = 1;
        while (toPlace.size() > 1) {
            Match match = new Match();
            match.setId(UUID.randomUUID().toString());
            match.setFirstPreviousMatch(toPlace.get(topMatch).getId());
            match.setSecondPreviousMatch(toPlace.get(bottomMatch).getId());
            match.setRoundNumber(roundNumber + 1);
            currentRound.add(match);
            toPlace.remove(bottomMatch);
            toPlace.remove(topMatch);
        }
        
        if(toPlace.size() == 1) {
            return currentRound;
        }
        
        return currentRound;
    }

}

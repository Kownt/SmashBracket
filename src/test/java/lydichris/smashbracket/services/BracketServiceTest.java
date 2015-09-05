/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.services;

import java.util.ArrayList;
import java.util.List;
import lydichris.smashbracket.enums.TournamentType;
import lydichris.smashbracket.models.Entrant;
import lydichris.smashbracket.models.Match;
import lydichris.smashbracket.models.Tournament;
import lydichris.smashbracket.persistence.MatchPersistence;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import static org.mockito.Matchers.anyString;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author cgmcandrews
 */
public class BracketServiceTest {
    
    List<Entrant> entrants;
    Tournament tournament;
    BracketService service = new BracketService();
    
    @Captor
    ArgumentCaptor<List<Match>> captor;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        entrants = new ArrayList<>();
        populateEntrants();
        tournament = new Tournament();
        tournament.setTournamentType(TournamentType.SINGLE_ELIMINATION);
        service.tournamentService = Mockito.mock(TournamentService.class);
        service.entrantService = Mockito.mock(EntrantService.class);
        service.matchPersistence = Mockito.mock(MatchPersistence.class);
    }

    @Test
    public void testGenerateTournamentWithFullSeedsInOrder(){
        when(service.tournamentService.getTournament(anyString())).thenReturn(tournament);
        when(service.entrantService.getEntrantsInTournament(anyString())).thenReturn(entrants);
        service.createBrackets("tourney");
        verify(service.matchPersistence).persistMatches(captor.capture());
        List<Match> matches = captor.getValue();
        assertEquals(15, matches.size());
        assertEquals(entrants.get(0).getUuid(), matches.get(0).getEntrantOne());
        assertEquals(entrants.get(entrants.size() - 1).getUuid(), matches.get(0).getEntrantTwo());
    }
    
    @Test
    public void testGenerateTournamentWithSomeByes(){
        entrants.remove(entrants.size() - 1);
        when(service.tournamentService.getTournament(anyString())).thenReturn(tournament);
        when(service.entrantService.getEntrantsInTournament(anyString())).thenReturn(entrants);
        service.createBrackets("tourney");
        verify(service.matchPersistence).persistMatches(captor.capture());
        List<Match> matches = captor.getValue();
        assertEquals(15, matches.size());
        assertEquals(entrants.get(0).getUuid(), matches.get(0).getEntrantOne());
        assertEquals(entrants.get(entrants.size() - 1).getUuid(), matches.get(0).getEntrantTwo());
        assertEquals(null, matches.get(0).getEntrantTwo());
    }
    
    private void populateEntrants() {
        int desiredEntrants = 16;
        for(int i = 0; i < desiredEntrants; i++){
            Entrant entrant = new Entrant();
            entrant.setUuid(Integer.toString(i));
            entrant.setTag("tag" + Integer.toString(i));
            entrant.setTournamentUuid("tourney");
            entrant.setUserUuid("user" + Integer.toString(i));
            entrant.setSeed(i);
            entrants.add(entrant);
        }
    }
}

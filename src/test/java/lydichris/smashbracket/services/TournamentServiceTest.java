/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.services;

import java.util.Date;
import lydichris.smashbracket.enums.TournamentType;
import lydichris.smashbracket.models.Tournament;
import lydichris.smashbracket.persistence.TournamentPersistence;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author cgmcandrews
 */
public class TournamentServiceTest {
    
    TournamentService service = new TournamentService();
    
    String name = "testing";
    String description = "desc";
    int maxEntrants = 1;
    String game = "Soccer";
    TournamentType type = TournamentType.MULTI_STAGE;
    Date startTime = new Date();
    String hostId = "12";
    String tournamentId = "1";
    boolean isRevealed =false;
    Tournament mockTournament;
    
    @Before
    public void setUp() {
        service.tournamentPersistence = mock(TournamentPersistence.class);
        mockTournament = new Tournament();
        mockTournament.setHostId(hostId);
        mockTournament.setDescription(description);
        mockTournament.setGame(game);
        mockTournament.setId(tournamentId);
        mockTournament.setMatchesRevealed(isRevealed);
        mockTournament.setStartTime(startTime);
        mockTournament.setTournamentType(type);
    }
    
    @Test
    public void testCreateTournamentDelegatesToPersistence(){
        ArgumentCaptor<Tournament> captor = ArgumentCaptor.forClass(Tournament.class);
        service.createTournament(mockTournament.getName(),
                mockTournament.getDescription(),
                mockTournament.getMaxEntrants(),
                mockTournament.getGame(),
                mockTournament.getTournamentType(),
                mockTournament.getStartTime(),
                mockTournament.getHostId());
        
        when(service.tournamentPersistence.createTournament(any(Tournament.class))).thenReturn(mockTournament);
        verify(service.tournamentPersistence).createTournament(captor.capture());
        assertEquals(mockTournament.getName(), captor.getValue().getName());
        assertEquals(mockTournament.getDescription(), captor.getValue().getDescription());
        assertEquals(mockTournament.getMaxEntrants(), captor.getValue().getMaxEntrants());
        assertEquals(mockTournament.getGame(), captor.getValue().getGame());
        assertEquals(mockTournament.getTournamentType(), captor.getValue().getTournamentType());
        assertEquals(mockTournament.getStartTime(), captor.getValue().getStartTime());
        assertEquals(mockTournament.getHostId(), captor.getValue().getHostId());
    }
    
    @Test 
    public void testEditTournamentDelegatesToPersistence(){
        boolean seed = false;
        service.editTournament(mockTournament, seed);
        verify(service.tournamentPersistence).editTournament(mockTournament);
    }
    
    @Test
    public void testGetTournamentDelegatesToPersistence(){
        String uuid = "test";
        service.getTournament(uuid);
        verify(service.tournamentPersistence).getTournament(uuid);
    }
}



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
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author cgmcandrews
 */
public class TournamentControllerTest {

    TournamentController controller = new TournamentController();

    String name;
    String description;
    int maxEntrants;
    String game;
    TournamentType type;
    String startTime;
    String hostId;
    SimpleDateFormat formatter;
    Date startTimeDate;
    Tournament mockTournament;
    String uuid;
    
    @Before
    public void setUp() throws ParseException {
        controller.tournamentService = mock(TournamentService.class);
        name = "testing";
        description = "desc";
        maxEntrants = 1;
        game = "Soccer";
        type = TournamentType.MULTI_STAGE;
        startTime = "Thu Jun 18 20:56:02 EDT 2009";
        hostId = "12";
        uuid = "334";
        formatter = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
        startTimeDate = formatter.parse(startTime);
    }

    @Test
    public void testCreateTournamentDelegatesToServiceLayer() throws Exception {

        when(controller.tournamentService.createTournament(name, description, maxEntrants, game, type, startTimeDate, hostId)).thenReturn(mockTournament);
        controller.createTournament(name, description, maxEntrants, game, type, startTime, hostId);
        verify(controller.tournamentService).createTournament(name, description, maxEntrants, game, type, startTimeDate, hostId);
    }

    @Test
    public void testGetTournamentDelegatesToServiceLayer() throws Exception {
        String uuid = "testing";
        when(controller.tournamentService.getTournament(uuid)).thenReturn(mockTournament);
        controller.getTournament(uuid);
        verify(controller.tournamentService).getTournament(uuid);
    }

    @Test
    public void testEditTournamentDelegatesToServiceLayer() throws Exception {
        boolean seed = true;
        when(controller.tournamentService.editTournament(mockTournament, seed)).thenReturn(mockTournament);
        controller.editTournament(uuid, name, description, maxEntrants, game, type, startTime, hostId, seed);
        verify(controller.tournamentService).editTournament(uuid, name, description, maxEntrants, game, type, startTimeDate, hostId, seed);
    }
}

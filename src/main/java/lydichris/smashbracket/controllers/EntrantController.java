/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.controllers;

import lydichris.smashbracket.models.Entrant;
import lydichris.smashbracket.services.EntrantService;
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
public class EntrantController {

    @Autowired
    EntrantService entrantService;

    public void setEntrantService(EntrantService entrantService) {
        this.entrantService = entrantService;
    }

    @RequestMapping(value = "/entrants", method = RequestMethod.GET)
    Entrant getEntrant(@RequestParam String uuid) {
        return entrantService.getEntrant(uuid);
    }

    @RequestMapping(value = "/entrants", method = RequestMethod.DELETE)
    boolean deleteEntrant(@RequestParam String uuid) {
        entrantService.deleteEntrant(uuid);
        return true;     
    }
    
    @RequestMapping(value = "/entrants", method = RequestMethod.POST)
    Entrant createEntrant(@RequestParam String tag,
            @RequestParam (value = "username", required = false) String username,
            @RequestParam (value = "password", required = false)  String password,
            @RequestParam String tournamentUuid)
            {
        return entrantService.createEntrant(tag, username, password, tournamentUuid);
    }

    @RequestMapping(value = "/entrants", method = RequestMethod.PUT)
    Entrant editEntrant(@RequestParam (value = "tag", required = false)  String tag,
            @RequestParam (value = "seed", required = false) Integer seed,
            @RequestParam String entrantUuid) {

        //Seed throws null pointer when not provided :/
        return entrantService.editEntrant(tag, seed, entrantUuid);
    }

}

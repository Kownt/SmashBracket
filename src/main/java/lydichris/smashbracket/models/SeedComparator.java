/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lydichris.smashbracket.models;

import java.util.Comparator;

/**
 *
 * @author cgmcandrews
 */
public class SeedComparator implements Comparator<Entrant> {

    @Override
    public int compare(Entrant o1, Entrant o2) {

        Integer e1 = o1.getSeed();
        Integer e2 = o2.getSeed();

        if (e1 > e2) {
            return 1;
        } else if (e1 < e2) {
            return -1;
        } else {
            return 0;
        }
    }

}

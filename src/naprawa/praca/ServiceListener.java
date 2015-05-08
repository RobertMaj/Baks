/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.praca;

import Model.TO_Defect;
import Model.praca.Czesc;
import Model.praca.Material;
import Model.praca.Naprawa;
import java.util.List;

/**
 *
 * @author RobertM
 */
public interface ServiceListener {

    void fireCzescTableChanged(List<Czesc> lista);

    void fireNaprawaTableChanged(List<Naprawa> lista);

    void fireMaterialTableChanged(List<Material> lista);

    void fireAktualizujDefect(TO_Defect defect);
}

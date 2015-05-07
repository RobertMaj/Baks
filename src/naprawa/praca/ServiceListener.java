/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package naprawa.praca;

import Model.praca.RodzajUslugi;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RobertM
 */
public interface ServiceListener {

    void fireCzescTableChanged();

    void fireNaprawaTableChanged();

    void fireMaterialTableChanged();

}

package com.example.gis_optik_20201.view;


import com.example.gis_optik_20201.model.optik.IsiItem_optik;

import java.util.List;


/**
 * This class represents the country view interface.
 *
 * @author Jean Carlos (Github: @jeancsanchez)
 * @date 09/03/18.
 * Jesus loves you.
 */
public interface optik_view {

    void optik(List<IsiItem_optik> optik);
    void status(String status,String pesan);


}

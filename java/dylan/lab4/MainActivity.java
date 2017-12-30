/*
    Name:     Dylan Coakley
    Username: x2014gvw
    ID:       201403528
    Purpose:  Assignment #2
    Date:     March 1st, 2016
 */

package dylan.lab4;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HotelListFragment hotelListFragment = new HotelListFragment();
        fragmentTransaction.add(R.id.fragment_container, hotelListFragment);
        fragmentTransaction.commit();
    }

}

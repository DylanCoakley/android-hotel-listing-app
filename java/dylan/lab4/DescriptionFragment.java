package dylan.lab4;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DescriptionFragment extends Fragment {

    private TextView clickedHotelName;
    private ImageView clickedHotelStars;
    private TextView clickedHotelAddress;
    private TextView clickedHotelPhone;
    private TextView clickedHotelPrice;
    private TextView edit_hotel;
    private TextView delete_hotel;
    private Button return_button;
    private HotelList myHotels;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.description_fragment_layout, container, false);

        final Bundle extras = this.getArguments();
        myHotels = extras.getParcelable("list");

        edit_hotel = (TextView) view.findViewById(R.id.edit_hotel);
        edit_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", extras.getInt("position"));
                bundle.putParcelable("list", myHotels);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                EditHotelFragment editHotelFragment = new EditHotelFragment();
                editHotelFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, editHotelFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        delete_hotel = (TextView) view.findViewById(R.id.delete_hotel);
        delete_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myHotels.remove(extras.getInt("position"));

                Toast.makeText(getActivity(), "Deleted!", Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putParcelable("list", myHotels);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                HotelListFragment hotelListFragment = new HotelListFragment();
                hotelListFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, hotelListFragment);
                fragmentTransaction.commit();
            }
        });

        clickedHotelName = (TextView) view.findViewById(R.id.clickedHotelName);
        clickedHotelStars = (ImageView) view.findViewById(R.id.clickedHotelStars);
        clickedHotelAddress = (TextView) view.findViewById(R.id.clickedHotelAddress);
        clickedHotelPhone = (TextView) view.findViewById(R.id.clickedHotelPhone);
        clickedHotelPrice = (TextView) view.findViewById(R.id.clickedHotelPrice);

        if(extras != null) {
            clickedHotelName.setText(extras.getString("name"));
            clickedHotelStars.setImageResource(extras.getInt("starsID"));
            clickedHotelAddress.setText("Address: " + extras.getString("address"));
            clickedHotelPhone.setText("Call: " + extras.getString("phone_number"));
            clickedHotelPrice.setText(extras.getString("fee") + "/night");
        }

        return_button = (Button) view.findViewById(R.id.return_button);
        return_button.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v){
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
            }
        });

        return view;
    }

}

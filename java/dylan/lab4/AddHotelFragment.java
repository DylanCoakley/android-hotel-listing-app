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
import android.widget.EditText;
import android.widget.Toast;

public class AddHotelFragment extends Fragment {

    private EditText new_hotel_name;
    private EditText new_hotel_rating;
    private EditText new_hotel_address;
    private EditText new_hotel_phone;
    private EditText new_hotel_fee;
    private Button add_button;
    private Button cancel_button;
    private HotelList myHotels;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_hotel_layout, container, false);

        Bundle bundle = this.getArguments();
        myHotels = bundle.getParcelable("list");

        new_hotel_name = (EditText) view.findViewById(R.id.new_hotel_name);
        new_hotel_rating = (EditText) view.findViewById(R.id.new_hotel_rating);
        new_hotel_address = (EditText) view.findViewById(R.id.new_hotel_address);
        new_hotel_phone = (EditText) view.findViewById(R.id.new_hotel_phone);
        new_hotel_fee = (EditText) view.findViewById(R.id.new_hotel_fee);

        add_button = (Button) view.findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int starID;
                switch (Integer.valueOf(new_hotel_rating.getText().toString())){
                    case 1:
                        starID = R.drawable.star1;
                        break;
                    case 2:
                        starID = R.drawable.star2;
                        break;
                    case 3:
                        starID = R.drawable.star3;
                        break;
                    case 4:
                        starID = R.drawable.star4;
                        break;
                    case 5:
                        starID = R.drawable.star5;
                        break;
                    default:
                        starID = R.drawable.star5;
                }

                myHotels.add(new Hotel(
                        new_hotel_name.getText().toString(),
                        starID,
                        new_hotel_address.getText().toString(),
                        new_hotel_phone.getText().toString(),
                        new_hotel_fee.getText().toString()));

                Toast.makeText(getActivity(), "Added!", Toast.LENGTH_SHORT).show();

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

        cancel_button = (Button) view.findViewById(R.id.cancel_button);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.popBackStack();
            }
        });

        return view;
    }
}

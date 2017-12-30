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

public class EditHotelFragment extends Fragment {

    private EditText edit_hotel_name;
    private EditText edit_hotel_rating;
    private EditText edit_hotel_address;
    private EditText edit_hotel_phone;
    private EditText edit_hotel_fee;
    private Button done_button;
    private Button cancel_button;
    private HotelList myHotels;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.edit_hotel_layout, container, false);

        Bundle bundle = this.getArguments();
        final int position = bundle.getInt("position");
        myHotels = bundle.getParcelable("list");

        edit_hotel_name = (EditText) view.findViewById(R.id.edit_hotel_name);
        edit_hotel_rating = (EditText) view.findViewById(R.id.edit_hotel_rating);
        edit_hotel_address = (EditText) view.findViewById(R.id.edit_hotel_address);
        edit_hotel_phone = (EditText) view.findViewById(R.id.edit_hotel_phone);
        edit_hotel_fee = (EditText) view.findViewById(R.id.edit_hotel_fee);

        edit_hotel_name.setText(myHotels.get(position).getName());
        switch(myHotels.get(position).getStars()) {
            case R.drawable.star1:
                edit_hotel_rating.setText("1");
                break;
            case R.drawable.star2:
                edit_hotel_rating.setText("2");
                break;
            case R.drawable.star3:
                edit_hotel_rating.setText("3");
                break;
            case R.drawable.star4:
                edit_hotel_rating.setText("4");
                break;
            case R.drawable.star5:
                edit_hotel_rating.setText("5");
                break;
            default:
        }
        edit_hotel_address.setText(myHotels.get(position).getAddress());
        edit_hotel_phone.setText(myHotels.get(position).getPhone_number());
        edit_hotel_fee.setText(myHotels.get(position).getFee());

        done_button = (Button) view.findViewById(R.id.done_button);
        done_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int starID;
                switch (Integer.valueOf(edit_hotel_rating.getText().toString())){
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

                myHotels.get(position).setName(edit_hotel_name.getText().toString());
                myHotels.get(position).setStars(starID);
                myHotels.get(position).setAddress(edit_hotel_address.getText().toString());
                myHotels.get(position).setPhone_number(edit_hotel_phone.getText().toString());
                myHotels.get(position).setFee(edit_hotel_fee.getText().toString());

                Toast.makeText(getActivity(), "Edited!", Toast.LENGTH_SHORT).show();

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

        cancel_button = (Button) view.findViewById(R.id.cancel_button2);
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

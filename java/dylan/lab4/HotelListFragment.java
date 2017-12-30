package dylan.lab4;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HotelListFragment extends Fragment {

    private TextView add_hotel;
    private ListView list_view;
    private HotelList myHotels;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hotel_list_fragment_layout, container, false);

        add_hotel = (TextView) view.findViewById(R.id.add_hotel);
        add_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("list", myHotels);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                AddHotelFragment addHotelFragment = new AddHotelFragment();
                addHotelFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, addHotelFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        populateHotelList();
        populateListView(view);
        registerClickCallback(view);

        return view;
    }

    private void populateHotelList() {

        Bundle bundle = getArguments();

        // BUNDLE MAY BE NULL
        if(bundle != null) {
            myHotels = bundle.getParcelable("list");
        } else {
            myHotels = new HotelList();
            myHotels.add(new Hotel("Cambridge Suites", R.drawable.star4, "380 Esplanade", "902-562-6500", "$135"));
            myHotels.add(new Hotel("Holiday Inn", R.drawable.star3, "300 Esplanade", "902-562-7500", "$144"));
            myHotels.add(new Hotel("Dove House", R.drawable.star4, "108 Queen Street", "902-794-7126", "$70"));
            myHotels.add(new Hotel("Clansman Motel", R.drawable.star2, "9 Baird Street", "902-794-7226", "$109"));
            myHotels.add(new Hotel("Hearthstone Inn", R.drawable.star3, "560 Kings Road", "902-539-8101", "$102"));
            myHotels.add(new Hotel("Hotel North", R.drawable.star3, "39 Forrest Street", "800-561-8585", "$109"));
        }
    }

    private class MyListAdapter extends ArrayAdapter<Hotel> {
        public MyListAdapter() {
            super(getActivity(), R.layout.item_view, myHotels);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if(itemView == null)
                itemView = getActivity().getLayoutInflater().inflate(R.layout.item_view, parent, false);

            Hotel currentHotel = myHotels.get(position);

            ImageView imageView = (ImageView)itemView.findViewById(R.id.hotel_stars);
            imageView.setImageResource(currentHotel.getStars());

            TextView nameText = (TextView) itemView.findViewById(R.id.hotel_name);
            nameText.setText(currentHotel.getName());

            TextView priceText = (TextView) itemView.findViewById(R.id.hotel_price);
            priceText.setText(currentHotel.getFee() + "/night");

            return itemView;
        }
    }

    private void populateListView(View view) {
        ArrayAdapter<Hotel> adapter = new MyListAdapter();
        list_view = (ListView) view.findViewById(R.id.hotelList);
        list_view.setAdapter(adapter);
    }

    private void registerClickCallback(View view) {
        ListView list = (ListView) view.findViewById(R.id.hotelList);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                Hotel clickedHotel = myHotels.get(position);

                Bundle bundle = new Bundle();
                bundle.putString("name", clickedHotel.getName());
                bundle.putInt("starsID", clickedHotel.getStars());
                bundle.putString("address", clickedHotel.getAddress());
                bundle.putString("phone_number", clickedHotel.getPhone_number());
                bundle.putString("fee", clickedHotel.getFee());
                bundle.putInt("position", position);
                bundle.putParcelable("list", myHotels);

                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                DescriptionFragment descriptionFragment = new DescriptionFragment();
                descriptionFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_container, descriptionFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }
}

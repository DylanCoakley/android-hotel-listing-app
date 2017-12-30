package dylan.lab4;

import java.util.ArrayList;
import android.os.Parcel;
import android.os.Parcelable;

public class HotelList extends ArrayList<Hotel> implements Parcelable {

    public HotelList(){}

    public HotelList(Parcel in){
        readFromParcel(in);
    }

    @SuppressWarnings("unchecked")
    public static final Creator CREATOR = new Creator() {
        public HotelList createFromParcel(Parcel in) {
            return new HotelList(in);
        }
        public Object[] newArray(int arg0){
            return null;
        }
    };

    private void readFromParcel(Parcel in) {
        this.clear();

        int size = in.readInt();

        for(int i = 0; i < size; i++) {
            Hotel h = new Hotel();
            h.setName(in.readString());
            h.setStars(in.readInt());
            h.setAddress(in.readString());
            h.setPhone_number(in.readString());
            h.setFee(in.readString());
            this.add(h);
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        int size = this.size();
        dest.writeInt(size);
        for(int i = 0; i < size; i++) {
            Hotel h = this.get(i);
            dest.writeString(h.getName());
            dest.writeInt(h.getStars());
            dest.writeString(h.getAddress());
            dest.writeString(h.getPhone_number());
            dest.writeString(h.getFee());
        }
    }
}

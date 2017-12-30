package dylan.lab4;

public class Hotel {
    private String name;
    private int starsID;
    private String address;
    private String phone_number;
    private String fee;

    public Hotel() {};

    public Hotel(String name, int starsID, String address, String phone_number, String fee) {
        super();
        this.name = name;
        this.starsID = starsID;
        this.address = address;
        this.phone_number = phone_number;
        this.fee = fee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getStars() {
        return starsID;
    }

    public void setStars(int stars) {
        this.starsID = stars;
    }

    public String getPhone_number() { return phone_number; }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getFee() { return fee;}

    public void setFee(String fee) { this.fee = fee; }

}

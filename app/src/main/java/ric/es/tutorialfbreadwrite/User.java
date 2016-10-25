package ric.es.tutorialfbreadwrite;

/**
 * Created by Ricardo on 22/10/2016.
 */

public class User {
    //name and address string
    private String id;
    private String name;
    private String address;

    public User() {
      /*Blank default constructor essential for Firebase*/
    }
    //Getters and setters


    public User(String id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}

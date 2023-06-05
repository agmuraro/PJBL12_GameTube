package src;

import java.util.ArrayList;

public class Usuario {
    private String name;
    private String username;
    private String email;
    private String password;
    private ArrayList<Game> biblioteca;
    private String profilePic;

    public Usuario(String email, String password, String name, String username){
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
        this.profilePic = profilePic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Game> getBiblioteca() {
        return biblioteca;
    }
}

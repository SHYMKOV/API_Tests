package api.DTO;

import api.DTO.Model.Categories;
import api.DTO.Model.NotificationTypes;
import api.Gender;
import org.json.JSONArray;

import java.util.List;

public class UserProfileDTO {

    private String birthday;
    private Gender gender;
    private String phone;
    private List<Object> myRates;
    private String name;
    private Boolean isBlocked;
    private int rating;
    private String id;
    private List<Categories> categories;
    private List<NotificationTypes> notificationTypes;
    private String email;
    private int attitude;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Object> getMyRates() {
        return myRates;
    }

    public void setMyRates(List<Object> myRates) {
        this.myRates = myRates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public List<NotificationTypes> getNotificationTypes() {
        return notificationTypes;
    }

    public void setNotificationTypes(List<NotificationTypes> notificationTypes) {
        this.notificationTypes = notificationTypes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAttitude() {
        return attitude;
    }

    public void setAttitude(int attitude) {
        this.attitude = attitude;
    }
}

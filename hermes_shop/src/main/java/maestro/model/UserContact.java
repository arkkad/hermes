package maestro.model;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "contacts")
public class UserContact {
    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "City")
    private String city;

    @OneToOne
    @PrimaryKeyJoinColumn
    private User user;

    public UserContact() {
    }

    public UserContact(String phone, String address, String city, User user) {
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() !=o.getClass()) return false;
        UserContact userContact = (UserContact) o;
        return Objects.equals(id, userContact.id) &&
                Objects.equals(phone, userContact.phone) &&
                Objects.equals(city, userContact.city) &&
                Objects.equals(address, userContact.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,phone,address,city);
    }

    public static class Builder{
        private Long id;
        private String phone;
        private String address;
        private String city;

        public Builder() {
        }

        public Builder(UserContact userContact) {
            this.id = userContact.id;
            this.phone = userContact.phone;
            this.address = userContact.address;
            this.city = userContact.city;
        }

        public UserContact build(){
            UserContact userContact = new UserContact();
            userContact.id = id;
            userContact.phone = phone;
            userContact.address = address;
            userContact.city = city;
            return userContact;
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }
    }
}

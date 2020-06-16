package maestro.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "name")
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    public Role() {
    }
    public Role(String name){
        this.name = name;
    }

    public Role(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

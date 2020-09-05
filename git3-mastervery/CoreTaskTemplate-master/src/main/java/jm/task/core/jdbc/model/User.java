package jm.task.core.jdbc.model;

import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;


    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Age")
    private Byte age;

    public User() {

    }

    public User(String name, String lastName, Byte age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof User))
            return false;
        if (obj == this)
            return true;
        User user = (User) obj;
        return this.getId().equals(user.getId()) && this.getName().equals(user.getName()) &&
                this.getLastName().equals(user.getLastName()) && this.getAge().equals(user.getAge());
    }

    public int hashCode() {
        int result = 0;
        result = 31 * (this.getId() == null ? 2 : getId().hashCode()) * (this.getName() == null ? 2 : getName().hashCode());
        result = result * 29 * (this.getLastName() == null ? 2 : getLastName().hashCode()) * (this.getAge() == null ? 2 : getAge().hashCode());
        return result;
    }

}

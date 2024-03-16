package br.senai.sc.m3s06.model;
import br.senai.sc.m3s06.enums.NotificationsEnum;
import br.senai.sc.m3s06.model.dto.operations.create.CreatePersonDTO;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Entity
public class Person implements UserDetails {
    @Id
    @Column(nullable = false, length = 36, unique = true)
    private String guid;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String phone;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationsEnum notificationsEnum;


    public Person(){

    }

    public Person(CreatePersonDTO createPersonDTO, String password){
        this.guid = UUID.randomUUID().toString();
        this.name = createPersonDTO.name();
        this.email = createPersonDTO.email();
        this.phone = createPersonDTO.phone();
        this.notificationsEnum = createPersonDTO.notificationsEnum();
        this.password = password;
    }

    public String getGuid() {
        return guid;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    public void setGuid(String guid) {
        this.guid = guid;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public NotificationsEnum getNotificationsEnum() {
        return notificationsEnum;
    }

    public void setNotificationsEnum(NotificationsEnum notificationsEnum) {
        this.notificationsEnum = notificationsEnum;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

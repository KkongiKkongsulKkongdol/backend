package com.teach.me.back.data.entity;

import com.teach.me.back.data.dto.user.request.ModifyNameRequest;
import com.teach.me.back.data.dto.user.response.FindUserResponse;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {

    private User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private Boolean isAdmin;

    private String password;


    public Long getUserId() {
        return id;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public FindUserResponse convertToFindUserResponse() {
        return new FindUserResponse(this.name, this.email, this.isAdmin);
    }

    public void modifyName(ModifyNameRequest request) {
        this.name = request.name();
    }


}

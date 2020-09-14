package edu.hunau.crowd.entity.vo;

import java.io.Serializable;

public class MemberLoginVO implements Serializable {
    private final static long serialVersionUID=1L;
    private Integer id;

    private String userName;

    private String email;

    public MemberLoginVO() {
    }

    public MemberLoginVO(Integer id,String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package com.demo.mybits.model;

import java.util.Date;

import lombok.Data;

/**
 * @author lichaoshuai
 * Created on 2022-01-24
 */
@Data
public class User {
    private long id;
    private String user_name;
    private String password;
    private String name;
    private Integer age;
    private Integer sex;
    private Date birthday;
    private String created;
    private String updated;
}

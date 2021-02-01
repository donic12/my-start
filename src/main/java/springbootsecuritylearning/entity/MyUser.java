package springbootsecuritylearning.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyUser {

    private String myUserName;
    private String myPassWord;

}

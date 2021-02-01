package springbootsecuritylearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springbootsecuritylearning.entity.MyUser;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("i am coming................");
        MyUser myUser = this.getUser();
        if (!username.equals(myUser.getMyUserName())) {
            throw new UsernameNotFoundException("user not exist....");
        }
        return new User(username, myUser.getMyPassWord(), AuthorityUtils.commaSeparatedStringToAuthorityList("admin,normal,ROLE_abc"));//角色必须是以ROLE_开头
    }

    private MyUser getUser() {
        return new MyUser("admin", passwordEncoder.encode("123"));
    }

}

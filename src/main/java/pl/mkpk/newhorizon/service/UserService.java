package pl.mkpk.newhorizon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mkpk.newhorizon.model.User;
import pl.mkpk.newhorizon.repository.RoleRepository;
import pl.mkpk.newhorizon.repository.UserRepository;

@Service("userService")
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean registerUser(User user){
        try {
            if (user.getPassword().equals(user.getMatchingPassword())){
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                user.setUserRole(roleRepository.findByName("USER"));
                userRepository.save(user);
                return true;
            }
        } catch(NullPointerException ex){
            ex.printStackTrace();
        }
        return false;
    }
}

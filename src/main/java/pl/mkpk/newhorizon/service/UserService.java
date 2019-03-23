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

//    public User saveUser(User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setActive(1);
//        Role userRole = roleRepository.findByName("ADMIN");
//        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
//        return userRepository.save(user);
//    }

    public boolean registerUser(User user){
        try {
            if (user.getPassword().equals(user.getMatchingPassword())){
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//                user.setMatchingPassword(bCryptPasswordEncoder.encode(user.getMatchingPassword()));
//                user.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findByName("USER"))));
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

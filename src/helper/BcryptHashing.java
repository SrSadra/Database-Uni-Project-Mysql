package helper;
// import org

public class BcryptHashing {
        public static String hashPassword(String password) {
            bcryptpa passwordEncoder = new BCryptPasswordEncoder();
            return passwordEncoder.encode(password);
        }
}

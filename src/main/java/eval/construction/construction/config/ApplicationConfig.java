package eval.construction.construction.config;
// package eval.cinepax.cinepax.config;

// import java.util.Optional;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
// import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import eval.cinepax.cinepax.model.magasin.MagasinUser;
// import eval.cinepax.cinepax.model.pdv.user.User;
// import eval.cinepax.cinepax.repository.magasin.MagasinUserRepository;
// import eval.cinepax.cinepax.repository.pdv.user.UserRepository;

// @Configuration
// public class ApplicationConfig {
//     @Autowired
//     MagasinUserRepository magasinUserRepository;
//     @Autowired
//     UserRepository userRepository;
    
//     @Bean
//     UserDetailsService userDetailsService() {
//         return username -> {
//             String mail=username.split(",")[0];
//             String role=username.split(",")[1];
//             Optional<MagasinUser> magasinUserOptional = magasinUserRepository.findByNomMagasinUserAndRole(mail, role);
//             if (magasinUserOptional.isPresent()) {
//                 return magasinUserOptional.get();
//             } else {
//                 // If not found in MagasinUserRepository, check UserRepository
//                 Optional<User> userOptional = userRepository.findByEmailUserAndRole(mail, role);
                
//                 return userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found"));
//             }
//         };
//     }

//     @Bean
//     // data aceees 
//     public AuthenticationProvider authenticationProvider(){
//         DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//         authProvider.setUserDetailsService(userDetailsService());
//         authProvider.setPasswordEncoder(passwordEncoder());
//         return authProvider;
//     }

//     @Bean
//     public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//         return config.getAuthenticationManager();
//     }

//     @Bean 
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder()  ;
//     }

// }

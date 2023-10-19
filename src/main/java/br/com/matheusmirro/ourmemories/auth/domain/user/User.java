// package br.com.matheusmirro.ourmemories.auth.domain.user;

// import java.util.Collection;
// import java.util.List;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import lombok.AllArgsConstructor;
// import lombok.Getter;

// @Getter
// @AllArgsConstructor

// public class User implements UserDetails {
//     @Id
//     @GeneratedValue(strategy = GenerationType.UUID)

//     private String id;
//     private String login;
//     private String password;
//     private UserRole role;

//     public User (String login, String password, UserRole role) {
//         this.login = login;
//         this.password = password;
//         this.role = role;
//     }

    

// }

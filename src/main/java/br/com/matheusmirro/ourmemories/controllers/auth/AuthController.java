package br.com.matheusmirro.ourmemories.controllers.auth;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.matheusmirro.ourmemories.auth.domain.user.AuthenticationDTO;
import br.com.matheusmirro.ourmemories.controllers.user.TokenRequest;
import br.com.matheusmirro.ourmemories.infra.security.TokenService;
import br.com.matheusmirro.ourmemories.model.user.UserModel;
import br.com.matheusmirro.ourmemories.repository.user.IUserRepository;

@RestController
public class AuthController {

    @Autowired
    TokenService tokenService;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        var userResponse = new HashMap<>();
        userResponse.put("user", data.username());

        // Create a main response object to encapsulate user and token responses
        var mainResponse = new HashMap<>();
        mainResponse.put("user", userResponse);
        mainResponse.put("token", token);

        return ResponseEntity.ok(mainResponse);
    }

    @CrossOrigin
    @PostMapping("/api/auth/validate")
    public ResponseEntity<Object> validateToken(@RequestBody TokenRequest request, AuthenticationDTO data) {

        String tokenToDecode = request.getToken();

        try {

            DecodedJWT decodedToken = JWT.decode(tokenToDecode);

            var decodedResponse = new HashMap<>();
            decodedResponse.put("decoded", decodedToken);

            if (decodedToken.getExpiresAt().before(new Date())) {
                return ResponseEntity.badRequest().body("Token expired");
            }

            var userResponse = new HashMap<>();
            userResponse.put("user", decodedToken.getSubject());

            var mainResponse = new HashMap<>();
            mainResponse.put("user", userResponse);
            mainResponse.put("decoded", decodedToken.getClaims());

            return ResponseEntity.ok().body(mainResponse);
        } catch (JWTDecodeException e) {
            return ResponseEntity.badRequest().body("Invalid token");
        }
    }
}
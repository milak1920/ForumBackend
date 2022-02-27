package com.esliceu.securityWeb.Controller;


import com.esliceu.securityWeb.Dto.*;

import com.esliceu.securityWeb.Model.User;
import com.esliceu.securityWeb.Model.UserMain;
import com.esliceu.securityWeb.Service.UserService;
import com.esliceu.securityWeb.jwt.JwtProvider;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;



@RestController
@ResponseBody
public class LoginController {
    Gson gson = new Gson();
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;
    UserService userService;
    JwtProvider jwtProvider;

    @Autowired
    public LoginController(PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           UserService userService, JwtProvider jwtProvider){

        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userService=userService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/register")
    public void nuevoUsuario(@Valid @RequestBody register register){
        userService.save(register, passwordEncoder.encode(register.getPassword()));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity("Campos mal", HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginUser.getEmail(),loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        UserMain usuario = (UserMain) authentication.getPrincipal();
        getprofile user = userService.getprofileUser(usuario.getEmail());
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities(),user);
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

    @GetMapping("/getprofile")
    public String getprofile(Authentication authentication) {
        try {
            UserMain usuario = (UserMain) authentication.getPrincipal();
            getprofile profile = userService.getprofileUser(usuario.getEmail());
            return gson.toJson(profile);
        } catch (Exception e) {
            return null;
        }
    }
    @PutMapping("/profile")
    public ResponseEntity<JwtDto> putProfile(@Valid @RequestBody getprofile getprofile, Authentication auth,
                                             BindingResult bindingResult){
        UserMain usuario = (UserMain) auth.getPrincipal();
        getprofile profile = userService.upProfile(getprofile, usuario.getEmail());

        User user = userService.getByEmail(profile.getEmail());

        if (bindingResult.hasErrors())
            return new ResponseEntity("Campos mal", HttpStatus.BAD_REQUEST);

        String jwt = jwtProvider.generateToken(auth);
        UserDetails userDetails = (UserDetails) auth.getPrincipal();

        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities(),profile);
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }

    @GetMapping("/profile/{avatarUrl}")
     public String avatarUrl() {

         return null;
    }

    @PutMapping("/profile/{password}")
    public void passwordProfilePut(@Valid @RequestBody password password, Authentication auth) {
        UserMain usuario = (UserMain) auth.getPrincipal();
        User user = userService.getByEmail(usuario.getEmail());
        if(passwordEncoder.matches(password.getCurrentPassword(),user.getPassword())){
            userService.newPassword(passwordEncoder.encode(password.getNewPassword()), user);
        }
    }

}

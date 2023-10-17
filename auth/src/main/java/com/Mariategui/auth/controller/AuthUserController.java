package com.Mariategui.auth.controller;

import com.Mariategui.auth.dto.AuthUserDto;
import com.Mariategui.auth.dto.CreateUserResponse;
import com.Mariategui.auth.entity.AuthUser;
import com.Mariategui.auth.entity.TokenDto;
import com.Mariategui.auth.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthUserController {
    @Autowired
    AuthUserService authUserService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserDto authUserDto) {
        TokenDto tokenDto = authUserService.login(authUserDto);
        if (tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token) {
        TokenDto tokenDto = authUserService.validate(token);
        if (tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }

    // @PostMapping("/create")
    // public ResponseEntity<?> create(@RequestBody AuthUserDto authUserDto) {
    // if (!authUserService.isPasswordConfirmed(authUserDto)) {
    // return ResponseEntity.badRequest().body("Password and confirm password do not
    // match.");
    // }

    // AuthUser authUser = authUserService.save(authUserDto);
    // if (authUser == null) {
    // return ResponseEntity.badRequest().build();
    // }

    // CreateUserResponse response = new CreateUserResponse();
    // response.setMessage("User created successfully.");
    // response.setUser(authUser);

    // return ResponseEntity.ok(response);
    // }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody AuthUserDto authUserDto) {
        if (!authUserService.isPasswordConfirmed(authUserDto)) {
            return ResponseEntity.badRequest().body("Password and confirm password do not match.");
        }

        AuthUser authUser = authUserService.save(authUserDto);
        if (authUser == null) {
            return ResponseEntity.badRequest().build();
        }

        CreateUserResponse response = new CreateUserResponse();
        response.setMessage("User created successfully.");
        response.setUser(authUser);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<List<AuthUser>> list() {
        List<AuthUser> userList = authUserService.listar();
        return ResponseEntity.ok(userList);
    }

    // @PutMapping("/update/{id}")
    // public ResponseEntity<AuthUser> update(@PathVariable Integer id, @RequestBody
    // AuthUserDto authUserDto) {
    // Optional<AuthUser> existingUser = authUserService.listarPorId(id);
    // if (!existingUser.isPresent())
    // return ResponseEntity.notFound().build();

    // AuthUser updatedUser = existingUser.get();
    // updatedUser.setEmail(authUserDto.getEmail());
    // updatedUser.setPassword(authUserDto.getPassword());
    // updatedUser.setRole(authUserDto.getRole());
    // updatedUser.setName(authUserDto.getName());
    // updatedUser.setConfirmPassword(authUserDto.getConfirmPassword());

    // AuthUser savedUser = authUserService.actualizar(updatedUser);
    // return ResponseEntity.ok(savedUser);
    // }

    @PutMapping("/update/{id}")
    public ResponseEntity<AuthUser> update(@PathVariable Integer id, @RequestBody AuthUserDto authUserDto) {
        Optional<AuthUser> existingUser = authUserService.listarPorId(id);
        if (!existingUser.isPresent())
            return ResponseEntity.notFound().build();

        AuthUser updatedUser = existingUser.get();

        // Actualiza las propiedades del usuario con los valores proporcionados en
        // authUserDto, excepto la contraseña
        updatedUser.setEmail(authUserDto.getEmail());
        updatedUser.setRole(authUserDto.getRole());
        updatedUser.setName(authUserDto.getName());

        // Verifica si la contraseña ha sido modificada y la encripta
        if (!authUserDto.getPassword().equals(existingUser.get().getPassword())) {
            String password = passwordEncoder.encode(authUserDto.getPassword());
            updatedUser.setPassword(password);
        }

        AuthUser savedUser = authUserService.actualizar(updatedUser);
        CreateUserResponse response = new CreateUserResponse();
        response.setMessage("Usuario actualizado con éxito.");
        response.setUser(savedUser);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthUser> listById(@PathVariable(required = true) Integer id) {
        return ResponseEntity.ok().body(authUserService.listarPorId(id).get());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        Optional<AuthUser> existingUser = authUserService.listarPorId(id);
        if (!existingUser.isPresent())
            return ResponseEntity.notFound().build();

        authUserService.eliminarPorId(id);
        return ResponseEntity.ok("User deleted successfully.");
    }
}
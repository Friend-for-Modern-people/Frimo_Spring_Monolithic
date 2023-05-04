package com.gachon.frimo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gachon.frimo.service.UserService;
import com.gachon.frimo.web.dto.UserDto;

@Controller
@RequestMapping(path = "/app/user")
public class UserController {
    @Autowired
    UserService userService;

    /*
     * 회원가입 API
     * 
     * @RequestBody UserDto.registRequestDto
     * 
     * @return 201 CREATED , saved
     */
    @PostMapping(path = "")
    public ResponseEntity<String> RegistUser(@RequestBody UserDto.RegistRequestDto regist) {
        userService.UserRegist(regist);
        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }

    /*
     * 닉네임 중복확인 API
     * 
     * @variable usernickname
     * 
     * @return ok
     */
    @GetMapping(path = "/NNcheck/{userNN}")
    public ResponseEntity<Boolean> checkUserNNDuplicate(@PathVariable(value = "userNN") String nickname) {
        return ResponseEntity.ok(userService.checkUserNNDuplicate(nickname));
    }

    /*
     * 회원탈퇴 API
     * 
     * @variable userPk
     * 
     * @return ok, "# is deleted"
     */
    @DeleteMapping(path = "/{userPk}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "userPk") Long userPk) {
        userService.deleteUser(userPk);
        return ResponseEntity.ok("deleted");
    }

    /*
     * 회원조회 API
     * 
     * @variable userPk
     * 
     * @return ok, userDto.GetUserOnlyInfoResponseDto
     */
    @GetMapping(path = "/{userPk}")
    public ResponseEntity<UserDto.GetUserOnlyInfoResponseDto> getUserInfo(@PathVariable(value = "userPk") Long userPk) {
        return ResponseEntity.ok(userService.getUserInfoResponseDto(userPk));
    }

    /*
     * 닉네임 -> pk찾기 API
     * 
     * @variable userNN
     * 
     * @return ok, userDto.
     */
    @GetMapping(path = "/userinfo/{userNN}")
    public ResponseEntity<Long> getUserPk(@PathVariable(value = "userNN") String userNN) {
        return ResponseEntity.ok(userService.getUserPk(userNN));
    }

}
//
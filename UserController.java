package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.Service.UserService;
import net.javaguides.springboot.tutorial.entity.UserRoles;
import net.javaguides.springboot.tutorial.model.AjaxResponseBody;
import net.javaguides.springboot.tutorial.model.SearchCriteria;
import net.javaguides.springboot.tutorial.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final UserRolesRepository userRolesRepository;

     UserService userService;

    public UserController(UserRolesRepository userRolesRepository) {
        this.userRolesRepository = userRolesRepository;
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String getUsersRoles(Model model){

    model.addAttribute("users",userRolesRepository.findAll());
        initModelList(model);
        return"user-roles";
    }

    private void initModelList(Model model) {
        List<String> list = new ArrayList<String>();
        list.add("Admin");
        list.add("Developer");
        list.add("Approver");
        model.addAttribute("roles",list);
    }

    @PostMapping
    public String addUsersRoles(@Valid UserRoles users, Model model){
        userRolesRepository.save(users);
        model.addAttribute("users",userRolesRepository.findAll());
        initModelList(model);
        return "user-roles";
    }


    @GetMapping("/{id}")
    public String romoveRoles(@PathVariable("id") Long id, Model model){
        UserRoles user=  userRolesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User Id:" + id));
        userRolesRepository.delete(user);
        model.addAttribute("users",userRolesRepository.findAll());
        return "user-roles";
    }

   /* @PostMapping("/{userPid}")
    public ResponseEntity<?> getSearchResultViaAjax(@PathVariable("userPid") String userPid , Errors errors) {

        AjaxResponseBody result = new AjaxResponseBody();

        //If error, just return a 400 bad request, along with the error message
        if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(result);

        }

        List<UserRoles> users = userService.findUserPid(userPid);



        if (users.isEmpty()) {
            result.setMsg("no user found!");
        } else {
            result.setMsg("success");
        }
        result.setResult(users);

        return ResponseEntity.ok(result);

    }*/

    @PostMapping("/{userPid}")
    public String getSearchResultViaAjax(@PathVariable("userPid") String userPid , Model model) {


        model.addAttribute("users",userRolesRepository.findAll());
        initModelList(model);
        //If error, just return a 400 bad request, along with the error message
       /* if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(result);

        }*/
        Iterable<UserRoles> users= userRolesRepository.findAll();

        for (UserRoles ur:users) {
            if(ur.getUserPid().equals(userPid)){
                System.out.println("pid is there");
            }
        }

        return "user-roles";

    }
}

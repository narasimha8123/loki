package net.javaguides.springboot.tutorial.controller;

import net.javaguides.springboot.tutorial.Service.UserService;
import net.javaguides.springboot.tutorial.entity.Roles;
import net.javaguides.springboot.tutorial.entity.UserRoles;
import net.javaguides.springboot.tutorial.repository.UserDefinationRepository;
import net.javaguides.springboot.tutorial.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/roles")
@Controller
public class UserDefinationController {

    @Autowired
    private final UserDefinationRepository userDefinationRepository;

    UserService userService;

    public UserDefinationController(UserDefinationRepository userDefinationRepository) {
        this.userDefinationRepository = userDefinationRepository;
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String getUsersRoles(Model model) {

        model.addAttribute("users", userDefinationRepository.findAll());
        initModelList(model);
        initContext(model);
        return "user-defination";
    }

    private void initContext(Model model) {
        List<String> contextList = new ArrayList<String>();
        contextList.add("New Requester");
        contextList.add("New Role");
        model.addAttribute("contexts", contextList);
    }

    private void initModelList(Model model) {
        List<String> list = new ArrayList<String>();
        list.add("Approver");
        list.add("Reviwer");
        model.addAttribute("roles", list);
    }

    @PostMapping
    public String addUsersRoles(@Valid Roles role, Model model) {
        userDefinationRepository.save(role);
        model.addAttribute("roles", userDefinationRepository.findAll());
        initModelList(model);
        initContext(model);
        return "user-defination";
    }


    @GetMapping("/{id}")
    public String romoveRoles(@PathVariable("id") Long id, Model model) {
        Roles user = userDefinationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User Id:" + id));
        userDefinationRepository.delete(user);
        model.addAttribute("users", userDefinationRepository.findAll());
        return "user-defination";
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

   /* @PostMapping("/{userPid}")
    public String getSearchResultViaAjax(@PathVariable("userPid") String userPid , Model model) {


        model.addAttribute("users",userDefinationRepository.findAll());
        initModelList(model);
        //If error, just return a 400 bad request, along with the error message
       *//* if (errors.hasErrors()) {

            result.setMsg(errors.getAllErrors()
                    .stream().map(x -> x.getDefaultMessage())
                    .collect(Collectors.joining(",")));

            return ResponseEntity.badRequest().body(result);

        }*//*
        Iterable<UserRoles> users= userDefinationRepository.findAll();

        for (UserRoles ur:users) {
            if(ur.getUserPid().equals(userPid)){
                System.out.println("pid is there");
            }
        }

        return "user-defination";

    }*/

}

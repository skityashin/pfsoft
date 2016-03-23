package com.controller;


import com.dto.UserDto;
import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Class {@link UserController}
 *
 * @author Skityashin Vladimir
 * @version 1.0
 * @since 23.03.16
 */

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showForm() {

        return "login";
    }

    /**
     * @param userDto
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUser(@ModelAttribute UserDto userDto) {
        User user = userService.findByLogin(userDto.getLogin());
        if (user == null) {
            return "redirect:/user/login";
        }
        return "file";
    }

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public String workFile(Model model) {
            model.addAttribute("description", "ghfdkjghfdkj");

        return "result";
    }


    public String good() {
//        String st = "(1-(5*10)+(2-1))";
//
//        Queue queue = new PriorityQueue();
//        for (char s : st.toCharArray()) {
//            if (s != '(' && s != ')') {
//                continue;
//            }
//            if (queue.isEmpty()) {
//                queue.add(s);
//                continue;
//            }
//            if (s == (queue.element())) {
//                queue.add(s);
//            } else {
//                queue.remove();
//            }
//
//        }
//
//        if (queue.isEmpty()) {
//            System.out.println("Syntax OK");
//        } else {
//
//            System.out.println(queue);
//        }
//
//
//        Integer a = 128;
//        Integer b = 128;
//        System.out.println(a == b);
//
//
//        String test = "String test";
//        System.out.println(st.intern());

        return null;
    }

}

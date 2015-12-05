package win.trytryagain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by momo on 15-12-2.
 */
@Controller
@RequestMapping("index")
public class IndexController {
    @RequestMapping("")
    public String index(){
        return "/index";
    }
}

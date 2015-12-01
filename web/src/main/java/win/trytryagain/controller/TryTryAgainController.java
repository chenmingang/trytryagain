package win.trytryagain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import win.trytryagain.service.TryTryAgainService;

/**
 * Created by momo on 15-12-1.
 */
@Controller
@RequestMapping("try")
public class TryTryAgainController {
    @Autowired
    TryTryAgainService tryTryAgainService;


}

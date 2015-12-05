package win.trytryagain.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import win.trytryagain.model.TryTryAgain;
import win.trytryagain.model.TryTryAgainCriteria;
import win.trytryagain.service.TryTryAgainService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by momo on 15-12-1.
 */
@Controller
@RequestMapping("try")
public class TryTryAgainController {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
    @Autowired
    TryTryAgainService tryTryAgainService;

    @RequestMapping("")
    public String page() {
        return "try/page";
    }

    @RequestMapping("list")
    @ResponseBody
    public String list(HttpServletRequest request) {
        TryTryAgainCriteria criteria = new TryTryAgainCriteria();
        criteria.setLimitStart(0);
        criteria.setLimitEnd(100);
        return gson.toJson(tryTryAgainService.query(criteria));
    }

    @RequestMapping("save")
    @ResponseBody
    public String save(TryTryAgain tryTryAgain) {
        return gson.toJson(tryTryAgainService.save(tryTryAgain));
    }

    @RequestMapping("get")
    @ResponseBody
    public String get(Integer id) {
        return gson.toJson(tryTryAgainService.get(id));
    }
}

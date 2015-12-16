package win.trytryagain.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import win.trytryagain.model.TryTryAgain;
import win.trytryagain.model.TryTryAgainCriteria;
import win.trytryagain.service.TryTryAgainService;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by momo on 15-12-1.
 */
@Controller
@RequestMapping("")
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
        TryTryAgainCriteria.Criteria c1 = criteria.createCriteria();
        TryTryAgainCriteria.Criteria c2 = criteria.createCriteria();
        String keyword = request.getParameter("keyword");
        if (keyword != null && !keyword.equalsIgnoreCase("")) {
            c1.andTagsLike("%" + keyword + "%");
            c2.andTitleLike("%" + keyword + "%");
        }
        criteria.or(c2);
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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
    }
}

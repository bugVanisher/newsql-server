package com.mbappe.newsql.controller;


import com.mbappe.newsql.base.AjaxResponseBody;
import com.mbappe.newsql.constants.StatusCode;
import com.mbappe.newsql.user.persistence.ddl.AppInfoDO;
import com.mbappe.newsql.user.persistence.ddl.UserDO;
import com.mbappe.newsql.user.services.UserService;
import com.mbappe.newsql.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/api/getApps")
    public AjaxResponseBody<List<AppInfoDO>> getTableNames() {
        UserDO userDO = currentUser();
        List<AppInfoDO> appInfoDOList = userService.getAppInfosByUid(userDO.getId());
        AjaxResponseBody<List<AppInfoDO>> result = new AjaxResponseBody<>(appInfoDOList);
        result.setSuccess(true);
        result.setCode(StatusCode.SUCCESS.getCode());
        return result;
    }

    @GetMapping("/api/getUser")
    public AjaxResponseBody<UserDO> getUser() {
        AjaxResponseBody<UserDO> result = new AjaxResponseBody<>(currentUser());
        result.setSuccess(true);
        result.setCode(StatusCode.SUCCESS.getCode());
        return result;
    }


}

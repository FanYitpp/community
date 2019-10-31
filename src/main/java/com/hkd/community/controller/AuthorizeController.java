package com.hkd.community.controller;

import com.hkd.community.dto.AccessTokenDTO;
import com.hkd.community.dto.GitUser;
import com.hkd.community.mapper.UserMapper;
import com.hkd.community.model.User;
import com.hkd.community.provider.GitProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GitProvider gitProvider;

    @Autowired
    private UserMapper userMapper;

    @Value("${git.client.id}")
    private String clientId;

    @Value("${git.client.secret}")
    private String clientSecret;

    @Value("${git.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest httpServletRequest){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);

        String accessToken = gitProvider.getAccessToken(accessTokenDTO);
        GitUser gitUser = gitProvider.getUser(accessToken);
        if(gitUser!=null){
            User user = new User();
            user.setName(gitUser.getName());
            user.setToken(UUID.randomUUID().toString());
            user.setAccountId(String.valueOf(gitUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);
            //登陆成功，写cookie和session
            httpServletRequest.getSession().setAttribute("user",gitUser);
            return "redirect:/";
        }else{
            return "redirect:/";
        }
    }
}

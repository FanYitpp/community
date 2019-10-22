package com.hkd.community.controller;

import com.hkd.community.dto.AccessTokenDTO;
import com.hkd.community.dto.GitUser;
import com.hkd.community.provider.GitProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GitProvider gitProvider;

    @Value("${git.client.id}")
    private String clientId;

    @Value("${git.client.secret}")
    private String clientSecret;

    @Value("${git.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);

        String accessToken = gitProvider.getAccessToken(accessTokenDTO);
        GitUser gitUser = gitProvider.getUser(accessToken);
        System.out.println(gitUser.getName());
        return "index";
    }
}

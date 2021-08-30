package com.ark.inflearnback.domain.security.service;

import com.ark.inflearnback.domain.security.dto.OAuthAttributes;
import com.ark.inflearnback.domain.security.dto.SessionMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Service
public class CustomOauth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final HttpSession httpSession;
    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        OAuthAttributes oAuthAttributes = OAuthAttributes.of(oAuth2User, userRequest, oAuth2User.getAttribute("email_verified"));

        httpSession.setAttribute("Oauth20Authentication", new SessionMember(oAuthAttributes.getEmail()));

        return oAuthAttributes.customDefaultOauth2User();
    }
}

package com.easylose.backend.security.oauth2;

import com.easylose.backend.api.v1.domain.User;
import com.easylose.backend.api.v1.repository.UserRepository;
import com.easylose.backend.security.jwt.JwtService;
import com.easylose.backend.security.jwt.TokenDto;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  private final UserRepository userRepository;
  private final JwtService jwtService;
  private @Value("${easylose.frontend-redirect-host}") String frontendRedirectHost;
  private @Value("${easylose.frontend-redirect-scheme}") String frontendRedirectScheme;

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request, HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
    OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
    log.info("OAuth2User: {}\n", oAuth2User);
    log.info("OAuth2User: {}\n", oAuth2User.getAttributes());

    Map<String, Object> attributes = oAuth2User.getAttributes();

    String providerId = (String) attributes.get("id");
    String provider = (String) attributes.get("provider");
    String name = (String) attributes.get("name");
    String profileImg = (String) attributes.get("img");

    User user;
    List<User> queryResult = userRepository.findByProviderIdAndProvider(providerId, provider);
    if (queryResult.isEmpty()) {
      user =
          User.builder()
              .providerId(providerId)
              .provider(provider)
              .name(name)
              .profileImg(profileImg)
              .build();

      user = userRepository.save(user);

    } else {
      user = queryResult.get(0);
      user.setProviderId(providerId);
      user.setProvider(provider);
      user.setName(name);
      user.setProfileImg(profileImg);

      user = userRepository.save(user);
    }

    TokenDto token = jwtService.create(user);

    user.setRefreshJws(token.getRefreshJws());
    userRepository.save(user);

    String uri =
        UriComponentsBuilder.newInstance()
            .scheme(frontendRedirectScheme)
            .host(frontendRedirectHost)
            .path("/auth/redirect")
            .queryParam("accessToken", token.getAccessJws())
            .queryParam("refreshToken", token.getRefreshJws())
            .build()
            .toUriString();

    log.info(uri);

    getRedirectStrategy().sendRedirect(request, response, uri);
  }
}

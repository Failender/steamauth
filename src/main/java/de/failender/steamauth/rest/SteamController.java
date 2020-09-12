package de.failender.steamauth.rest;

import com.lukaspradel.steamapi.data.json.playersummaries.Player;
import de.failender.steamauth.integration.SteamRestService;
import de.failender.steamauth.integration.SteamService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SteamController {

    private final SteamRestService steamRestService;
    private final SteamService steamService;
    private final String redirectUri;

    public SteamController(SteamRestService steamRestService, SteamService steamService,
                           @Value("${de.failender.steamauth.redirect.uri:http://localhost:8080/index.html}") String redirectUri) {
        this.steamRestService = steamRestService;
        this.steamService = steamService;
        this.redirectUri = redirectUri;
    }

    @GetMapping("/api/steam/authenticate")
    public String getSteamAuthenticationLink() {
        return steamService.login(redirectUri);
    }

    @PostMapping("/api/steam/verify")
    public Player verify(@RequestBody Map<String, String> params) {
        String result = steamService.verify(redirectUri, params);
        if (result != null) {
            return steamRestService.getUser(result);

        }
        return null;
    }
}

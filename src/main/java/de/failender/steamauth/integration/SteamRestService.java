package de.failender.steamauth.integration;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.playersummaries.GetPlayerSummaries;
import com.lukaspradel.steamapi.data.json.playersummaries.Player;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SteamRestService {

    private final SteamWebApiClient apiClient;

    public SteamRestService(@Value("${de.failender.steamauth.steam.api.key}") String apiKey) {

        if (apiKey.isEmpty()) {
            apiClient = null;
            return;
        }

        this.apiClient = new SteamWebApiClient.SteamWebApiClientBuilder(apiKey).build();

    }


    public Player getUser(String openId) {
        try {

            GetPlayerSummaries summaries = apiClient.processRequest(SteamWebApiRequestFactory.createGetPlayerSummariesRequest(Collections.singletonList(openId)));
            if (summaries.getResponse().getPlayers().isEmpty()) {
                return null;
            }
            return summaries.getResponse().getPlayers().get(0);
        } catch (SteamApiException e) {
            e.printStackTrace();
            return null;
        }

    }
}

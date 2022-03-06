package ru.sugarisboy.home.core.rest.extractor.feature.service;

import ru.sugarisboy.home.core.station.api.DialogManagerApi;
import ru.sugarisboy.home.core.station.api.dialogs.request.DialogInputDto;
import ru.sugarisboy.home.core.station.api.dialogs.response.DialogOutputDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DialogService {

    private final DialogManagerApi dialogApi = DialogManagerApi.getInstance();

    public DialogOutputDto resolve(String urlPath, DialogInputDto request) {
        return dialogApi.resolveRequest(urlPath, request).getOutput();
    }
}

package ru.sugarisboy.home.core.station.api;


import static ru.sugarisboy.home.core.common.api.utils.CollectionUtils.isEmpty;
import static ru.sugarisboy.home.core.common.api.utils.StringUtils.containsAnyIgnoreCase;
import static ru.sugarisboy.home.core.common.api.utils.StringUtils.startWithAnyIgnoreCase;

import ru.sugarisboy.home.core.station.api.dialogs.usage.CommandDecoder;
import ru.sugarisboy.home.core.station.api.dialogs.usage.CommandInstance;
import ru.sugarisboy.home.core.station.api.dialogs.usage.CommandPhrase;
import ru.sugarisboy.home.core.station.api.dialogs.usage.CommandResponse;
import ru.sugarisboy.home.core.station.api.dialogs.request.DialogInputDto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class DialogManagerApi {

    private final Set<CommandDecoder> decoders = new HashSet<>();

    public DialogManagerApi addDecoder(CommandDecoder decoder) {
        decoders.add(decoder);
        log.info("Added decoder for urlPath [{}]", decoder.getUrlPath());
        return this;
    }

    public CommandResponse resolveRequest(String urlPath, DialogInputDto input) {
        List<CommandDecoder> decodersByUrl = decoders.stream()
                .filter(decoder -> decoder.getUrlPath().equalsIgnoreCase(urlPath))
                .collect(Collectors.toList());

        val req = input.getRequest();
        val command = req.getCommand();

        boolean isCorrect;
        for (CommandDecoder decoder : decodersByUrl) {
            for (CommandInstance commandInstance : decoder.getCommands()) {
                for (CommandPhrase phrase : commandInstance.getPhrases()) {
                    Set<String> startWith = phrase.getStartWith();
                    Set<String> contains = phrase.getContains();

                    isCorrect = (isEmpty(startWith) || startWithAnyIgnoreCase(command, startWith))
                            && (isEmpty(contains) || containsAnyIgnoreCase(command, contains));

                    if (isCorrect) {
                        return commandInstance.getReduce().apply(req);
                    }
                }
            }
        }

        for (CommandDecoder decoder : decoders) {
            Function<DialogInputDto, CommandResponse> orElse = decoder.getOrElse();
            if (orElse != null) {
                return orElse.apply(input);
            }

            val orElseThrow = decoder.getOrElseThrow();
            if (decoder.getOrElseThrow() != null) {
                throw orElseThrow.apply(input);
            }
        }

        log.error("Not found command decoder for message with urlPath = {}: {}", urlPath, input);
        throw new RuntimeException("Not found command decoder for command: " + command);
    }

    private static class SingletonHolder {
        public static final DialogManagerApi HOLDER_INSTANCE = new DialogManagerApi();
    }

    public static DialogManagerApi getInstance() {
        return DialogManagerApi.SingletonHolder.HOLDER_INSTANCE;
    }
}

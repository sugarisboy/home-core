package ru.sugarisboy.home.core.station.api.dialogs.usage;

import ru.sugarisboy.home.core.station.api.dialogs.request.DialogRequest;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class CommandInstance {

    private final Set<CommandPhrase> phrases;
    private final Function<DialogRequest, CommandResponse> reduce;

    @Accessors(chain = true)
    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    public static class Builder {
        private final CommandDecoder.Builder decoderBuilder;

        final Set<CommandPhrase> phrases = new HashSet<>();

        public CommandPhrase.Builder addPhrase() {
            return CommandPhrase.builder(this);
        }

        public CommandDecoder.Builder then(Function<DialogRequest, CommandResponse> reduce) {
            CommandInstance command = new CommandInstance(phrases, reduce);
            decoderBuilder.commands.add(command);
            return decoderBuilder;
        }
    }

    public static Builder builder(CommandDecoder.Builder decoderBuilder) {
        return new Builder(decoderBuilder);
    }
}

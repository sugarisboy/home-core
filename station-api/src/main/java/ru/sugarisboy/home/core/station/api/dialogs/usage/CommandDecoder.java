package ru.sugarisboy.home.core.station.api.dialogs.usage;

import ru.sugarisboy.home.core.station.api.dialogs.request.DialogInputDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class CommandDecoder {

    private final Set<CommandInstance> commands;
    private final String urlPath;
    private final Function<DialogInputDto, CommandResponse> orElse;
    private final Function<DialogInputDto, RuntimeException> orElseThrow;

    @NoArgsConstructor(access = AccessLevel.PACKAGE)
    public static class Builder {
        final Set<CommandInstance> commands = new HashSet<>();

        private String urlPath;

        public CommandInstance.Builder addCommand() {
            return CommandInstance.builder(this);
        }

        public CommandDecoder.Builder urlPath(String urlPath) {
            this.urlPath = urlPath;
            return this;
        }

        public CommandDecoder orElse(Function<DialogInputDto, CommandResponse> function) {
            return new CommandDecoder(commands, urlPath, function, null);
        }
        public CommandDecoder orElseThrow(Function<DialogInputDto, RuntimeException> function) {
            return new CommandDecoder(commands, urlPath, null, function);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}

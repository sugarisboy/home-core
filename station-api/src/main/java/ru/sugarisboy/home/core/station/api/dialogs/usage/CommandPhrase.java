package ru.sugarisboy.home.core.station.api.dialogs.usage;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class CommandPhrase {

    private final Set<String> startWith;
    private final Set<String> contains;

    @RequiredArgsConstructor(access = AccessLevel.PACKAGE)
    public static class Builder {
        private final CommandInstance.Builder instanceBuilder;
        private final Set<String> startWith = new HashSet<>();
        private final Set<String> contains = new HashSet<>();

        public Builder startWith(String startPhrase) {
            this.startWith.add(startPhrase);
            return this;
        }

        public Builder contains(String startPhrase) {
            this.contains.add(startPhrase);
            return this;
        }

        public CommandInstance.Builder end() {
            CommandPhrase phrase = new CommandPhrase(startWith, contains);
            instanceBuilder.phrases.add(phrase);
            return instanceBuilder;
        }
    }

    public static Builder builder(CommandInstance.Builder instanceBuilder) {
        return new Builder(instanceBuilder);
    }
}

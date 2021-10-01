package ru.sugarisboy.home.core.station.api.dialogs.usage;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class TtsMessage {

    @NoArgsConstructor(access = AccessLevel.PACKAGE)
    @AllArgsConstructor(access = AccessLevel.PACKAGE)
    public static class Builder {
        private CommandResponse.Builder responseBuilder;

        private final StringBuilder builder = new StringBuilder();

        public Builder add(String s) {
            builder.append(s);
            return this;
        }

        public Builder addStress() {
            builder.append('+');
            return this;
        }

        public Builder addPause(int ms) {
            builder.append(String.format(" sil <[%s]>", ms));
            return this;
        }

        public Builder addAudio(StationAudio audio) {
            builder.append(String.format("<speaker audio=\"%s\">", audio.getFile()));
            return this;
        }

        public Builder addWithEffect(String s, StationAudioEffect effect) {
            builder.append(String.format("<speaker effect=\"%s\">%s<speaker effect=\"-\">", effect.getRaw(), s));
            return this;
        }

        public CommandResponse.Builder speechEnd() {
            responseBuilder.tts = builder.toString();
            return responseBuilder;
        }

        public String asString() {
            return builder.toString();
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(CommandResponse.Builder responseBuilder) {
        return new Builder(responseBuilder);
    }
}

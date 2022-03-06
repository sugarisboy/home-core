package ru.sugarisboy.home.core.station.api.dialogs.usage;

import ru.sugarisboy.home.core.station.api.dialogs.response.DialogOutputDto;
import ru.sugarisboy.home.core.station.api.dialogs.response.DialogResponse;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class CommandResponse {

    @Getter
    private final DialogOutputDto output;

    @NoArgsConstructor(access = AccessLevel.PACKAGE)
    public static class Builder {
        String text;
        String tts;
        boolean endSession = false;

        public Builder previewText(String text) {
            this.text = text;
            return this;
        }

        public TtsMessage.Builder speech() {
            return TtsMessage.builder(this);
        }

        public Builder speech(String speech) {
            this.tts = speech;
            return this;
        }

        public Builder endSession() {
            this.endSession = true;
            return this;
        }

        public CommandResponse build() {
            return new CommandResponse(new DialogOutputDto(new DialogResponse(text, tts, endSession), "1.0"));
        }
    }

    public static Builder builder() {
        return new Builder();
    }

}

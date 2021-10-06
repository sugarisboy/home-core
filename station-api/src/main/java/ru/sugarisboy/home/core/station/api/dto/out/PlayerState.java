package ru.sugarisboy.home.core.station.api.dto.out;

import ru.sugarisboy.home.core.station.api.dto.in.StationPlayerStateType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerState {

    private PlayerStateExtra extra;
    private float duration;
    private boolean hasNext;
    private boolean hasPrev;
    private boolean hasPause;
    private boolean hasPlay;
    private boolean hasProgressBar;
    private int id;
    private String liveStreamText;
    private String playerType;
    private String playlistDescription;
    private String playlistId;
    private int progress;
    private boolean showPlayer;
    private String subtitle;
    private String title;
    private StationPlayerStateType type;
}

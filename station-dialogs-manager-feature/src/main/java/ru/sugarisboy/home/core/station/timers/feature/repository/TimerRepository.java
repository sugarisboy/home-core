package ru.sugarisboy.home.core.station.timers.feature.repository;

import ru.sugarisboy.home.core.station.timers.feature.model.Timer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface TimerRepository extends JpaRepository<Timer, UUID> {

    @Query(value = "" +
            " SELECT t " +
            " FROM Timer t " +
            " WHERE t.finish >= :finishBefore " +
            " ORDER BY t.finish"
    )
    List<Timer> findFinishBefore(@Param("finishBefore") OffsetDateTime finishBefore);
}

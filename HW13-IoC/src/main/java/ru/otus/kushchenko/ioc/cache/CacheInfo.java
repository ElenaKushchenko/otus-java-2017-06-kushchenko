package ru.otus.kushchenko.ioc.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CacheInfo {
    private final int maxElements;
    private final long lifeTimeMs;
    private final long idleTimeMs;

    private int count;
    private int hit;
    private int miss;
}

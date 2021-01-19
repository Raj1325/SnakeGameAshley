package com.snake.util;

import com.badlogic.ashley.core.Entity;
import com.snake.common.GameMapper;

import java.util.Comparator;

/**
 * Created by RAJ DIGHE on 29/10/2020
 **/


public class ZOrderComparator implements Comparator<Entity> {

    public static final ZOrderComparator INSTANCE = new ZOrderComparator();

    private ZOrderComparator(){}

    @Override
    public int compare(Entity entity, Entity t1) {
        return Float.compare(
                GameMapper.ZORDER.get(entity).zorder,
                GameMapper.ZORDER.get(t1).zorder
        );
    }
}

package com.reda.concurrent.part8;

import java.util.Set;

/**
 * @author reda
 * @date 7/31/18 12:00 PM
 */
public interface Puzzle<P,M> {
    P initialPosition();
    boolean isGoal(P position);
    Set<M> legalMove(P position);
    P move(P position,M move);

}

package com.reda.concurrent.part8;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author reda
 * @date 7/31/18 2:25 PM
 */
public class SequentialPuzzleSolver<P,M> {
    private final Puzzle<P,M> puzzle;
    private final Set<P> seen = new HashSet<>();

    public SequentialPuzzleSolver(Puzzle<P,M> puzzle) {
        this.puzzle = puzzle;
    }

    public List<M> solve() {
        P pos = puzzle.initialPosition();
        return search(new Node<P,M>(pos,null,null));
    }

    private List<M> search(Node<P,M> node) {
        if (!seen.contains(node.pos)) {
            seen.add(node.pos);
            if (puzzle.isGoal(node.pos))
                return node.asMoveList();
            for (M move: puzzle.legalMove(node.pos)) {
                P pos = puzzle.move(node.pos,move);
                Node<P,M> child = new Node<>(pos,move,node);
                List<M>  result = search(child);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }
}

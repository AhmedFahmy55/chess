
package com.chess.engine.board.Move;

import com.chess.engine.board.Board;
import static com.chess.engine.board.Move.move.null_Move;

public class moveFactory {
    private moveFactory(){
    throw new RuntimeException("not istantiable");
    }
    public static move creatMove(final Board board, int currntPostion, int destinyPostion){
    for(final move Move:board.getAllLegalMoves()){
        if(Move.getCurrentCoordinate()==currntPostion&&Move.getDestinationCoordinate()==destinyPostion){
        return Move;
        }
    }
    return null_Move;
    }
}

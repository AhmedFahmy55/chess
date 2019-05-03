
package com.chess.engine.board.Move;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.piece;


public class nullMove extends move {
    
    public nullMove() {
        super(null, null, -1);
    }
    @Override
    public Board excute(){
    throw new RuntimeException("cant excute nothing");
    }
    
}

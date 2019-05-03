
package com.chess.engine.board.Move;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.piece;
import com.chess.engine.pieces.rook;


public final class kingSideCastleMove extends castleMove {
    
    public kingSideCastleMove(Board board, piece movedPiece, int destinyCoordinate,
            rook castleRook, int castleRookCoordinate, int castleRookDestinyCoordinate) {
        super(board, movedPiece, destinyCoordinate, castleRook, castleRookCoordinate, castleRookDestinyCoordinate);
    }
    @Override
    public String toString(){
    return "o-o";
    }
}

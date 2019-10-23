
package com.chess.engine.board.Move;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.piece;


public class RookAttackMove extends attackMove {
    
    public RookAttackMove(Board board, piece movedPiece, int destinyCoordinate, piece attackedPiece) {
        super(board, movedPiece, destinyCoordinate, attackedPiece);
    }
    
}

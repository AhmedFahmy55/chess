package com.chess.engine.board.Move;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move.move;
import com.chess.engine.pieces.piece;

public final class normalMove extends move{
        public normalMove(final Board board,final piece movedPiece, int destinyCoordinate) {
            super(board, movedPiece, destinyCoordinate);
        }
    
    }
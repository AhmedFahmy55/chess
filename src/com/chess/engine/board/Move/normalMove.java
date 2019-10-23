package com.chess.engine.board.Move;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move.move;
import com.chess.engine.pieces.piece;

public  class normalMove extends move{
        public normalMove(final Board board,final piece movedPiece, int destinyCoordinate) {
            super(board, movedPiece, destinyCoordinate);
        }
        @Override
   public boolean equals(Object other){
   return (this==other || other instanceof normalMove)&& (super.equals(other));
   }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }
    
    }
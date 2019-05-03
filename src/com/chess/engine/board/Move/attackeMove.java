
package com.chess.engine.board.Move;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move.move;
import com.chess.engine.pieces.piece;


public final class attackeMove extends move {
    final piece attackedPiece;

    public attackeMove(Board board, piece movedPiece, final int destinyCoordinate,
            final piece attackedPiece) {
        super(board, movedPiece, destinyCoordinate);
        this.attackedPiece=attackedPiece;
    }
   @Override
   public boolean isAttack(){
    return true;
    }
       @Override
   public piece getAttackedPiece(){
    return this.attackedPiece;
    }  
   @Override
    public int hashCode(){
    return this.movedPiece.hashCode()+super.hashCode();
    }
    @Override
    public boolean equals(Object other){
        if(this==other){
        return true;
        }
        if(!(other instanceof attackeMove)){
        return false;
        }
         final attackeMove move=(attackeMove)other;
    return super.equals(move)&&this.getAttackedPiece().equals(move.getAttackedPiece());
}
    
}

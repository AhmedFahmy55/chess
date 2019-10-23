
package com.chess.engine.board.Move;

import com.chess.engine.board.Board;
import com.chess.engine.board.builder;
import com.chess.engine.pieces.pawn;
import com.chess.engine.pieces.piece;

public class pawnjump extends move{
    
    public pawnjump(Board board, piece movedPiece, int destinyCoordinate) {
        super(board, movedPiece, destinyCoordinate);
    }
    @Override
      public Board excute(){
        final builder Builder=new builder();
        for(final piece Piece:this.board.getCurrentPlayer().getActivePieces() ){
        if(!this.movedPiece.equals(Piece)){
        Builder.setPiece(Piece);
        }
        }    
        for(final piece Piece:this.board.getCurrentPlayer().getOpponent().getActivePieces()){
        Builder.setPiece(Piece);
        }
        final pawn pawnJump=(pawn)this.movedPiece.movePiece(this);
        Builder.setPiece(pawnJump);
        /* to do en passant move
        Builder.setEnpassant(pawnJump);
*/
        Builder.setNextMoveMaker(this.board.getCurrentPlayer().getOpponent().getAlliance());
    return Builder.build();
    }
}

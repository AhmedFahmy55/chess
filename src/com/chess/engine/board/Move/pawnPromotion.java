
package com.chess.engine.board.Move;

import com.chess.engine.board.Board;
import com.chess.engine.board.builder;
import com.chess.engine.pieces.pawn;
import com.chess.engine.pieces.piece;
import java.util.Objects;
import javafx.stage.Stage;

public class pawnPromotion extends move {
    final move decoratedMove;
    final pawn pawnPromoted;
    public pawnPromotion(final move decoratedMove) {
        super(decoratedMove.getBoard(), decoratedMove.getMovedPiece(), decoratedMove.getDestinationCoordinate());
        this.decoratedMove=decoratedMove;
        this.pawnPromoted=(pawn)decoratedMove.getMovedPiece();
    }
   @Override
   public boolean equals(Object other){
   return (this==other || other instanceof pawnPromotion)&& (super.equals(other));
   }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.pawnPromoted);
        return hash;
    }
    @Override
    public Board excute(){
      final builder Builder=new builder();
        for(final piece Piece:this.board.getCurrentPlayer().getActivePieces() ){
        if(!this.pawnPromoted.equals(Piece)){
        Builder.setPiece(Piece);
        }
        }    
        for(final piece Piece:this.board.getCurrentPlayer().getOpponent().getActivePieces()){
        Builder.setPiece(Piece);
        }
        Builder.setPiece(this.pawnPromoted.getPtomotedPiece().movePiece(this));
        Builder.setNextMoveMaker(this.board.getCurrentPlayer().getOpponent().getAlliance());
    return Builder.build();
    }
}


package com.chess.engine.board.Move;

import com.chess.engine.board.Board;
import com.chess.engine.board.builder;
import com.chess.engine.pieces.piece;
import java.util.Objects;

public abstract class move {
    final Board board;
    final piece movedPiece;
    final int destinyCoordinate;
    public static move null_Move=new nullMove();
    public move(final Board board, final piece movedPiece, int destinyCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinyCoordinate = destinyCoordinate;
    }
    public boolean isAttack(){
    return false;
    }
    public boolean isCastlingMove(){
        return false;
    }
    public piece getAttackedPiece(){
    return null;
    }
    @Override
    public boolean equals(Object other){
    if(this==other){
   return true;
    }
    if(!(other instanceof move)){
    return false;
    }
    final move otherMove=(move)other;
    return getDestinationCoordinate()==otherMove.getDestinationCoordinate()&&
            getMovedPiece().equals(otherMove.movedPiece);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.movedPiece);
        hash = 59 * hash + this.destinyCoordinate;
        return hash;
    }
    public int getDestinationCoordinate(){
    return this.destinyCoordinate;
    }
    public piece getMovedPiece(){
    return this.movedPiece;
    }
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
        Builder.setPiece(this.movedPiece.movePiece(this));
        Builder.setNextMoveMaker(this.board.getCurrentPlayer().getOpponent().getAlliance());
    return Builder.build();
    }

    int getCurrentCoordinate() {
return this.movedPiece.getPostion();
        }
   
}


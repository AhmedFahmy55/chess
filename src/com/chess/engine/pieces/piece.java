
package com.chess.engine.pieces;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move.move;
import java.util.Collection;
import java.util.Objects;
public abstract class piece {
    protected pieceType piece_Type;
     protected int piecePostion;
    protected alliance pieceAlliance;
    protected boolean isFristMove;
    private int cachHachCode;
    public piece(final int piecePostion,final alliance pieceAlliance,final pieceType piece_Type
            ,final boolean isFristMove) {
        this.piecePostion = piecePostion;
        this.pieceAlliance = pieceAlliance;
        
        this.isFristMove=isFristMove;
        this.piece_Type=piece_Type;
        this.cachHachCode=computeHashCode();
    }
     public boolean isFristMove(){
    return this.isFristMove;
    }
    @Override
    public boolean equals(final Object other){
    if(this==other){
    return true;
    }
    if(!(other instanceof piece)){
    return false;
    }
    final piece Piece=(piece)other;
    return this.piecePostion==Piece.getPostion()&&this.piece_Type==Piece.getPiece_Type()&&
            this.pieceAlliance==Piece.getAlliance()&&this.isFristMove()==Piece.isFristMove();
    }

    @Override
    public int hashCode() {
        return this.cachHachCode;
    }
    public abstract piece movePiece(move Move);
    public pieceType getPiece_Type(){
    return this.piece_Type;
    }
    public int getPostion(){
    return this.piecePostion;
    }
    public final alliance getAlliance(){
    return this.pieceAlliance;
    }
    public abstract Collection<move> calculatePossibleMoves(final Board board);

    private int computeHashCode() {
     int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.piece_Type);
        hash = 31 * hash + this.piecePostion;
        hash = 31 * hash + Objects.hashCode(this.pieceAlliance);
        hash = 31 * hash + (this.isFristMove ? 1 : 0);
        return hash;
    }
}

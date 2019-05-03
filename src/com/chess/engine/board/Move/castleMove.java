
package com.chess.engine.board.Move;

import com.chess.engine.board.Board;
import com.chess.engine.board.builder;
import com.chess.engine.pieces.piece;
import com.chess.engine.pieces.rook;

public class castleMove extends move {
    protected final rook castleRook;
    protected final int castleRookCoordinate;
    protected final int castleRookDestinyCoordinate;

    public castleMove(Board board, piece movedPiece, int destinyCoordinate,final rook castleRook,
            final int castleRookCoordinate,final int castleRookDestinyCoordinate) {
        super(board, movedPiece, destinyCoordinate);
        this.castleRook=castleRook;
        this.castleRookCoordinate=castleRookCoordinate;
        this.castleRookDestinyCoordinate=castleRookDestinyCoordinate;
    }
    public rook getCastleRook(){
    return this.castleRook;
    }
    @Override
    public boolean isCastlingMove(){
    return true;
    }
    @Override
    public Board excute(){
        final builder Builder=new builder();
        for(final piece Piece:this.board.getCurrentPlayer().getActivePieces() ){
        if(!this.movedPiece.equals(Piece)&&!this.castleRook.equals(Piece)){
        Builder.setPiece(Piece);
        }
        }    
        for(final piece Piece:this.board.getCurrentPlayer().getOpponent().getActivePieces()){
        Builder.setPiece(Piece);
        }
        Builder.setPiece(this.movedPiece.movePiece(this));
        Builder.setPiece(new rook(this.castleRookDestinyCoordinate,this.castleRook.getAlliance()));
        Builder.setNextMoveMaker(this.board.getCurrentPlayer().getOpponent().getAlliance());
    return Builder.build();
    }
}


package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move.QueenSideCastleMove;
import com.chess.engine.board.Move.kingSideCastleMove;
import com.chess.engine.board.Move.move;
import com.chess.engine.board.tile;
import com.chess.engine.pieces.alliance;
import com.chess.engine.pieces.king;
import com.chess.engine.pieces.piece;
import com.chess.engine.pieces.rook;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

public class BlackPlayer extends Player {
    
    public BlackPlayer(final Board board, List<move> l, final List<move> o ) {
        super(board, l, o);
    }
      @Override
   public String toString(){
    return "Black Player";
    }
    @Override
    public List<piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Player getOpponent() {
    return this.board.getWhitPlayer();
        
        }

    @Override
    public alliance getAlliance() {
return alliance.BLACK;
        }

       @Override
    protected List<move> calculateKingCastles(List<move> legalMoves, List<move> oponantMoves) {
        // white king side castles
        final List<move>kingCastles=new ArrayList<>();
        final tile kingTile=this.board.getTile(4);
        if(!this.isInCheck() && kingTile.getPiece().isFristMove()){
        if(!this.board.getTile(5).isFilled()&&!this.board.getTile(6).isFilled()){
        final tile rookTile=this.board.getTile(7);
        if(rookTile.isFilled()&&rookTile.getPiece().isFristMove()){
            if(Player.calculateAttackOnTile(5, oponantMoves).isEmpty()&&
               Player.calculateAttackOnTile(6, oponantMoves).isEmpty()&&
               rookTile.getPiece().getPiece_Type().isRook()){
            kingCastles.add(new kingSideCastleMove(this.board,this.playerKing,6,
            (rook)rookTile.getPiece(),rookTile.getTileCoordinate(),5));
            }
           }
          }
        if(!this.board.getTile(1).isFilled()&&
           !this.board.getTile(2).isFilled()&&
            !this.board.getTile(3).isFilled()){
        final tile rookTile=this.board.getTile(0);
         if(rookTile.isFilled()&&rookTile.getPiece().isFristMove()){
            if(Player.calculateAttackOnTile(2, oponantMoves).isEmpty()&&
               Player.calculateAttackOnTile(3, oponantMoves).isEmpty()&&
               rookTile.getPiece().getPiece_Type().isRook()){
            kingCastles.add(new kingSideCastleMove(this.board,this.playerKing,6,
            (rook)rookTile.getPiece(),rookTile.getTileCoordinate(),5));
            }
           }
        }
}
          return ImmutableList.copyOf(kingCastles);
 }
    
    
}

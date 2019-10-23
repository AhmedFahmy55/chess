
package com.chess.engine.player;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move.kingSideCastleMove;
import com.chess.engine.board.Move.move;
import com.chess.engine.board.tile;
import com.chess.engine.pieces.alliance;
import com.chess.engine.pieces.piece;
import com.chess.engine.pieces.rook;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
public class WhitePlayer extends Player {
    public WhitePlayer(final Board board, List<move> l, final List<move> o) {
        super(board, l, o);
    }
    @Override
   public String toString(){
    return "White Player";
    }
    @Override
    public List<piece> getActivePieces() {
        return this.board.getWhitePieces();
    }

    @Override
    public Player getOpponent() {
   return this.board.getBlackPlayer() ;
        }

    @Override
    public alliance getAlliance() {
    return alliance.WHITE  ;
    }

    @Override
    protected List<move> calculateKingCastles(List<move> legalMoves, List<move> oponantMoves) {
        // white king side castles
        final List<move>kingCastles=new ArrayList<>();
        final tile kingTile=this.board.getTile(60);
        if(!this.isInCheck() && kingTile.getPiece().isFristMove()){
        if((!this.board.getTile(61).isFilled())&&(!this.board.getTile(62).isFilled())){
        final tile rookTile=this.board.getTile(63);
        if(rookTile.isFilled()&&rookTile.getPiece().isFristMove()){
            if(true){
            kingCastles.add(new kingSideCastleMove(this.board,this.playerKing,62,
            (rook)rookTile.getPiece(),63,61));
            }
           }
          }
        
        //Queen side castle
        if(!this.board.getTile(57).isFilled()&&!this.board.getTile(58).isFilled()&&
                !this.board.getTile(59).isFilled()){
        final tile rookTile=this.board.getTile(56);
         if(rookTile.isFilled()&&rookTile.getPiece().isFristMove()){
            if(Player.calculateAttackOnTile(58, oponantMoves).isEmpty()&&
               Player.calculateAttackOnTile(59, oponantMoves).isEmpty()&&
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


package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move.move;
import com.chess.engine.pieces.alliance;
import com.chess.engine.pieces.king;
import com.chess.engine.pieces.piece;
import static com.chess.engine.pieces.pieceType.KING;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.List;

public abstract class Player {
    protected Board board;
    protected king playerKing;
    protected List<move> legalMoves;
   protected boolean isInCheck;
Player(Board board,List<move> l,List<move>o){
this.board=board;
this.legalMoves=l;
this.playerKing=creatPlayerKing();
this.isInCheck=!(calculateAttackOnTile(this.playerKing.getPostion(),o).isEmpty());
}

   private king creatPlayerKing(){
   for(final piece Piece:getActivePieces()){
       if(Piece.getPiece_Type()==KING){
           return (king)Piece;
       }
   }

   throw new RuntimeException("board without king xD");
   }

     public abstract Player getOpponent();
    public piece getPlayerKing(){
    return this.playerKing;
    }
    public List<move>getLegalMoves(){
    return this.legalMoves;
    }
    //work here
    public abstract alliance getAlliance();
    public abstract List<piece>getActivePieces();
    
    protected static List<move> calculateAttackOnTile(final int piecePostion,final List<move>moves) {
        final List<move>attackMoves=new ArrayList<>();
    for(final move move:moves){
    if(piecePostion==move.getDestinationCoordinate()){
        attackMoves.add(move);
      }
    }  
    return ImmutableList.copyOf(attackMoves);
    }
      public boolean isMoveLegal(final move move){
        return this.legalMoves.contains(move);
    }  
      
     public boolean isInCheck(){
    return this.isInCheck;
    }
     public boolean isInCheckMate(){
     return (this.isInCheck&&!hasEscapeMove());
     }
     public boolean isInStaleMate(){
     return (!this.isInCheck)&&(!hasEscapeMove());
     }
    private boolean hasEscapeMove() {
        for(final move move:this.legalMoves){
            final MoveTranstion moveTranstion=makeMove(move);
        if(moveTranstion.getMoveStatues().isDone()){
        return true;
        }
        }
        return false;
    }
    public MoveTranstion makeMove(final move Move) {
        if(!isMoveLegal(Move)){
            return new MoveTranstion(this.board,Move,MoveStatues.ILLEGAL_MOVE);
        }
        final Board transtionBoard=Move.excute();
        final List<move>kingsAttacks=calculateAttackOnTile(transtionBoard.getCurrentPlayer()
                .getOpponent().getPlayerKing().getPostion(),transtionBoard.getCurrentPlayer()
        .getLegalMoves());
        if(!kingsAttacks.isEmpty()){
        return new MoveTranstion(this.board,Move,MoveStatues.LEAVES_IN_CHECK);
        }
        return new MoveTranstion(transtionBoard,Move,MoveStatues.DONE);
    }
    protected abstract  List<move>calculateKingCastles(List<move>legalMoves,List<move>oponantMoves);
}

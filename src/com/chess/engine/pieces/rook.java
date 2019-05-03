
package com.chess.engine.pieces;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move.attackeMove;
import com.chess.engine.board.Move.move;
import com.chess.engine.board.Move.normalMove;
import com.chess.engine.board.tile;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class rook extends piece {

   
    final int []rookMoves={-8,-1,1,8};

    public rook(final int piecePostion, final alliance pieceAlliance) {
        super(piecePostion, pieceAlliance, pieceType.ROOK,true);
        
    }
    public rook(final int piecePostion, final alliance pieceAlliance,final boolean isFristMove ){
         super(piecePostion, pieceAlliance, pieceType.ROOK,isFristMove);
        }
    @Override
    public String toString(){
    return pieceType.ROOK.toString();
    }
   public rook movePiece(move Move) {
        return new rook(Move.getDestinationCoordinate(),Move.getMovedPiece().getAlliance());
    }
    @Override
    public Collection<move> possibleMoves(Board board) {
final List<move> legalMoves=new ArrayList<>();
       for(final int rook_Moves :rookMoves){
          int destinyCoordinate=this.piecePostion;
          while(methods.isValliedCordinate(destinyCoordinate)){
              /*
              if((!methods.eightColumnRookExceptions(this, rook_Moves))||
                      (!methods.fristColumnRookExceptions(this, rook_Moves))){
              break;
             
              }
                */
          destinyCoordinate +=rook_Moves;
          if(methods.isValliedCordinate(destinyCoordinate)){
           final tile destinyTile=board.getTile(destinyCoordinate);
            if(!destinyTile.isFilled()){
            legalMoves.add(new normalMove(board,this,destinyCoordinate));
             }
            else{
            final piece pieceOnTile=destinyTile.getPiece();
            final alliance piece_alliance=pieceOnTile.getAlliance();
            if(this.pieceAlliance != piece_alliance){
            legalMoves.add(new attackeMove(board,this,destinyCoordinate,pieceOnTile));
            }
            else{
            break;
            }
             }
          }
          }
       }
       return ImmutableList.copyOf(legalMoves);
    }
    
}

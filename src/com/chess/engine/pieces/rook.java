
package com.chess.engine.pieces;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move.RookAttackMove;
import com.chess.engine.board.Move.RookNormalMove;
import com.chess.engine.board.Move.attackMove;
import com.chess.engine.board.Move.move;
import com.chess.engine.board.Move.normalMove;
import com.chess.engine.board.methods;
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
    @Override
   public rook movePiece(move Move) {
        return new rook(Move.getDestinationCoordinate(),Move.getMovedPiece().getAlliance());
    }
   
   
    @Override
    public Collection<move> calculatePossibleMoves(Board board) {
final List<move> legalMoves=new ArrayList<>();
       for(final int rook_Moves :rookMoves){
          int destinyCoordinate=this.piecePostion;
          while(methods.isValliedCoordinate(destinyCoordinate)){
              //problem in exceptions
              
              if((methods.INSTANCE.FIRST_COLUMN.get(destinyCoordinate)&&rook_Moves==-1)||
                      (methods.INSTANCE.EIGHTH_COLUMN.get(destinyCoordinate)&&rook_Moves==1)){
              break;
             
              }
               
          destinyCoordinate +=rook_Moves;
          if(methods.isValliedCoordinate(destinyCoordinate)){
           final tile destinyTile=board.getTile(destinyCoordinate);
            if(!destinyTile.isFilled()){
            legalMoves.add(new RookNormalMove(board,this,destinyCoordinate));
             }
            else{
            final piece pieceOnTile=destinyTile.getPiece();
            final alliance piece_alliance=pieceOnTile.getAlliance();
            if(this.pieceAlliance != piece_alliance){
            legalMoves.add(new RookAttackMove(board,this,destinyCoordinate,pieceOnTile));
            }
            break;
             }
            
          }
          }
       }
       return ImmutableList.copyOf(legalMoves);
    }
    
}

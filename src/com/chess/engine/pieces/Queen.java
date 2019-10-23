
package com.chess.engine.pieces;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move.QueenAttackMove;
import com.chess.engine.board.Move.QueenNormalMove;
import com.chess.engine.board.Move.attackMove;
import com.chess.engine.board.Move.move;
import com.chess.engine.board.Move.normalMove;
import com.chess.engine.board.methods;
import com.chess.engine.board.tile;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;



public class Queen extends piece {
  
    final int[]QueenMoves={-1,-7,-8,-9,9,8,7,1};

    public Queen(final int piecePostion, final alliance pieceAlliance ) {
        super(piecePostion, pieceAlliance, pieceType.QUEEN,true);
    }
    public Queen(final int piecePostion, final alliance pieceAlliance,final boolean isFristMove ){
         super(piecePostion, pieceAlliance, pieceType.ROOK,isFristMove);
        }
    @Override
    public String toString(){
    return pieceType.QUEEN.toString();
    }
    @Override   
    public List<move> calculatePossibleMoves(Board board) {
final List<move> legalMoves=new ArrayList<>();
       for(final int Queen_Moves :QueenMoves){
          int destinyCoordinate=this.piecePostion;
          while(methods.isValliedCoordinate(destinyCoordinate)){
              // queen= rook+bishop so we added their exceptions
              
            if(((methods.INSTANCE.FIRST_COLUMN.get(destinyCoordinate)&&Queen_Moves==-1)||
                      (methods.INSTANCE.EIGHTH_COLUMN.get(destinyCoordinate)&&Queen_Moves==1))){
              break;
              }
             if(((methods.INSTANCE.FIRST_COLUMN.get(destinyCoordinate))
                      &&((Queen_Moves==-9||Queen_Moves==7)))||((methods.INSTANCE.EIGHTH_COLUMN.get(destinyCoordinate))
                      &&(Queen_Moves==-7||Queen_Moves==9))){
              break;
              }
          
          destinyCoordinate +=Queen_Moves;
          if(methods.isValliedCoordinate(destinyCoordinate)){
           final tile destinyTile=board.getTile(destinyCoordinate);
            if(!destinyTile.isFilled()){
            legalMoves.add(new QueenNormalMove(board,this,destinyCoordinate));
             }
            else{
            final piece pieceOnTile=destinyTile.getPiece();
            final alliance piece_alliance=pieceOnTile.getAlliance();
            if(this.pieceAlliance != piece_alliance){
            legalMoves.add(new QueenAttackMove(board,this,destinyCoordinate,pieceOnTile));
            }
            break;
             }
          }
          }
       }
       return ImmutableList.copyOf(legalMoves) ;
    }
    
    @Override
    public Queen movePiece(move Move) {
return new Queen(Move.getDestinationCoordinate(),Move.getMovedPiece().getAlliance());
        }

 
    
}


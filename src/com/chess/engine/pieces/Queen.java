
package com.chess.engine.pieces;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move.attackeMove;
import com.chess.engine.board.Move.move;
import com.chess.engine.board.Move.normalMove;
import com.chess.engine.board.tile;
import com.google.common.collect.ImmutableList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;


public class Queen extends piece {
  private Image image;
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
    public List<move> possibleMoves(Board board) {
final List<move> legalMoves=new ArrayList<>();
       for(final int Queen_Moves :QueenMoves){
          int destinyCoordinate=this.piecePostion;
          while(methods.isValliedCordinate(destinyCoordinate)){
              // queen= rook+bishop so we added their exceptions
              /*
              if((!methods.eightColumnRookExceptions(this, Queen_Moves))||
                      (!methods.fristColumnRookExceptions(this, Queen_Moves))||
                      (!methods.fristColumnBishopExceptions(this, Queen_Moves))||
                      (!methods.eightColumnBishopExceptions(this, Queen_Moves))){
              break;
              } */
          destinyCoordinate +=Queen_Moves;
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
       return ImmutableList.copyOf(legalMoves) ;
    }
    
    @Override
    public Queen movePiece(move Move) {
return new Queen(Move.getDestinationCoordinate(),Move.getMovedPiece().getAlliance());
        }

 
    
}


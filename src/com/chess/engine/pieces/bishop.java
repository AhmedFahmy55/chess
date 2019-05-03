
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
public class bishop extends piece {
   
final int []bishopMoves={-9,-7,7,9};

    public bishop(final int piecePostion, final alliance pieceAlliance) {
        super(piecePostion, pieceAlliance, pieceType.BISHOP,true);
    }
    public bishop(final int piecePostion, final alliance pieceAlliance,final boolean isFristMove ){
         super(piecePostion, pieceAlliance, pieceType.ROOK,isFristMove);
        }
@Override
    public String toString(){
    return pieceType.BISHOP.toString();
    }
       public bishop movePiece(move Move) {
        return new bishop(Move.getDestinationCoordinate(),Move.getMovedPiece().getAlliance());
    }
    @Override
    public Collection<move> possibleMoves(final Board board) {
       final List<move> legalMoves=new ArrayList<>();
       for(final int bishop_Moves :bishopMoves){
          int destinyCoordinate=this.piecePostion;
          while(methods.isValliedCordinate(destinyCoordinate)){
              /*
              if((!methods.fristColumnBishopExceptions(this, bishop_Moves))||
                      (!methods.eightColumnBishopExceptions(this, bishop_Moves))){
              break;
              }
           */
          destinyCoordinate +=bishop_Moves;
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
}

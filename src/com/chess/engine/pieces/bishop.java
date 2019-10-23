
package com.chess.engine.pieces;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move.BishopAttackMove;
import com.chess.engine.board.Move.BishopNormalMove;
import com.chess.engine.board.Move.attackMove;
import com.chess.engine.board.Move.move;
import com.chess.engine.board.Move.normalMove;
import com.chess.engine.board.methods;
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
    public Collection<move> calculatePossibleMoves(final Board board) {
       final List<move> legalMoves=new ArrayList<>();
       for(final int bishop_Moves :bishopMoves){
          int destinyCoordinate=this.piecePostion;
          while(methods.isValliedCoordinate(destinyCoordinate)){
              
              if(((methods.INSTANCE.FIRST_COLUMN.get(destinyCoordinate))
                      &&((bishop_Moves==-9||bishop_Moves==7)))||((methods.INSTANCE.EIGHTH_COLUMN.get(destinyCoordinate))
                      &&(bishop_Moves==-7||bishop_Moves==9))){
              break;
              }
           
          destinyCoordinate +=bishop_Moves;
          if(methods.isValliedCoordinate(destinyCoordinate)){
            final tile destinyTile=board.getTile(destinyCoordinate);
            if(!destinyTile.isFilled()){
            legalMoves.add(new BishopNormalMove(board,this,destinyCoordinate));
             }
            else{
            final piece pieceOnTile=destinyTile.getPiece();
            final alliance piece_alliance=pieceOnTile.getAlliance();
            if(this.pieceAlliance != piece_alliance){
            legalMoves.add(new BishopAttackMove(board,this,destinyCoordinate,pieceOnTile));
            }
            break;
             }
          }
       }
       }
       return ImmutableList.copyOf(legalMoves) ;
    }
}

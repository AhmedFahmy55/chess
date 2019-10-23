
package com.chess.engine.pieces;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move.KingAttackMove;
import com.chess.engine.board.Move.KingNormalMove;
import com.chess.engine.board.Move.attackMove;
import com.chess.engine.board.Move.move;
import com.chess.engine.board.Move.normalMove;
import com.chess.engine.board.methods;
import com.chess.engine.board.tile;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class king extends piece {

    
final int []kingMoves={-9,-8,-7,-1,1,7,8,9};

    public king(final int piecePostion, final alliance pieceAlliance) {
        super(piecePostion, pieceAlliance, pieceType.KING,true);
    }
        public king(int piecePostion, final alliance pieceAlliance,final boolean isFristMove ){
         super(piecePostion, pieceAlliance, pieceType.ROOK,isFristMove);
        }
    
@Override
    public String toString(){
    return pieceType.KING.toString();
    }
    @Override
    public Collection<move> calculatePossibleMoves(Board board) {
    final List<move> legalMoves=new ArrayList<>();
    for(final int king_Moves : kingMoves){
            final int destinyTileNumber=this.piecePostion+king_Moves;
            if(methods.isValliedCoordinate(destinyTileNumber)){
                
              if((methods.INSTANCE.FIRST_COLUMN.get(this.piecePostion)&&(king_Moves==-1||
                king_Moves==-9||king_Moves==7))||(methods.INSTANCE.EIGHTH_COLUMN.get(this.piecePostion)&&(
                      king_Moves==-7||king_Moves==1||king_Moves==9))){
              continue;
               }
             
            final tile destinyTile=board.getTile(destinyTileNumber);
            if(!destinyTile.isFilled()){
            legalMoves.add(new KingNormalMove(board,this,destinyTileNumber));
             }
            else{
            final piece pieceOnTile=destinyTile.getPiece();
            final alliance piece_alliance=pieceOnTile.getAlliance();
            if(this.pieceAlliance != piece_alliance){
            legalMoves.add(new KingAttackMove(board,this,destinyTileNumber,pieceOnTile));
            }
             }
            }
        }

        return ImmutableList.copyOf(legalMoves) ;
    }

      @Override
    public king movePiece(move Move) {
return new king(Move.getDestinationCoordinate(),Move.getMovedPiece().getAlliance());
        }

}

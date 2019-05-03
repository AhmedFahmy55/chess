
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
    public Collection<move> possibleMoves(Board board) {
    final List<move> legalMoves=new ArrayList<>();
    for(final int king_Moves : kingMoves){
            final int destinyTileNumber=this.piecePostion+king_Moves;
            if(methods.isValliedCordinate(destinyTileNumber)){
                /*
              if((kingExceptionsFrist(this,king_Moves))||(kingExceptionsEight(this,king_Moves))){
              continue;
               }
             */
            final tile destinyTile=board.getTile(destinyTileNumber);
            if(!destinyTile.isFilled()){
            legalMoves.add(new normalMove(board,this,destinyTileNumber));
             }
            else{
            final piece pieceOnTile=destinyTile.getPiece();
            final alliance piece_alliance=pieceOnTile.getAlliance();
            if(this.pieceAlliance != piece_alliance){
            legalMoves.add(new attackeMove(board,this,destinyTileNumber,pieceOnTile));
            }
             }
            }
        }

        return ImmutableList.copyOf(legalMoves) ;
    }
    private boolean kingExceptionsFrist(final piece piece,int kingMoves){
    return (methods.isFristColumn(piece))&& (kingMoves==-9||kingMoves==-1||kingMoves==7);
    }
    private boolean kingExceptionsEight(final piece piece,int kingMoves){
    return (methods.isFristColumn(piece))&& (kingMoves==9||kingMoves==1||kingMoves==-7);
    }

      @Override
    public king movePiece(move Move) {
return new king(Move.getDestinationCoordinate(),Move.getMovedPiece().getAlliance());
        }

}

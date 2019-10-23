
package com.chess.engine.pieces;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move.NightAttackMove;
import com.chess.engine.board.Move.NightNormalMove;
import com.chess.engine.board.Move.move;
import com.chess.engine.board.methods;
import com.chess.engine.board.tile;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class knight extends piece {

    public knight(final int piecePostion, final alliance pieceAlliance) {
        super(piecePostion, pieceAlliance, pieceType.KNIGHT,true);
    }
    public knight(final int piecePostion, final alliance pieceAlliance,final boolean isFristMove ){
         super(piecePostion, pieceAlliance, pieceType.ROOK,isFristMove);
        }
   
    private boolean isValliedCordinate(int cordinate){
    return cordinate >=0 && cordinate<64;
    }
    @Override
    public String toString(){
    return pieceType.KNIGHT.toString();
    }
    final int[] knightMoves= {-17,-15,-10,-6,6,10,15,17};
    @Override
    public Collection<move> calculatePossibleMoves(final Board board) {
        final List<move> legalMoves=new ArrayList<>();
       
        for(final int knight_Moves : knightMoves){
            final int destinyTileNumber=this.piecePostion+knight_Moves;
            if(isValliedCordinate(destinyTileNumber)){
                
               if((methods.INSTANCE.FIRST_COLUMN.get(this.piecePostion)&&(knight_Moves==-17||knight_Moves==15
                       ||knight_Moves==-10||knight_Moves==6))||
                       (methods.INSTANCE.SECOND_COLUMN.get(this.piecePostion)&&(knight_Moves==-10||knight_Moves==6))
                      ||(methods.INSTANCE.EIGHTH_COLUMN.get(this.piecePostion)&&(knight_Moves==-15||knight_Moves==17
                       ||knight_Moves==10||knight_Moves==-6))
                     ||(methods.INSTANCE.SEVENTH_COLUMN.get(this.piecePostion)&&(knight_Moves==-6||knight_Moves==10))){
               continue;
               }
            
            final tile destinyTile=board.getTile(destinyTileNumber);
            if(!destinyTile.isFilled()){
            legalMoves.add(new NightNormalMove(board,this,destinyTileNumber));
             }
            else{
            final piece pieceOnTile=destinyTile.getPiece();
            final alliance piece_alliance=pieceOnTile.getAlliance();
            if(this.pieceAlliance != piece_alliance){
            legalMoves.add(new NightAttackMove(board,this,destinyTileNumber,pieceOnTile));
            }
             }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }

       @Override
    public knight movePiece(move Move) {
return new knight(Move.getDestinationCoordinate(),Move.getMovedPiece().getAlliance());
        }
}



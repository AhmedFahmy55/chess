
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
    @Override
    public Collection<move> possibleMoves(final Board board) {
        final List<move> legalMoves=new ArrayList<>();
       final int[] knightMoves= {-17,-15,-10,-6,6,10,15,17};
        for(final int knight_Moves : knightMoves){
            final int destinyTileNumber=this.piecePostion+knight_Moves;
            if(isValliedCordinate(destinyTileNumber)){
                /*
               if((!methods.fristColumnExceptions(this, knight_Moves))||
                       (!methods.secondColumnExceptions(this, knight_Moves))
                      ||(!methods.sevenColumnExceptions(this, knight_Moves))
                     ||(!methods.eightColumnExceptions(this,knight_Moves ))  ){
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
        return ImmutableList.copyOf(legalMoves);
    }

       @Override
    public knight movePiece(move Move) {
return new knight(Move.getDestinationCoordinate(),Move.getMovedPiece().getAlliance());
        }
}



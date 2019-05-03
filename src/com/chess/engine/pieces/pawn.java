
package com.chess.engine.pieces;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move.attackeMove;
import com.chess.engine.board.Move.move;
import com.chess.engine.board.Move.normalMove;
import com.chess.engine.board.Move.pawnjump;
import com.chess.engine.board.tile;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class pawn extends piece {
    //work of drist move
        
    public pawn(final int piecePostion,final  alliance pieceAlliance) {
        super(piecePostion, pieceAlliance, pieceType.PAWN,true);
    }
    public pawn(final int piecePostion, final alliance pieceAlliance,final boolean isFristMove ){
         super(piecePostion, pieceAlliance, pieceType.ROOK,isFristMove);
        }
   public pawn movePiece(move Move) {
        return new pawn(Move.getDestinationCoordinate(),Move.getMovedPiece().getAlliance());
    }
final int[]pawnMoves={8,16,7,9};

@Override
    public String toString(){
    return pieceType.PAWN.toString();
    }
    @Override
    public Collection<move> possibleMoves(final Board board) {
       final List<move>legalMoves=new ArrayList<>();
        for(final int pawn_Moves :pawnMoves){
        int destinyCoordinate=this.piecePostion  +(pawn_Moves * this.getAlliance().getDirection());
        if(!methods.isValliedCordinate(destinyCoordinate)){
            continue;
         }
        final tile destinyTile =board.getTile(destinyCoordinate);
        if((pawn_Moves==8) && (!destinyTile.isFilled())){
       legalMoves.add(new normalMove(board,this,destinyCoordinate));
        }
        // work on frist move
        else  if(pawn_Moves == 16 && this.isFristMove() &&
                    ((BoardUtils.INSTANCE.SECOND_ROW.get(this.getPostion()) && this.pieceAlliance.isBlack()) ||
                     (BoardUtils.INSTANCE.SEVENTH_ROW.get(this.getPostion()) && this.pieceAlliance.isWhite()))) {
                final int behindCandidateDestinationCoordinate =
                        this.piecePostion + (this.pieceAlliance.getDirection() * 8);
                tile t=board.getTile(behindCandidateDestinationCoordinate);
                if ( destinyTile.getPiece()== null &&
                    t.getPiece() == null) {
                    legalMoves.add(new pawnjump(board,this,destinyCoordinate));
                }
            }
        else if((pawn_Moves==7)&&!((methods.isFristColumn(this)&&this.pieceAlliance.isBlack())||
                            (methods.isEightColumn(this)&&this.pieceAlliance.isWhite()))){
                if(board.getTile(destinyCoordinate).isFilled()){
                final piece attackedPiece=board.getTile(destinyCoordinate).getPiece();
               final alliance piece_alliance=attackedPiece.getAlliance();

                    if(this.pieceAlliance != piece_alliance ){
                        legalMoves.add(new attackeMove(board,this,destinyCoordinate,attackedPiece));
                    } 
                }
                  }
        else if((pawn_Moves==9)&&!((methods.isEightColumn(this)&&this.pieceAlliance.isBlack())||
                (methods.isEightColumn(this)&&this.pieceAlliance.isWhite()))){
                final piece attackedPiece=board.getTile(destinyCoordinate).getPiece();
                if( this.pieceAlliance != attackedPiece.getAlliance()){
                 legalMoves.add(new attackeMove(board,this,destinyCoordinate,attackedPiece));
                    } 
       }
        }
     return ImmutableList.copyOf(legalMoves);
        }
        
    }

    



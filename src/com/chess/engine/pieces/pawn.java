
package com.chess.engine.pieces;

import com.chess.GUI.End;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move.PawnAttackMove;
import com.chess.engine.board.methods;
import com.chess.engine.board.Move.attackMove;
import com.chess.engine.board.Move.move;
import com.chess.engine.board.Move.normalMove;
import com.chess.engine.board.Move.pawnMove;
import com.chess.engine.board.Move.pawnPromotion;
import com.chess.engine.board.Move.pawnjump;
import com.chess.engine.board.tile;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class pawn extends piece {
    
        
    public pawn(final int piecePostion,final  alliance pieceAlliance) {
        super(piecePostion, pieceAlliance, pieceType.PAWN,true);
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
    public Collection<move> calculatePossibleMoves(final Board board) {
       final List<move>legalMoves=new ArrayList<>();
        for(final int pawn_Moves :pawnMoves){
        int destinyCoordinate=this.piecePostion  +(pawn_Moves * this.getAlliance().getDirection());
        if(!methods.isValliedCoordinate(destinyCoordinate)){
            continue;
         }
        final tile destinyTile =board.getTile(destinyCoordinate);
        if((pawn_Moves==8) && (!destinyTile.isFilled())){
            if(this.pieceAlliance.isPawnPromotionTile(destinyCoordinate)){
             legalMoves.add(new pawnPromotion(new pawnMove(board,this,destinyCoordinate)));
            }else{
       legalMoves.add(new pawnMove(board,this,destinyCoordinate));
            }
            }
        // work on frist move
        else  if(pawn_Moves == 16 && this.isFristMove() &&
                    ((methods.INSTANCE.SECOND_ROW.get(this.getPostion()) && this.pieceAlliance.isBlack()) ||
                     (methods.INSTANCE.SEVENTH_ROW.get(this.getPostion()) && this.pieceAlliance.isWhite()))) {
                final int behindCandidateDestinationCoordinate =
                        this.piecePostion + (this.pieceAlliance.getDirection() * 8);
                tile t=board.getTile(behindCandidateDestinationCoordinate);
                if ( destinyTile.getPiece()== null &&
                    t.getPiece() == null) {
                    legalMoves.add(new pawnjump(board,this,destinyCoordinate));
                }
            }
        else if((pawn_Moves==7)&&!((methods.INSTANCE.FIRST_COLUMN.get(this.piecePostion)&&this.pieceAlliance.isBlack())||
                            (methods.INSTANCE.EIGHTH_COLUMN.get(this.piecePostion)&&this.pieceAlliance.isWhite()))){
                if(board.getTile(destinyCoordinate).isFilled()){
                final piece attackedPiece=board.getTile(destinyCoordinate).getPiece();
               final alliance piece_alliance=attackedPiece.getAlliance();

                    if(this.pieceAlliance != piece_alliance ){
                        if(this.pieceAlliance.isPawnPromotionTile(destinyCoordinate)){
                            
                        legalMoves.add(new pawnPromotion(new pawnMove(board,this,destinyCoordinate)));
                        }else{
                        legalMoves.add(new PawnAttackMove(board,this,destinyCoordinate,attackedPiece));
                        }
                        } 
                }
                  }
        else if((pawn_Moves==9)&&!((methods.INSTANCE.EIGHTH_COLUMN.get(this.piecePostion)&&this.pieceAlliance.isBlack())||
                (methods.INSTANCE.FIRST_COLUMN.get(this.piecePostion)&&this.pieceAlliance.isWhite()))){
            if(board.getTile(destinyCoordinate).isFilled()){
            final piece attackedPiece=board.getTile(destinyCoordinate).getPiece();
               final alliance piece_alliance=attackedPiece.getAlliance();
                    if(this.pieceAlliance != piece_alliance ){
                    if(this.pieceAlliance.isPawnPromotionTile(destinyCoordinate)){
                        
                    legalMoves.add(new pawnPromotion(new pawnMove(board,this,destinyCoordinate)));
                  
            }else{
                    legalMoves.add(new PawnAttackMove(board,this,destinyCoordinate,attackedPiece));
            }            }
                    } 
       }
        }
     return ImmutableList.copyOf(legalMoves);
        }
    //todo here
    public piece getPtomotedPiece(){
        
    return new Queen(this.piecePostion,this.getAlliance(),false);
    }
    }

    



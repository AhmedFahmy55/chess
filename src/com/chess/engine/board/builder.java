
package com.chess.engine.board;

import com.chess.engine.pieces.alliance;
import com.chess.engine.pieces.pawn;
import com.chess.engine.pieces.piece;
import java.util.HashMap;
import java.util.Map;


public class builder {
    public Map<Integer,piece>boardConfig;
    alliance nextMoveMaker;
     //pawn enPassantPawn;
    public builder(){
        this.boardConfig=new HashMap<>();
    }
    public builder setPiece(final piece piece){
        this.boardConfig.put(piece.getPostion(),piece);
        return this;
    }
    public builder setNextMoveMaker(final alliance nextMoveC){
    this.nextMoveMaker=nextMoveC;
    return this ;
    }
    public Board build(){
    return new Board(this);
    }
/*
    public void setEnpassant(pawn pawnJump) {
        this.enPassantPawn=pawnJump;
    }
    */
}

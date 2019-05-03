
package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move.move;

public class MoveTranstion {
    Board TranstionBoard;
   final move Move;
   final MoveStatues move_Statues;
   MoveTranstion(final Board tB,final move Move,final MoveStatues mS){
   this.TranstionBoard=tB;
   this.Move=Move;
   this.move_Statues=mS;
   }
   public MoveStatues getMoveStatues(){
   return this.move_Statues;
   }

    public Board getBoard() {
return this.TranstionBoard;
        }
}

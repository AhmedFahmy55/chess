
package com.chess.engine.player;


public enum MoveStatues {
    DONE {
        @Override
        public boolean isDone() {
return true;        }
    } ,
    ILLEGAL_MOVE {
        @Override
       public boolean isDone() {
return false;        }
    },
    LEAVES_IN_CHECK{
          @Override
      public boolean isDone() {
return false;        }
    };
  public  abstract boolean isDone();
}

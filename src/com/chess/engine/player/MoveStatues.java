
package com.chess.engine.player;


public enum MoveStatues {
    DONE {
        @Override
        public boolean isDone() {
return true;        }

        @Override
        public String toString() {
return "done";
        }
      
    } ,
    ILLEGAL_MOVE {
        @Override
       public boolean isDone() {
return false;        }

        @Override
        public String toString() {
return "illegal";        }
        
    },
    LEAVES_IN_CHECK{
          @Override
      public boolean isDone() {
return false;        }

        @Override
        public String toString() {
return "leavesInCheck";        }

    };
  public  abstract boolean isDone();
    @Override
  public abstract String toString();
}

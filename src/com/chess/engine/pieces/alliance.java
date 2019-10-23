
package com.chess.engine.pieces;

import com.chess.engine.board.methods;
import com.chess.engine.player.BlackPlayer;
import com.chess.engine.player.Player;
import com.chess.engine.player.WhitePlayer;

public enum alliance {

  
    WHITE() {
        @Override
        public int getDirection() {
            return -1;
        }

        @Override
        public boolean isBlack() {
            return false ;
        }

        @Override
        public boolean isWhite() {
            return true ;
        }

        @Override
        public Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer) {
            return whitePlayer;
        }

        @Override
        public boolean isPawnPromotionTile(int x) {
          return methods.INSTANCE.FIRST_ROW.get(x);
        }
    },
   BLACK() {
        @Override
        public int getDirection() {
            return 1;
        }

        @Override
        public boolean isBlack() {
            return true;
        }

        @Override
        public boolean isWhite() {
return false;  
        }

        @Override
        public Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer) {
        return blackPlayer;    
        }

        @Override
        public boolean isPawnPromotionTile(int x) {
          return methods.INSTANCE.EIGHTH_ROW.get(x);
        }
    };
   

    /**
     *
     * @return
     */
    public abstract int getDirection();
    public abstract boolean isBlack();
        public abstract boolean isWhite();

    public abstract Player choosePlayer(WhitePlayer whitePlayer, BlackPlayer blackPlayer);
    public abstract boolean isPawnPromotionTile(int x);
}


package com.chess.engine.pieces;


public enum pieceType {
    
    ROOK("R") {
        @Override
        public boolean isRook() {
        return true;
        }
        
    },
    KNIGHT("N") {
        @Override
        public boolean isRook() {
        return false;
        }
    },
    BISHOP("B") {
             @Override
        public boolean isRook() {
        return false;
        }
    },
    QUEEN("Q") {
             @Override
        public boolean isRook() {
         return false;
        }
    },
    PAWN("P") {
           @Override
        public boolean isRook() {
        return false;
        }
    },
    KING("K") {
             @Override
        public boolean isRook() {
        return false;
        }
    };
    private final String pieceName;
    pieceType(final String pieceName){
    this.pieceName=pieceName;
    }
    @Override
    public String toString(){
        return this.pieceName;
    }
    public abstract boolean isRook();
}

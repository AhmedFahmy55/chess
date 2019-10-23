/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chess.engine.board.Move;

import com.chess.engine.board.Board;
import com.chess.engine.pieces.piece;

/**
 *
 * @author Kizaro
 */
public class NightNormalMove extends move {
    
    public NightNormalMove(Board board, piece movedPiece, int destinyCoordinate) {
        super(board, movedPiece, destinyCoordinate);
    }
    
}

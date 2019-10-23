
package com.chess.engine.board;
import com.chess.engine.board.Move.move;
import com.chess.engine.pieces.Queen;
import com.chess.engine.pieces.alliance;
import com.chess.engine.pieces.bishop;
import com.chess.engine.pieces.king;
import com.chess.engine.pieces.knight;
import com.chess.engine.pieces.pawn;
import com.chess.engine.pieces.piece;
import com.chess.engine.pieces.rook;
import com.chess.engine.player.BlackPlayer;
import com.chess.engine.player.Player;
import com.chess.engine.player.WhitePlayer;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.List;
public class Board {
   private final List<tile> gameBoard;
       private final List<piece> activeBlack;
       private final List<piece> activeWhite;
       private final WhitePlayer whitePlayer;
       private final BlackPlayer blackPlayer;
       private final Player currentPlayer;
   
 public Board(final builder Builder){
     this.gameBoard=creatGameBoard(Builder);
     this.activeBlack=calculateActivePiece(this.gameBoard,alliance.BLACK);
      this.activeWhite=calculateActivePiece(this.gameBoard,alliance.WHITE);
      final List<move>whiteLegalMoves=calculateLegalMoves(this.activeWhite);
      final List<move>blackLegalMoves=calculateLegalMoves(this.activeBlack);
      this.whitePlayer=new WhitePlayer(this,whiteLegalMoves,blackLegalMoves);
      this.blackPlayer=new BlackPlayer(this,blackLegalMoves,whiteLegalMoves);
      this.currentPlayer=Builder.nextMoveMaker.choosePlayer(this.whitePlayer,this.blackPlayer);
    }
 
  public tile getTile(int x){
    return gameBoard.get(x);
    }
  public Player getCurrentPlayer(){
  return this.currentPlayer;
  }
  //work here
  public List<piece>getBlackPieces(){
  return this.activeBlack;
  }
  public List<piece>getWhitePieces(){
  return this.activeWhite;
  }
  public Player getWhitPlayer(){
  return this.whitePlayer;
  }
  public Player getBlackPlayer(){
  return this.blackPlayer;
  }
  @Override
   public String toString(){
    final StringBuilder builder=new StringBuilder();
    for(int i=0;i<64;i++){
     final String txt=this.gameBoard.get(i).toString();
     builder.append(txt+"  ");
     if(((i+1)%8)==0){
     builder.append("\n");
     }
    }
    return builder.toString() ;
    }
     private static List<piece> calculateActivePiece(final List<tile> gameBoard, final alliance alliance) {
     final List<piece>activePiece=new ArrayList<>();
    for(final tile Tile: gameBoard){
    if(Tile.isFilled()){
       final piece Piece=Tile.getPiece();
       if(Piece.getAlliance()==alliance){
      activePiece.add(Piece);
       }
    }
    }
     return activePiece;
    }
     private List<move>calculateLegalMoves(List<piece>pieces){
         List<move>legalMoves=new ArrayList<>();
        for(final piece Piece:pieces){
        legalMoves.addAll(Piece.calculatePossibleMoves(this));
        }
        return ImmutableList.copyOf(legalMoves) ;
     }
private  List<tile>creatGameBoard(final builder builder){
    // can be list of tiles
final tile[]boardTiles=new tile[64];
for(int i=0;i<64;i++){
    boardTiles[i]=tile.creatTIle(i, builder.boardConfig.get(i));
}
return ImmutableList.copyOf(boardTiles);
 }
 public static Board creatStandardBoard(){
 final builder Builder=new builder();
 //black pieces
 Builder.setPiece(new rook(0,alliance.BLACK));
 Builder.setPiece(new knight(1,alliance.BLACK));
 Builder.setPiece(new bishop(2,alliance.BLACK));
 Builder.setPiece(new Queen(3,alliance.BLACK));
 Builder.setPiece(new king(4,alliance.BLACK));
 Builder.setPiece(new bishop(5,alliance.BLACK));
 Builder.setPiece(new knight(6,alliance.BLACK));
 Builder.setPiece(new rook(7,alliance.BLACK));
Builder.setPiece(new pawn(8,alliance.BLACK));
 Builder.setPiece(new pawn(9,alliance.BLACK));
 Builder.setPiece(new pawn(10,alliance.BLACK));
 Builder.setPiece(new pawn(11,alliance.BLACK));
 Builder.setPiece(new pawn(12,alliance.BLACK));
 Builder.setPiece(new pawn(13,alliance.BLACK));
Builder.setPiece(new pawn(14,alliance.BLACK));
 Builder.setPiece(new pawn(15,alliance.BLACK));
 //white pieces
  Builder.setPiece(new pawn(48,alliance.WHITE));
 Builder.setPiece(new pawn(49,alliance.WHITE));
 Builder.setPiece(new pawn(50,alliance.WHITE));
 Builder.setPiece(new pawn(51,alliance.WHITE));
 Builder.setPiece(new pawn(52,alliance.WHITE));
  Builder.setPiece(new pawn(53,alliance.WHITE));
  Builder.setPiece(new pawn(54,alliance.WHITE));
 Builder.setPiece(new pawn(55,alliance.WHITE));
 Builder.setPiece(new rook(56,alliance.WHITE));
 Builder.setPiece(new knight(57,alliance.WHITE));
 Builder.setPiece(new bishop(58,alliance.WHITE));
 Builder.setPiece(new Queen(59,alliance.WHITE));
Builder.setPiece(new king(60,alliance.WHITE));
 Builder.setPiece(new bishop(61,alliance.WHITE));
Builder.setPiece(new knight(62,alliance.WHITE));
 Builder.setPiece(new rook(63,alliance.WHITE));
 // next move maker
 Builder.setNextMoveMaker(alliance.WHITE);
 return Builder.build();
 }
    public Iterable<move> getAllLegalMoves() {
       return   Iterables.unmodifiableIterable(Iterables.concat(this.whitePlayer.getLegalMoves()
               ,this.blackPlayer.getLegalMoves()));
    }
}

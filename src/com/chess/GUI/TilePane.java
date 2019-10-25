
package com.chess.GUI;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move.BishopAttackMove;
import com.chess.engine.board.Move.BishopNormalMove;
import com.chess.engine.board.Move.KingAttackMove;
import com.chess.engine.board.Move.KingNormalMove;
import com.chess.engine.board.Move.NightAttackMove;
import com.chess.engine.board.Move.NightNormalMove;
import com.chess.engine.board.Move.PawnAttackMove;
import com.chess.engine.board.Move.QueenAttackMove;
import com.chess.engine.board.Move.QueenNormalMove;
import com.chess.engine.board.Move.RookAttackMove;
import com.chess.engine.board.Move.RookNormalMove;
import com.chess.engine.board.Move.move;
import com.chess.engine.board.Move.pawnMove;
import com.chess.engine.board.Move.pawnPromotion;
import com.chess.engine.board.Move.pawnjump;
import com.chess.engine.board.tile;
import com.chess.engine.board.methods;
import static com.chess.engine.pieces.alliance.BLACK;
import static com.chess.engine.pieces.alliance.WHITE;
import com.chess.engine.pieces.piece;
import com.chess.engine.player.MoveTranstion;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.stage.Stage;


public class TilePane extends Pane {
    final int tileID;
    tile sourceTile;
    tile destinyTile;
    piece movedPiece;
    static int FLAG = 0;
    static int cn=0;
    TilePane(final BoardPane boardPane,final int tileID){
    this.tileID=tileID;
    
    setMaxSize(80, 80);
    setMinSize(80, 80);
    makeTileColor();
    setTileIcon(boardPane.chessBoard);
    
    setOnMouseClicked(( e) -> {
        MouseButton button = e.getButton();
        if (FLAG == 0) {
            //do frist move
            
            if(button==MouseButton.SECONDARY){
                sourceTile=null;
                destinyTile=null;
                movedPiece=null;
                boardPane.drawBoard(boardPane.chessBoard, boardPane);
                cn=0;
                System.out.println(FLAG +"Cancel Select");
            }else if(button==MouseButton.PRIMARY){
                if(sourceTile==null){
                    sourceTile=boardPane.chessBoard.getTile(tileID);
                    movedPiece=sourceTile.getPiece();
                    System.out.println("Select"+" "+sourceTile.toString()+sourceTile.getTileCoordinate());
                    if(movedPiece==null){
                        sourceTile=null;
                        FLAG=0;
                    }else if(movedPiece !=null&&movedPiece.getAlliance()==boardPane.chessBoard.getCurrentPlayer().getAlliance()&&
                            !movedPiece.calculatePossibleMoves(boardPane.chessBoard).isEmpty() ){
                        highLight(boardPane.chessBoard,boardPane);
                        cn=sourceTile.getTileCoordinate();
                        FLAG=1;
                        sourceTile=null;
                    }else{
                        sourceTile=null;
                        destinyTile=null;
                        movedPiece=null;
                        boardPane.drawBoard(boardPane.chessBoard, boardPane);
                        
                    }
                }
            }
        }else{
            if(button==MouseButton.SECONDARY){
                sourceTile=null;
                destinyTile=null;
                movedPiece=null;
                boardPane.drawBoard(boardPane.chessBoard, boardPane);
                FLAG=0;
                cn=0;
                System.out.println("Cancel Select");
            }else if(button==MouseButton.PRIMARY){
                tile t=boardPane.chessBoard.getTile(cn);
                destinyTile=boardPane.chessBoard.getTile(tileID);
                System.out.println("move"+t.toString()+" "+"from"+" "+cn+" "+"to  "+destinyTile.getTileCoordinate());
                final move Move=move.creatMove(boardPane.chessBoard, cn,destinyTile.getTileCoordinate());
                
                if((Move instanceof pawnMove ||Move instanceof pawnjump||Move instanceof PawnAttackMove)&&
                        boardPane.chessBoard.getCurrentPlayer().getAlliance()==WHITE){
                    W_P_M();
                }else if((Move instanceof pawnMove ||Move instanceof pawnjump || Move instanceof PawnAttackMove)&&
                        (boardPane.chessBoard.getCurrentPlayer().getAlliance()==BLACK)){
                    B_P_M();
                }else if(Move instanceof pawnPromotion){
                    P_promotion();
                }else if((Move instanceof RookNormalMove|| Move instanceof RookAttackMove)&&
                        (boardPane.chessBoard.getCurrentPlayer().getAlliance()==WHITE)){
                    W_R_M();
                }else if((Move instanceof RookNormalMove|| Move instanceof RookAttackMove)&&
                        (boardPane.chessBoard.getCurrentPlayer().getAlliance()==BLACK)){
                    B_R_M();
                }else if((Move instanceof NightNormalMove|| Move instanceof NightAttackMove)&&
                        (boardPane.chessBoard.getCurrentPlayer().getAlliance()==BLACK)){
                    B_N_M();
                }else if((Move instanceof NightNormalMove|| Move instanceof NightAttackMove)&&
                        (boardPane.chessBoard.getCurrentPlayer().getAlliance()==WHITE)){
                    W_N_M();
                }else if((Move instanceof BishopNormalMove|| Move instanceof BishopAttackMove)&&
                        (boardPane.chessBoard.getCurrentPlayer().getAlliance()==WHITE)){
                    W_B_M();
                }else if((Move instanceof BishopNormalMove|| Move instanceof BishopAttackMove)&&
                        (boardPane.chessBoard.getCurrentPlayer().getAlliance()==BLACK)){
                    B_B_M();
                }else if((Move instanceof QueenNormalMove|| Move instanceof QueenAttackMove)&&
                        (boardPane.chessBoard.getCurrentPlayer().getAlliance()==WHITE)){
                    W_Q_M();
                }else if((Move instanceof QueenNormalMove|| Move instanceof QueenAttackMove)&&
                        (boardPane.chessBoard.getCurrentPlayer().getAlliance()==BLACK)){
                    B_Q_M();
                }else if((Move instanceof KingNormalMove|| Move instanceof KingAttackMove)&&
                        (boardPane.chessBoard.getCurrentPlayer().getAlliance()==WHITE)){
                    W_K_M();
                }else if((Move instanceof KingNormalMove|| Move instanceof KingAttackMove)&&
                        (boardPane.chessBoard.getCurrentPlayer().getAlliance()==BLACK)){
                    B_K_M();
                }
                
                final MoveTranstion transtion=boardPane.chessBoard.getCurrentPlayer().makeMove(Move);
                boardPane.chessBoard=transtion.getBoard();
                boardPane.drawBoard(boardPane.chessBoard, boardPane);
                
                System.out.println(transtion.getMoveStatues());
                
                
                if(boardPane.chessBoard.getCurrentPlayer().isInCheckMate()){
                    
                    try {
                        Image image1 = new Image(new FileInputStream("art\\G_O.jpg"));
                        ImageView imageView1 = new ImageView(image1);
                        imageView1.setFitHeight(650);
                        imageView1.setFitWidth(650);
                        boardPane.getChildren().add(imageView1);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(TilePane.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String winner=boardPane.chessBoard.getCurrentPlayer().getOpponent().toString();
                    End end=new End();
                    end.winner=winner;
                    end.start(new Stage());
                }else if(boardPane.chessBoard.getCurrentPlayer().isInCheck()){
                    try {
                        Image image1 = new Image(new FileInputStream("art\\red_dot.png"));
                        ImageView imageView1 = new ImageView(image1);
                        imageView1.setFitHeight(30);
                        imageView1.setFitWidth(30);
                        int kingPostion= boardPane.chessBoard.getCurrentPlayer().getPlayerKing().getPostion();
                        TilePane ktp=getTIlePane(kingPostion,boardPane);
                        ktp.getChildren().add(imageView1);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(TilePane.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                
            }
            cn=0;                    
            FLAG=0;
            
        }
    });
    }
     public static void BGMusic1(){
         String bip="audio\\excute.mp3";
     Media hit = new Media(Paths.get(bip).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(hit.getSource());
        mediaPlayer.play();
    }
  
     public TilePane getTIlePane(int i,BoardPane p){
    return p.boardTiles[i];
    }
    public int getTileID(){
    return this.tileID;
    }
    public void drawTilePane(final Board board){
    makeTileColor();
    setTileIcon(board);
    
    }
    //to do use the highlit
    public void  highLight(Board board,BoardPane p){
    for(final move Move:pieceLegalMoves(board)){
             TilePane tp=getTIlePane(Move.getDestinationCoordinate(),p);
             
        try {
            Image image1 = new Image(new FileInputStream("art\\green_dot.png"));
            ImageView imageView1 = new ImageView(image1);
            imageView1.setFitHeight(40); 
            imageView1.setFitWidth(40);
             
            tp.getChildren().add(imageView1);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TilePane.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }
    
    private Collection<move> pieceLegalMoves(Board board){
    if(this.movedPiece!=null &&this.movedPiece.getAlliance()==board.getCurrentPlayer().getAlliance()){
    return this.movedPiece.calculatePossibleMoves(board);
    }
        return null;
    }
    private void setTileIcon(final Board board){
        getChildren().clear();
    if(board.getTile(tileID).isFilled()){
        
        try { 
              Image image = new Image(new FileInputStream("art\\"+
                    board.getTile(tileID).getPiece().getAlliance().toString().substring(0,1)+
                    board.getTile(tileID).getPiece().toString()+".gif"));
              ImageView imageView=new ImageView(image);
            imageView.setFitHeight(40); 
            imageView.setFitWidth(40);
             imageView.setX(20); 
             imageView.setY(20); 
            getChildren().add(imageView);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TilePane.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    }

    private void makeTileColor() {
        if(methods.INSTANCE.FIRST_ROW.get(tileID)||methods.INSTANCE.THIRD_ROW.get(tileID)||
                methods.INSTANCE.FIFTH_ROW.get(tileID)||methods.INSTANCE.SEVENTH_ROW.get(tileID)){
        setStyle(this.tileID % 2==0 ? "-fx-background-color: #fcd703"
                :"-fx-background-color: #0335fc");
        }else if(methods.INSTANCE.SECOND_ROW.get(tileID)||methods.INSTANCE.FOURTH_ROW.get(tileID)||
                methods.INSTANCE.SIXTH_ROW.get(tileID)||methods.INSTANCE.EIGHTH_ROW.get(tileID)){
                setStyle(this.tileID % 2==0 ? "-fx-background-color: #0335fc"
                        :"-fx-background-color: #fcd703");

        }
        
    }
       public static void B_P_M(){
         String id="voice\\B_P_rengo.MP3";
        
     Media b_c = new Media(Paths.get(id).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(b_c.getSource());
        
                mediaPlayer.play();
             
    }
        public static void W_P_M(){
         String id="voice\\W_P_jayce.mp3";
        
     Media b_c = new Media(Paths.get(id).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(b_c.getSource());
        
                mediaPlayer.play();
             
    }
        public static void P_promotion(){
         String id="voice\\P_Promotion.mp3";
        
     Media b_c = new Media(Paths.get(id).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(b_c.getSource());
        
                mediaPlayer.play();
             
    }
        public static void B_N_M(){
         String id="voice\\B_N_zed.mp3";
        
     Media b_c = new Media(Paths.get(id).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(b_c.getSource());
        
                mediaPlayer.play();
             
    }
        public static void W_N_M(){
         String id="voice\\W_N_yasuo.MP3";
        
     Media b_c = new Media(Paths.get(id).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(b_c.getSource());
        
                mediaPlayer.play();
             
    }
        public static void B_B_M(){
         String id="voice\\B_B_trynda.mp3";
        
     Media b_c = new Media(Paths.get(id).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(b_c.getSource());
        
                mediaPlayer.play();
             
    }
        public static void W_B_M(){
         String id="voice\\W_B_daruis.mp3";
        
     Media b_c = new Media(Paths.get(id).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(b_c.getSource());
        
                mediaPlayer.play();
             
    }
        public static void B_R_M(){
         String id="voice\\B_R_nasus.mp3";
        
     Media b_c = new Media(Paths.get(id).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(b_c.getSource());
        
                mediaPlayer.play();
             
    }
        public static void W_R_M(){
         String id="voice\\W_R_yi.mp3";
        
     Media b_c = new Media(Paths.get(id).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(b_c.getSource());
        
                mediaPlayer.play();
             
    }
        public static void B_Q_M(){
         String id="voice\\B_Q_kata.mp3";
        
     Media b_c = new Media(Paths.get(id).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(b_c.getSource());
        
                mediaPlayer.play();
             
    }
        public static void W_Q_M(){
         String id="voice\\W_Q_shyva.mp3";
        
     Media b_c = new Media(Paths.get(id).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(b_c.getSource());
        
                mediaPlayer.play();
             
    }
        public static void B_K_M(){
         String id="voice\\B_K_zed.mp3";
        
     Media b_c = new Media(Paths.get(id).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(b_c.getSource());
        
                mediaPlayer.play();
             
    }
     
      public static void W_K_M(){
         String id="voice\\W_K_j4.mp3";
        
     Media b_c = new Media(Paths.get(id).toUri().toString());
        AudioClip mediaPlayer = new AudioClip(b_c.getSource());
        
                mediaPlayer.play();
             
    }

}




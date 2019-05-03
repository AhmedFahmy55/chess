
package com.chess.GUI;

import com.chess.engine.board.Board;
import com.chess.engine.board.Move.move;
import com.chess.engine.board.Move.moveFactory;
import com.chess.engine.board.Move.normalMove;
import com.chess.engine.board.tile;
import com.chess.engine.pieces.methods;
import com.chess.engine.pieces.piece;
import com.chess.engine.player.MoveTranstion;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javax.swing.SwingUtilities;

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
    
    setOnMouseClicked(e ->{
       MouseButton button = e.getButton();
        if (FLAG == 0) {
        //do frist move
       
    if(button==MouseButton.SECONDARY){
    sourceTile=null;
    destinyTile=null;
    movedPiece=null;
    }else if(button==MouseButton.PRIMARY){
     if(sourceTile==null){
       sourceTile=boardPane.chessBoard.getTile(tileID);
       movedPiece=sourceTile.getPiece();
       
       System.out.println(sourceTile.getTileCoordinate()+"click1"+"  "+sourceTile.toString());
        if(movedPiece==null){
            sourceTile=null;
            FLAG=0;
        }else{
            cn=sourceTile.getTileCoordinate();
        FLAG=1;  
        }
       
  }
    }
        }else{
     if(button==MouseButton.SECONDARY){
    sourceTile=null;
    destinyTile=null;
    movedPiece=null;
    FLAG=0;
    }else if(button==MouseButton.PRIMARY){
    destinyTile=boardPane.chessBoard.getTile(tileID);
     System.out.println(destinyTile.getTileCoordinate()+"  "+cn+"click2"+destinyTile.toString());
        final move Move=moveFactory.creatMove(boardPane.chessBoard, cn,
        destinyTile.getTileCoordinate());
        final MoveTranstion transtion=boardPane.chessBoard.getCurrentPlayer().makeMove(Move);
        boardPane.chessBoard=transtion.getBoard();
                  
    }
        cn=0;
        FLAG=0;
        destinyTile=null;
        movedPiece=null;
        sourceTile=null;
        }
        boardPane.drawBoard(boardPane.chessBoard, boardPane);
        
    });
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
    public void highlight(Board board,BoardPane p){
    for(final move Move:pieceLegalMoves(board)){
             TilePane tp=getTIlePane(Move.getDestinationCoordinate(),p);
             
        try {
            Image image1 = new Image(new FileInputStream("C:\\Users\\Kizaro\\Desktop\\New folder\\JavaApplication7\\"
                    + "art\\misc\\green_dot.png"));
            ImageView imageView1 = new ImageView(image1);
            imageView1.setFitHeight(40); 
            imageView1.setFitWidth(40);
             imageView1.setX(20); 
             imageView1.setY(20); 
            getChildren().add(imageView1);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TilePane.class.getName()).log(Level.SEVERE, null, ex);
        }
     
      }
    }
    
    private Collection<move> pieceLegalMoves(Board board){
    if(this.movedPiece!=null &&this.movedPiece.getAlliance()==board.getCurrentPlayer().getAlliance()){
    return this.movedPiece.possibleMoves(board);
    }
        return null;
    }
    private void setTileIcon(final Board board){
        getChildren().clear();
    if(board.getTile(tileID).isFilled()){
        
        try { 
            Image image = new Image(new FileInputStream("C:\\Users\\Kizaro\\Desktop\\New folder\\"
                    + "JavaApplication7\\art\\holywarriors\\"+
                    board.getTile(tileID).getPiece().getAlliance().toString().substring(0,1)+
                    board.getTile(tileID).getPiece().toString()+".gif"));
            ImageView imageView = new ImageView(image);
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
        if(methods.is1Row(tileID)||methods.is3Row(tileID)||
                methods.is5Row(tileID)||methods.is7Row(tileID)){
        setStyle(this.tileID % 2==0 ? "-fx-background-color: #FFFACD":"-fx-background-color: #47461e");
        }else if(methods.is2Row(tileID)||methods.is4Row(tileID)||
                methods.is6Row(tileID)||methods.is8Row(tileID)){
                setStyle(this.tileID % 2==0 ? "-fx-background-color: #47461e":"-fx-background-color: #FFFACD");

        }
        
    }

}




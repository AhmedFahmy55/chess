
package com.chess.engine.board;
import com.chess.engine.pieces.piece;
import java.util.HashMap;
import java.util.Map;
public abstract class tile {
    protected final int tileNumber ;
     private static final Map<Integer,emptyTile>emptyTiles=creatPosibleEmptyT();
    private static Map<Integer,emptyTile>creatPosibleEmptyT(){
    final Map<Integer,emptyTile>empty_Tiles=new HashMap<>();
    for(int i=0;i<64;i++){
        empty_Tiles.put(i, new emptyTile(i));
    }
    return empty_Tiles ;
    }
    public int getTileCoordinate(){
    return this.tileNumber;
    }
    public static tile creatTIle(final int tileNumb,final piece piece){
    if(piece !=null){
      return  new filledTile(tileNumb,piece);
    }else return new emptyTile(tileNumb);
    }
    protected tile( final int tileNumber) {
        this.tileNumber = tileNumber;
    }
   
  public abstract piece getPiece();
 public abstract  boolean isFilled();
}
// empty tile
final class emptyTile extends tile{
     emptyTile(final int tileNumber){
    super(tileNumber);
    }
     @Override
    public String toString(){
    return "-";
    }
    @Override
 public boolean isFilled(){
return false ;
}
    @Override
    public piece getPiece(){
return null ; 
}
}
// filled tile
 final class filledTile extends tile{
final private piece pieceOnTile ;
filledTile(final int x,final piece y){
super(x);
pieceOnTile=y;
}
@Override
    public String toString(){
    return this.pieceOnTile.getAlliance().isBlack() ? getPiece().toString() : 
            getPiece().toString();
    }
@Override
public boolean isFilled(){
return true ;
}
@Override
public piece getPiece(){
return this.pieceOnTile;
}
}

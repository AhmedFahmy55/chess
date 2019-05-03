

package com.chess.engine.pieces;

import static com.chess.engine.pieces.alliance.BLACK;
import static com.chess.engine.pieces.alliance.WHITE;
import com.google.common.collect.ImmutableList;
import java.util.List;

public class methods {
   public static final int numb_T = 64;
   private static boolean b=true;
   private static boolean a=true;
   private static boolean c=true;
   private static boolean d=true;
   private static boolean e=true;
   private static boolean f=true;
   private static boolean g=true;
   private static boolean h=true;
   private static boolean t=false;
   private static boolean z1=true;
   private static boolean z2=true;


public static boolean is1Row(int i){
    if(i==0||i==1||i==2||i==3||i==4||i==5||i==6||i==7){
    return true;
    }else return false;
}
public static boolean is2Row(int i){
    if(i==8||i==9||i==10||i==11||i==12||i==13||i==14||i==15){
    return true;
    }else return false;
}
public static boolean is3Row(int i){
    if(i==16||i==17||i==18||i==19||i==20||i==21||i==22||i==23){
    return true;
    }else return false;
}
public static boolean is4Row(int i){
    if(i==24||i==25||i==26||i==27||i==28||i==29||i==30||i==31){
    return true;
    }else return false;
}
public static boolean is5Row(int i){
    if(i==32||i==33||i==34||i==35||i==36||i==37||i==38||i==39){
    return true;
    }else return false;
}
public static boolean is6Row(int i){
    if(i==40||i==41||i==42||i==43||i==44||i==45||i==46||i==47){
    return true;
    }else return false;
}
public static boolean is7Row(int i){
    if(i==48||i==55||i==49||i==50||i==51||i==52||i==53||i==54){
    return true;
    }else return false;
}
public static boolean is8Row(int i){
    if(i==63||i==56||i==57||i==58||i==59||i==60||i==61||i==62){
    return true;
    }else return false;
}


   // knight moves exceptions functions
    public static boolean fristColumnExceptions(final piece knightPostion,final int knightMoves){
    if((knightPostion.piecePostion==0||knightPostion.piecePostion==8||knightPostion.piecePostion==16||
            knightPostion.piecePostion==24||knightPostion.piecePostion==32||knightPostion.piecePostion==40||
            knightPostion.piecePostion==48||knightPostion.piecePostion==56) &&(knightMoves==-17||knightMoves==-10||
            knightMoves==15||knightMoves==-6)){
    b=false;
    }
   return b;
}
    public static boolean secondColumnExceptions(final piece knightPostion,final int knightMoves){
    if((knightPostion.piecePostion==1||knightPostion.piecePostion==9||knightPostion.piecePostion==17||
            knightPostion.piecePostion==25||knightPostion.piecePostion==33||knightPostion.piecePostion==41||
            knightPostion.piecePostion==49||knightPostion.piecePostion==57) && (knightMoves==-10||knightMoves==6)){
    a=false;
    }
   return a;
}
    public static boolean sevenColumnExceptions(final piece knightPostion,final int knightMoves){
    if((knightPostion.piecePostion==6||knightPostion.piecePostion==14||knightPostion.piecePostion==22||
            knightPostion.piecePostion==30||knightPostion.piecePostion==38||knightPostion.piecePostion==46||
            knightPostion.piecePostion==54||knightPostion.piecePostion==62) && (knightMoves==10||knightMoves==-6)){
    c=false;
    }
   return c;
}
    public static boolean eightColumnExceptions(final piece knightPostion,final int knightMoves){
    if((knightPostion.piecePostion==7||knightPostion.piecePostion==15||knightPostion.piecePostion==23||
            knightPostion.piecePostion==31||knightPostion.piecePostion==39||knightPostion.piecePostion==47||
            knightPostion.piecePostion==55||knightPostion.piecePostion==63) && (knightMoves==17||knightMoves==-6||
            knightMoves==10||knightMoves==-15)){
    d=false;
    }
   return d;
}

    public static boolean isValliedCordinate(int cordinate){
    return cordinate >=0 && cordinate<64;

   }
   // bishop moves exceptions functions
   public static boolean fristColumnBishopExceptions(final piece pishopPostion,final int bishopMoves ){
   if((pishopPostion.piecePostion==0||pishopPostion.piecePostion==7||pishopPostion.piecePostion==15||
           pishopPostion.piecePostion==23||pishopPostion.piecePostion==31||pishopPostion.piecePostion==39||
           pishopPostion.piecePostion==47||pishopPostion.piecePostion==55) &&(bishopMoves==-9||bishopMoves==7) ){
   e=false;
   }
   return e;
   }
  public static boolean eightColumnBishopExceptions(final piece pishopPostion,final int bishopMoves ){
   if((pishopPostion.piecePostion==63||pishopPostion.piecePostion==7||pishopPostion.piecePostion==15||
           pishopPostion.piecePostion==23||pishopPostion.piecePostion==31||pishopPostion.piecePostion==39||
           pishopPostion.piecePostion==47||pishopPostion.piecePostion==55) &&(bishopMoves==9||bishopMoves==-7) ){
   f=false;
   }
   return f;
   }
  // rook moves exceptions functions
  public static boolean fristColumnRookExceptions(final piece rookPostion,final int rookMoves ){
   if((rookPostion.piecePostion==0||rookPostion.piecePostion==7||rookPostion.piecePostion==15||
           rookPostion.piecePostion==23||rookPostion.piecePostion==31||rookPostion.piecePostion==39||
           rookPostion.piecePostion==47||rookPostion.piecePostion==55) &&(rookMoves==-1) ){
   h=false;
   }
   return h;
   }
  public static boolean eightColumnRookExceptions(final piece pishopPostion,final int bishopMoves ){
   if((pishopPostion.piecePostion==63||pishopPostion.piecePostion==7||pishopPostion.piecePostion==15||
           pishopPostion.piecePostion==23||pishopPostion.piecePostion==31||pishopPostion.piecePostion==39||
           pishopPostion.piecePostion==47||pishopPostion.piecePostion==55) &&(bishopMoves==1) ){
   g=false;
   }
   return g;
   }
  public static boolean black_SecondRow(final piece pawnPostion){
  if(((pawnPostion.getAlliance()==BLACK)&&(pawnPostion.isFristMove())) && (pawnPostion.piecePostion==8||pawnPostion.piecePostion==9||
          pawnPostion.piecePostion==10||pawnPostion.piecePostion==11||pawnPostion.piecePostion==12||
          pawnPostion.piecePostion==13||pawnPostion.piecePostion==14||pawnPostion.piecePostion==15)){
  t=true;
  }
  return t;
  }
  public static boolean white_SevenRow(final piece pawnPostion){
  if(((pawnPostion.getAlliance()==WHITE) && (pawnPostion.isFristMove()))&&(pawnPostion.piecePostion==55||pawnPostion.piecePostion==54||
          pawnPostion.piecePostion==53||pawnPostion.piecePostion==52||pawnPostion.piecePostion==51||
          pawnPostion.piecePostion==50||pawnPostion.piecePostion==49||pawnPostion.piecePostion==48)){
      
  t=true;
  }
  return t;
  }
 public static boolean isFristColumn(final piece knightPostion ){
       if(knightPostion.piecePostion==0||knightPostion.piecePostion==8||knightPostion.piecePostion==16||
               knightPostion.piecePostion==24||knightPostion.piecePostion==32||knightPostion.piecePostion==40||
               knightPostion.piecePostion==48||knightPostion.piecePostion==56){
       z1=true ;
       }
return z1;
 }
  public static boolean isEightColumn(final piece knightPostion){
      if(knightPostion.piecePostion==7||knightPostion.piecePostion==15||knightPostion.piecePostion==23||
              knightPostion.piecePostion==31||knightPostion.piecePostion==39||knightPostion.piecePostion==47||
              knightPostion.piecePostion==55||knightPostion.piecePostion==63){
      z2 = true ;
      }
  return z2 ;
  }
   
}

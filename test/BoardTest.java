
import com.chess.engine.board.Board;
import static com.chess.engine.board.Board.creatStandardBoard;
import com.chess.engine.board.Move.move;
import com.chess.engine.board.Move.moveFactory;
import com.chess.engine.board.Move.normalMove;
import static com.chess.engine.pieces.alliance.BLACK;
import static com.chess.engine.pieces.alliance.WHITE;
import com.chess.engine.pieces.knight;
import com.chess.engine.player.MoveTranstion;


public class BoardTest {
     public static void main(String[] args) {
    Board board=Board.creatStandardBoard();
    for(int i=0;i<16;i++){
      move Move=new normalMove(board,board.getWhitePieces().get(i),30);
   Board b =Move.excute();
       System.out.println(b);
     
    }
   
     }
          
}

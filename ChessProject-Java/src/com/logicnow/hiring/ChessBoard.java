package com.logicnow.hiring;


public class ChessBoard {

    public static int MAX_BOARD_WIDTH = 8;
    public static int MAX_BOARD_HEIGHT = 8;

    private Pawn[][] pieces;

    public ChessBoard() {
        pieces = new Pawn[MAX_BOARD_WIDTH][MAX_BOARD_HEIGHT];

    }

    // TODO look into removing PieceColor from method
    // already stored in Pawn object
    // this method should be overloaded for other Pieces
    public void Add(Pawn pawn, int xCoordinate, int yCoordinate, PieceColor pieceColor) {
        
        // check number of Pieces on Board
        // return false if MAX number reached
        if( !canAddPieces(pieceColor) )
            return;
        
        // can add anywhere on the board as long as no other piece
        // in that position
        if( !IsLegalBoardPosition(xCoordinate, yCoordinate) ) 
            return;
        
        // can't add Pawn to row 0 or row 7
        if( xCoordinate == 0 || xCoordinate == 7 )
            return;

        // check if piece already in this position
        if( pieces[xCoordinate][yCoordinate] == null ) {
            
            pawn.setXCoordinate(xCoordinate);
            pawn.setYCoordinate(yCoordinate);
            pawn.setChessBoard(this);
            pieces[xCoordinate][yCoordinate] = pawn;
            
        }
    }

    // checks if the position on the board is legal
    // DOES NOT check if the position is free
    // DOES NOT check if the position is valid as a result of a Piece Move
    public boolean IsLegalBoardPosition(int xCoordinate, int yCoordinate) {
        
        if(xCoordinate < 0 || xCoordinate >= MAX_BOARD_HEIGHT) {
            return false;
        } else if( yCoordinate < 0 || yCoordinate >= MAX_BOARD_WIDTH) {
            return false;
        }
        
        return true;        
    }

    /**
     * @return the pieces
     */
    public Pawn[][] getPieces() {
        return pieces;
    }
    
    private boolean canAddPieces(PieceColor pieceColor) {
        
        int pawnCnt = 0;
        for (int j = 0; j< pieces[0].length; j++) {
            
            for (int i = 0; i< pieces.length; i++) {     
              
                if( pieces[j][i] != null && 
                    pieces[j][i].getPieceColor() == pieceColor) {
                    pawnCnt++;
                }
            }
        }
        
        if( pawnCnt >= Pawn.MAX_NUM_PIECES )
            return false;
        
        return true;
    }
}

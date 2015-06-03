package com.logicnow.hiring;

public class Pawn {

    private ChessBoard chessBoard;
    private int xCoordinate;
    private int yCoordinate;
    private PieceColor pieceColor;
    public static int MAX_NUM_PIECES = 8;

    public Pawn(PieceColor pieceColor) {
        this.pieceColor = pieceColor;
        this.xCoordinate = -1;
        this.yCoordinate = -1;
    }

    public ChessBoard getChesssBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(int value) {
        this.xCoordinate = value;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(int value) {
        this.yCoordinate = value;
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    private void setPieceColor(PieceColor value) {
        pieceColor = value;
    }

    public void Move(MovementType movementType, int newX, int newY) {
        
        switch( movementType ) {       
            case MOVE: 
                performMove( newX, newY);
                break;
            case CAPTURE:
                throw new UnsupportedOperationException("Need to implement MovementType.CAPTURE") ;
            default:
                throw new UnsupportedOperationException("This operation is unsupported") ;
        }   
    }

    @Override
    public String toString() {
        return CurrentPositionAsString();
    }

    protected String CurrentPositionAsString() {
        String eol = System.lineSeparator();
        return String.format("Current X: {1}{0}Current Y: {2}{0}Piece Color: {3}", eol, xCoordinate, yCoordinate, pieceColor);
    }
    
    // validates and performs Pawn Move
    private void performMove(int newX, int newY) {

        Pawn[][] pieces = chessBoard.getPieces(); 

        // check if valid board position and
        // piece not already on position
        if( !chessBoard.IsLegalBoardPosition(newX, newY) || 
            !(pieces[newX][newY] == null) )
            return;

        // Pawn cannot move in Y direction unless on CAPTURE
        if( yCoordinate != newY ) {
            return;
        }
        // black can only move from x=6 to x=0
        // NOT considering what happens on Pawn Promotion
        if( pieceColor == PieceColor.BLACK &&
            newX >= xCoordinate ) {
                return;
        }
        // white can only move from x=1 to x=7
        else if( pieceColor == PieceColor.WHITE &&
                newX <= xCoordinate ) {
                return;
        }
        
        // check that only moving one square or
        // two on initial move
        int xDiff = Math.abs(xCoordinate - newX);

        if( xDiff == 1 || (xDiff == 2 && ( xCoordinate == 1 || 
                                           xCoordinate == 6 ) ) ) { 
            this.setXCoordinate(newX);
            this.setYCoordinate(newY);
        }       
    }

}

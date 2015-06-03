package com.logicnow.hiring;

//import junit.framework.Assert;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PawnTest {

    private ChessBoard chessBoard;
    private Pawn testSubject;

    @Before
    public void setUp() {
        this.chessBoard = new ChessBoard();
        this.testSubject = new Pawn(PieceColor.BLACK);
    }

    @Test
    public void testChessBoard_Add_Sets_XCoordinate() {
        this.chessBoard.Add(testSubject, 6, 3, PieceColor.BLACK);
        assertEquals(6, testSubject.getXCoordinate());
    }

    @Test
    public void testChessBoard_Add_Sets_YCoordinate() {
        this.chessBoard.Add(testSubject, 6, 3, PieceColor.BLACK);
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Right_DoesNotMove() {
        chessBoard.Add(testSubject, 6, 3, PieceColor.BLACK);
        testSubject.Move(MovementType.MOVE, 6, 4);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    @Test
    public void testPawn_Move_IllegalCoordinates_Left_DoesNotMove() {
        chessBoard.Add(testSubject, 6, 3, PieceColor.BLACK);
        testSubject.Move(MovementType.MOVE, 6, 2);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }
    
    @Test
    public void testPawn_Move_LegalCoordinates_Forward_UpdatesCoordinates() {
        chessBoard.Add(testSubject, 6, 3, PieceColor.BLACK);
        testSubject.Move(MovementType.MOVE, 5, 3);
        assertEquals(5, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    // Test Black can move forward 2 from 6 to 4
    @Test
    public void testPawn_Move_LegalCoordinatesBlack_ForwardTwo_UpdatesCoordinates() {
        chessBoard.Add(testSubject, 6, 3, PieceColor.BLACK);
        testSubject.Move(MovementType.MOVE, 4, 3);
        assertEquals(4, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    // Test White can move forward 2 from 1 to 3
    @Test
    public void testPawn_Move_LegalCoordinatesWhite_ForwardTwo_UpdatesCoordinates() {
        Pawn pawn = new Pawn(PieceColor.WHITE);
        chessBoard.Add(pawn, 1, 3, PieceColor.WHITE);
        pawn.Move(MovementType.MOVE, 3, 3);
        assertEquals(3, pawn.getXCoordinate());
        assertEquals(3, pawn.getYCoordinate());
    }
    
    // Test White can NOT move forward 2 when not on initial row
    @Test
    public void testPawn_Move_IllegalCoordinatesWhite_ForwardTwo_UpdatesCoordinates() {
        Pawn pawn = new Pawn(PieceColor.WHITE);
        chessBoard.Add(pawn, 2, 3, PieceColor.WHITE);
        pawn.Move(MovementType.MOVE, 4, 3);
        assertEquals(2, pawn.getXCoordinate());
        assertEquals(3, pawn.getYCoordinate());
    }
    
    // Test Black can NOT move forward 2 when not on initial row
    @Test
    public void testPawn_Move_IllegalCoordinatesBlack_ForwardTwo_UpdatesCoordinates() {
        chessBoard.Add(testSubject, 5, 3, PieceColor.BLACK);
        testSubject.Move(MovementType.MOVE, 3, 3);
        assertEquals(5, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }
    
    // prevent from adding Black to top row x=7
    @Test
    public void testChessBoard_IllegalAdd_TopRow_Fails() {
        this.chessBoard.Add(testSubject, 7, 3, PieceColor.BLACK);
        assertEquals(-1, testSubject.getXCoordinate());
        assertEquals(-1, testSubject.getYCoordinate());
    }

    // prevent from adding White to bottom row x=0
    @Test
    public void testChessBoard_IllegalAdd_BottomRow_Fails() {
        Pawn pawn = new Pawn(PieceColor.WHITE);
        this.chessBoard.Add(pawn, 0, 3, PieceColor.WHITE);
        assertEquals(-1, testSubject.getXCoordinate());
        assertEquals(-1, testSubject.getYCoordinate());
    }
    
    // Diagonal Move; Should not move
    @Test
    public void testPawn_Move_IllegalCoordinates_Diagonal_DoesNotMove() {
        chessBoard.Add(testSubject, 6, 3, PieceColor.BLACK);
        testSubject.Move(MovementType.MOVE, 5, 2);
        assertEquals(6, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }
    
    // black should not move back
    @Test
    public void testPawn_Move_IllegalCoordinatesBlack_Back_DoesNotMove() {
        chessBoard.Add(testSubject, 5, 3, PieceColor.BLACK);
        testSubject.Move(MovementType.MOVE, 6, 3);
        assertEquals(5, testSubject.getXCoordinate());
        assertEquals(3, testSubject.getYCoordinate());
    }

    // white should not move back
    @Test
    public void testPawn_Move_IllegalCoordinatesWhite_Back_DoesNotMove() {
        Pawn pawn = new Pawn(PieceColor.WHITE);
        chessBoard.Add(pawn, 3, 3, PieceColor.WHITE);
        pawn.Move(MovementType.MOVE, 2, 3);
        assertEquals(3, pawn.getXCoordinate());
        assertEquals(3, pawn.getYCoordinate());
    }


}
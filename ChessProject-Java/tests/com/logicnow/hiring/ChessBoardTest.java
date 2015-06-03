package com.logicnow.hiring;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChessBoardTest extends TestCase {

    private ChessBoard testSubject;

    @Before
    public void setUp() throws Exception {
        testSubject = new ChessBoard();
    }

    @Test
    public void testHas_MaxBoardWidth_of_8() {
        assertEquals(8, ChessBoard.MAX_BOARD_HEIGHT);
    }

    @Test
    public void testHas_MaxBoardHeight_of_8() {
        assertEquals(8, ChessBoard.MAX_BOARD_HEIGHT);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_0_Y_equals_0() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(0, 0);
        assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_True_X_equals_5_Y_equals_5() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(5, 5);
        Assert.assertTrue(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_5() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(11, 5);
        assertFalse(isValidPosition);
    }

    // Test bounds position
    @Test
    public void testIsLegalBoardPosition_False_X_equals_8_Y_equals_5() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(8, 5);
        assertFalse(isValidPosition);
    }

    // Test bounds position
    @Test
    public void testIsLegalBoardPosition_False_X_equals_7_Y_equals_8() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(7, 8);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_0_Y_equals_9() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(0, 9);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_X_equals_11_Y_equals_0() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(11, 0);
        assertFalse(isValidPosition);
    }

    @Test
    public void testIsLegalBoardPosition_False_For_Negative_Y_Values() {
        boolean isValidPosition = testSubject.IsLegalBoardPosition(5, -1);
        Assert.assertFalse(isValidPosition);
    }

    @Test
    public void Avoids_Duplicate_Positioning() {
        Pawn firstPawn = new Pawn(PieceColor.BLACK);
        Pawn secondPawn = new Pawn(PieceColor.BLACK);
        testSubject.Add(firstPawn, 6, 3, PieceColor.BLACK);
        testSubject.Add(secondPawn, 6, 3, PieceColor.BLACK);
        assertEquals(6, firstPawn.getXCoordinate());
        assertEquals(3, firstPawn.getYCoordinate());
        assertEquals(-1, secondPawn.getXCoordinate());
        assertEquals(-1, secondPawn.getYCoordinate());
    }

    @Test
    public void testLimits_The_Number_Of_Pawns()
    {
        for (int i = 0; i < 10; i++)
        {
            Pawn pawn = new Pawn(PieceColor.BLACK);
            int row = i / ChessBoard.MAX_BOARD_WIDTH;
            testSubject.Add(pawn, 6 + row, i % ChessBoard.MAX_BOARD_WIDTH, PieceColor.BLACK);
            if (row < 1)
            {
                assertEquals(6 + row, pawn.getXCoordinate());
                assertEquals(i % ChessBoard.MAX_BOARD_WIDTH, pawn.getYCoordinate());
            }
            else
            {
                assertEquals(-1, pawn.getXCoordinate());
                assertEquals(-1, pawn.getYCoordinate());
            }
        }
    }
    
    // Can only add MAX_NUM_PIECES (8) pawn pieces of the same color
    @Test
    public void testLimits_The_NumberBlack_Of_Pawns()
    {
        for (int i = 0; i < 10; i++)
        {
            Pawn pawn = new Pawn(PieceColor.BLACK);
            if( i < Pawn.MAX_NUM_PIECES )
                testSubject.Add(pawn, 6, i, PieceColor.BLACK);
            else
                testSubject.Add(pawn, 5, i - Pawn.MAX_NUM_PIECES, PieceColor.BLACK);
            if (i < Pawn.MAX_NUM_PIECES)
            {
                assertEquals(6, pawn.getXCoordinate());
                assertEquals(i, pawn.getYCoordinate());
            }
            else
            {
                assertEquals(-1, pawn.getXCoordinate());
                assertEquals(-1, pawn.getYCoordinate());
            }
        }
    }
    
}
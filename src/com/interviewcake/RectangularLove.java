package com.interviewcake;

import static org.junit.Assert.assertEquals;

import com.interviewcake.datastructures.Rectangle;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class RectangularLove {
    public static Rectangle findRectangularOverlap(Rectangle rect1, Rectangle rect2) {

        // calculate the overlap between the two rectangles

        /*
            Find the max x co-ordinate?
            How to know they overlap?


        */
        int maxXBegin = Math.max(rect1.getLeftX(), rect2.getLeftX());
        int maxYBegin = Math.max(rect1.getBottomY(), rect2.getBottomY());
        int rightEndX = Math.min(rect1.getLeftX() + rect1.getWidth(), rect2.getLeftX() + rect2.getWidth());
        int topEndY = Math.min(rect1.getBottomY() + rect1.getHeight(), rect2.getBottomY() + rect2.getHeight());

        int width = rightEndX - maxXBegin;
        int height = topEndY - maxYBegin;
        if(width <= 0 || height <= 0)
            return new Rectangle();

        return new Rectangle(maxXBegin, maxYBegin, width, height);
    }


















    // tests

    @Test
    public void overlapAlongBothAxesTest() {
        final Rectangle actual = findRectangularOverlap(
            new Rectangle(1, 1, 6, 3), new Rectangle(5, 2, 3, 6));
        final Rectangle expected = new Rectangle (5, 2, 2, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void oneRectangleInsideAnotherTest() {
        final Rectangle actual = findRectangularOverlap(
            new Rectangle(1, 1, 6, 6), new Rectangle(3, 3, 2, 2));
        final Rectangle expected = new Rectangle(3, 3, 2, 2);
        assertEquals(expected, actual);
    }

    @Test
    public void bothRectanglesTheSameTest() {
        final Rectangle actual = findRectangularOverlap(
            new Rectangle(2, 2, 4, 4), new Rectangle(2, 2, 4, 4));
        final Rectangle expected = new Rectangle(2, 2, 4, 4);
        assertEquals(expected, actual);
    }

    @Test
    public void touchOnHorizontalEdgeTest() {
        final Rectangle actual = findRectangularOverlap(
            new Rectangle(1, 2, 3, 4), new Rectangle(2, 6, 2, 2));
        final Rectangle expected = new Rectangle();
        assertEquals(expected, actual);
    }

    @Test
    public void touchOnVerticalEdgeTest() {
        final Rectangle actual = findRectangularOverlap(
            new Rectangle(1, 2, 3, 4), new Rectangle(4, 3, 2, 2));
        final Rectangle expected = new Rectangle();
        assertEquals(expected, actual);
    }

    @Test
    public void touchAtCornerTest() {
        final Rectangle actual = findRectangularOverlap(
            new Rectangle(1, 1, 2, 2), new Rectangle(3, 3, 2, 2));
        final Rectangle expected = new Rectangle();
        assertEquals(expected, actual);
    }

    @Test
    public void noOverlapTest() {
        final Rectangle actual = findRectangularOverlap(
            new Rectangle(1, 1, 2, 2), new Rectangle(4, 6, 3, 6));
        final Rectangle expected = new Rectangle();
        assertEquals(expected, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(RectangularLove.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}
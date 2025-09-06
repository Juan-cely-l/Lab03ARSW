package edu.eci.arsw.blueprints.test;

import edu.eci.arsw.blueprints.filters.impl.RedundancyFilter;
import edu.eci.arsw.blueprints.filters.impl.SubsamplingFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.junit.Test;
import static org.junit.Assert.*;

public class FilterTest {

    @Test
    public void redundancyFilterShouldRemoveConsecutiveRepeatedPoints() {
        // Arrange
        Point[] points = new Point[]{
                new Point(1, 1),
                new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3),
                new Point(3, 3),
                new Point(4, 4)
        };
        Blueprint bp = new Blueprint("author", "test", points);
        RedundancyFilter filter = new RedundancyFilter();

        // Act
        Blueprint filteredBp = filter.applyFilter(bp);

        // Assert
        assertEquals(4, filteredBp.getPoints().size());
        assertEquals(1, filteredBp.getPoints().get(0).getX());
        assertEquals(2, filteredBp.getPoints().get(1).getX());
        assertEquals(3, filteredBp.getPoints().get(2).getX());
        assertEquals(4, filteredBp.getPoints().get(3).getX());
    }

    @Test
    public void subsamplingFilterShouldRemoveEveryOtherPoint() {
        // Arrange
        Point[] points = new Point[]{
                new Point(1, 1),
                new Point(2, 2),
                new Point(3, 3),
                new Point(5, 5)
        };
        Blueprint bp = new Blueprint("author", "test", points);
        SubsamplingFilter filter = new SubsamplingFilter();


        Blueprint filteredBp = filter.applyFilter(bp);


        assertEquals(3, filteredBp.getPoints().size());
        assertEquals(1, filteredBp.getPoints().get(0).getX());
        assertEquals(3, filteredBp.getPoints().get(1).getX());
        assertEquals(5, filteredBp.getPoints().get(2).getX());
    }
}
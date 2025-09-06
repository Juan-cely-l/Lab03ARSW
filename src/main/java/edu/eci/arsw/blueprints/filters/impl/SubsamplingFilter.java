package edu.eci.arsw.blueprints.filters.impl;

import edu.eci.arsw.blueprints.filters.BlueprintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class SubsamplingFilter implements BlueprintFilter {

    @Override
    public Blueprint applyFilter(Blueprint bp) {
        List<Point> points = bp.getPoints();
        List<Point> filteredPoints = new ArrayList<>();

        // Mantener solo los puntos con índice par
        for (int i = 0; i < points.size(); i++) {
            if (i % 2 == 0) {
                filteredPoints.add(points.get(i));
            }
        }

        Blueprint filteredBp = new Blueprint(bp.getAuthor(), bp.getName());
        for (Point p : filteredPoints) {
            filteredBp.addPoint(p);
        }

        return filteredBp;
    }
}

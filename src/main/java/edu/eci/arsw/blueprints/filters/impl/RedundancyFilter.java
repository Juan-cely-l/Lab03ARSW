package edu.eci.arsw.blueprints.filters.impl;

import edu.eci.arsw.blueprints.filters.BlueprintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RedundancyFilter implements BlueprintFilter {

    @Override
    public Blueprint applyFilter(Blueprint bp) {
        List<Point> points = bp.getPoints();
        List<Point> filteredPoints = new ArrayList<>();

        if (points.isEmpty()) {
            return bp;
        }

        // Primer punto siempre se agrega
        filteredPoints.add(points.get(0));

        // Comparar cada punto con el anterior
        for (int i = 1; i < points.size(); i++) {
            Point current = points.get(i);
            Point previous = points.get(i - 1);

            // Agregar solo si es diferente al anterior
            if (current.getX() != previous.getX() || current.getY() != previous.getY()) {
                filteredPoints.add(current);
            }
        }

        // Crear nuevo blueprint con los puntos filtrados
        Blueprint filteredBp = new Blueprint(bp.getAuthor(), bp.getName());
        for (Point p : filteredPoints) {
            filteredBp.addPoint(p);
        }

        return filteredBp;
    }
}

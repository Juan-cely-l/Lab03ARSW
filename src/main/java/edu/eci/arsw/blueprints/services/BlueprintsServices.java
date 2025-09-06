package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.filters.BlueprintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlueprintsServices {

    @Autowired
    BlueprintsPersistence bpp;

    @Autowired
    BlueprintFilter filter;

    /**
     * Adds a new blueprint to the system
     * @param bp the blueprint to add
     * @throws BlueprintPersistenceException if the blueprint already exists
     */
    public void addNewBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        bpp.saveBlueprint(bp);
    }

    /**
     * Gets all blueprints from the system with filtering applied
     * @return all blueprints with filter applied
     */
    public Set<Blueprint> getAllBlueprints() {
        Set<Blueprint> allBlueprints = bpp.getAllBlueprints();
        Set<Blueprint> filteredBlueprints = new HashSet<>();

        for (Blueprint bp : allBlueprints) {
            filteredBlueprints.add(filter.applyFilter(bp));
        }

        return filteredBlueprints;
    }

    /**
     * Gets a blueprint by author and name with filtering applied
     * @param author blueprint's author
     * @param name blueprint's name
     * @return the blueprint of the given name created by the given author with filter applied
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author, String name) throws BlueprintNotFoundException {
        Blueprint blueprint = bpp.getBlueprint(author, name);
        return filter.applyFilter(blueprint);
    }

    /**
     * Gets all blueprints by an author with filtering applied
     * @param author blueprint's author
     * @return all the blueprints of the given author with filter applied
     * @throws BlueprintNotFoundException if the given author doesn't exist
     */
    public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> authorBlueprints = bpp.getBlueprintByAuthor(author);
        Set<Blueprint> filteredBlueprints = new HashSet<>();

        for (Blueprint bp : authorBlueprints) {
            filteredBlueprints.add(filter.applyFilter(bp));
        }

        return filteredBlueprints;
    }
}
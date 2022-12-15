package nl.aniketic.survival.game.pathfinding;

import nl.aniketic.survival.game.level.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AStarPathfindingController {

    public List<Node> getPath(Node startNode, Node targetNode) {
        List<Node> nodesToSearch = new ArrayList<>();
        nodesToSearch.add(startNode);
        List<Node> nodesProcessed = new ArrayList<>();

        while (!nodesToSearch.isEmpty()) {
            Node current = nodesToSearch.get(0);
            for (Node n : nodesToSearch) {
                if (n.getF() < current.getF()
                        || n.getF() == current.getF() && n.getH() < current.getH()) {
                    current = n;
                }
            }

            nodesProcessed.add(current);
            nodesToSearch.remove(current);

            if (current.equals(targetNode)) {
                Node currentPathTile = targetNode;
                List<Node> path = new ArrayList<>();
                while (currentPathTile != startNode) {
                    path.add(currentPathTile);
                    currentPathTile = currentPathTile.getConnection();
                }
                return path;
            }


            List<Node> neighbours = current.getNeighbours().stream()
                    .filter(n -> !n.isSolid() && !nodesProcessed.contains(n))
                    .collect(Collectors.toList());

            for (Node neighbour : neighbours) {
                boolean inSearch = nodesToSearch.contains(neighbour);
                int costToNeighbour = current.getG() + current.getDistance(neighbour);

                if (!inSearch || costToNeighbour < neighbour.getG()) {
                    neighbour.setG(costToNeighbour);
                    neighbour.setConnection(current);

                    if (!inSearch) {
                        neighbour.setH(neighbour.getDistance(targetNode));
                        nodesToSearch.add(neighbour);
                    }
                }
            }
        }

        return null;
    }
}

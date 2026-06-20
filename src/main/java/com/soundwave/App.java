package com.soundwave;

import com.arcadedb.database.Database;
import com.arcadedb.database.DatabaseFactory;
import com.arcadedb.graph.Vertex;
import com.arcadedb.query.sql.executor.Result;
import com.arcadedb.query.sql.executor.ResultSet;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.model.MutableNode;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static guru.nidi.graphviz.model.Factory.*;

/**
 * Hero Graph Visualization
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Loading Hero Graph...");

        try (DatabaseFactory databaseFactory = new DatabaseFactory("Heroes");
                Database db = databaseFactory.open()) {

            // Clear and create fresh sample data
            db.begin();
            db.command("SQL", "DELETE FROM IsFriendOf");
            db.command("SQL", "DELETE FROM Hero");
            db.commit();

            // Create sample heroes
            db.begin();
            Vertex jay = db.newVertex("Hero")
                    .set("firstName", "Jay")
                    .set("lastName", "Miner")
                    .save();
            Vertex elon = db.newVertex("Hero")
                    .set("firstName", "Elon")
                    .set("lastName", "Musk")
                    .save();
            Vertex steve = db.newVertex("Hero")
                    .set("firstName", "Steve")
                    .set("lastName", "Jobs")
                    .save();
            Vertex bill = db.newVertex("Hero")
                    .set("firstName", "Bill")
                    .set("lastName", "Gates")
                    .save();

            // Create friendships
            jay.newEdge("IsFriendOf", elon, true)
                    .set("since", "1990")
                    .save();
            jay.newEdge("IsFriendOf", steve, true)
                    .set("since", "1985")
                    .save();
            elon.newEdge("IsFriendOf", bill, true)
                    .set("since", "2000")
                    .save();
            steve.newEdge("IsFriendOf", bill, true)
                    .set("since", "1995")
                    .save();

            db.commit();

            // Build Graphviz graph
            MutableGraph g = mutGraph("Heroes")
                    .setDirected(true);

            // Get all vertices and store them
            Map<String, MutableNode> nodes = new HashMap<>();
            ResultSet vertices = db.query("SQL", "SELECT @rid, firstName, lastName FROM Hero");
            while (vertices.hasNext()) {
                Result v = vertices.next();
                String id = v.getProperty("@rid").toString();
                String firstName = v.getProperty("firstName");
                String lastName = v.getProperty("lastName");
                String label = firstName + "\n" + lastName;

                MutableNode node = mutNode(id)
                        .add("label", label)
                        .add("shape", "box")
                        .add("style", "rounded,filled")
                        .add("fillcolor", "lightblue");
                nodes.put(id, node);
                g = g.add(node);
            }

            // Get all edges
            ResultSet edges = db.query("SQL", "SELECT @in, @out FROM IsFriendOf");
            while (edges.hasNext()) {
                Result e = edges.next();
                String inId = e.getProperty("@in").toString();
                String outId = e.getProperty("@out").toString();

                if (nodes.containsKey(inId) && nodes.containsKey(outId)) {
                    MutableNode from = nodes.get(outId);
                    MutableNode to = nodes.get(inId);
                    g = g.add(from.addLink(to));
                }
            }

            // Generate and display graph
            String outputPath = "hero_graph.png";
            Graphviz.fromGraph(g).render(Format.PNG).toFile(new File(outputPath));
            System.out.println("Graph saved to: " + new File(outputPath).getAbsolutePath());
            System.out.println("Opening graph visualization...");

            // Open the image
            try {
                File imageFile = new File(outputPath).getAbsoluteFile();
                if (imageFile.exists()) {
                    String os = System.getProperty("os.name").toLowerCase();
                    if (os.contains("win")) {
                        Runtime.getRuntime().exec(new String[] { "cmd", "/c", "start", imageFile.getAbsolutePath() });
                    } else if (os.contains("mac")) {
                        Runtime.getRuntime().exec(new String[] { "open", imageFile.getAbsolutePath() });
                    } else {
                        Runtime.getRuntime().exec(new String[] { "xdg-open", imageFile.getAbsolutePath() });
                    }
                    System.out.println("Graph opened successfully!");
                } else {
                    System.out.println("Error: PNG file not found at " + imageFile.getAbsolutePath());
                }
            } catch (Exception openErr) {
                System.out.println("Could not open image file automatically. Open manually: "
                        + new File(outputPath).getAbsolutePath());
                openErr.printStackTrace();
            }

        } catch (Exception e) {
            System.err.println("Error during graph generation:");
            e.printStackTrace();
        }
    }
}

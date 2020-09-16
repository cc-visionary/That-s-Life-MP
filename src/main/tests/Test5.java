package main.tests;

import main.Generator;
import main.paths.Path;

import java.util.ArrayList;

/**
 * <h1>Test Script #5</h1>
 * <p>This test script tests whether or not the generation of the Board is working properly or not</p>
 *
 * <h1>Sequence</h1>
 * <ol>
 *     <li>Generate the Board</li>
 *     <li>Seperately assign the 2 starting paths to their respective variables</li>
 *     <li>Apply Pre-Order Traversal in both Career Path and College Path</li>
 * </ol>
 *
 * <p>This proves that the Board generation works and the path produced is the same with the
 * <a href="https://app.lucidchart.com/invitations/accept/151b81a7-1b87-4ad8-8a07-25f41bef561c">Path Schema</a>.</p>
 *
 * @see Generator
 * @see main.players.Player
 *
 * @since 09/05/2020
 */
public class Test5 {
    public static void main(String[] args) {
        Path startingPaths[] = Generator.generateBoard();
        Path careerPath = startingPaths[0];
        Path collegePath = startingPaths[1];

        System.out.println("Pre-order Traversal for Career Path");
        preorderTraversal(careerPath);
        System.out.println("\nPre-order Traversal for College Path");
        preorderTraversal(collegePath);
    }

    public static void preorderTraversal(Path path) {
        if(path != null) {
            System.out.println(path.getUniqueName() + " : " + path.getNSpaces() );
            preorderTraversal(path.getPath1());
            preorderTraversal(path.getPath2());
        }
    }
}

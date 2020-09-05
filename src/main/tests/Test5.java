package main.tests;

import main.Generator;
import main.paths.Path;

/**
 * <h1>Test Script #5</h1>
 * <p>This test script tests whether or not the generation of the Board is working properly or not</p>
 *
 * <h1>Sequence</h1>
 * <ol>
 *     <li>Generate the Board</li>
 *     <li>Seperately assign the 2 starting paths to their respective variables</li>
 *     <li>Traverse both Career Path and College Path</li>
 *     <li>Print both</li>
 * </ol>
 *
 * <p>This proves that the Board generation works.</p>
 */
public class Test5 {
    public static void main(String[] args) {
        Path startingPaths[] = Generator.generateBoard();
        Path careerPath = startingPaths[0];
        Path collegePath = startingPaths[1];

        Path currPath = careerPath;
        do {

        } while(currPath.getPath1() != null || currPath.getPath2() != null);
        System.out.println(careerPath);
        System.out.println(collegePath);
    }
}

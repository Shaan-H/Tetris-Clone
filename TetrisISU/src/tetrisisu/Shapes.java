/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisisu;

import java.awt.Color;
import static java.awt.Color.*;
import java.awt.Point;

/**
 *
 * @author Admin
 */
public class Shapes {
    public final Point[][][] TetrisShapes = {
      
	// I-Piece
	{
            { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) },
            { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) },
            { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1) },
            { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3) }
	},
			
	// J-Piece
	{
            { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 0) },
            { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2) },
            { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 2) },
            { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 0) }
	},
			
	// L-Piece
	{
            { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(2, 2) },
            { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(0, 2) },
            { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(0, 0) },
            { new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 0) }
	},
			
	// O-Piece
	{
            { new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
            { new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
            { new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) },
            { new Point(0, 0), new Point(0, 1), new Point(1, 0), new Point(1, 1) }
=======
        //i piece
        {
            {new Point(0,0), new Point(0,1), new Point(0,2), new Point(0,3)},
            {new Point(0,0), new Point(1,0), new Point(2,0), new Point(3,0)},
            {new Point(0,0), new Point(0,1), new Point(0,2), new Point(0,3)},
            {new Point(0,0), new Point(1,0), new Point(2,0), new Point(3,0)},
        },
        //j piece
        {
            {new Point(0,-1), new Point(0,0), new Point(0,1), new Point(0,2)},
            {new Point(0,1), new Point(0,0), new Point(1,0), new Point(2,0)},
            {new Point(0,0), new Point(1,0), new Point(0,-1), new Point(0,-2)},
            {new Point(), new Point(), new Point(), new Point()},
        },
        //L piece
        {
            {new Point(), new Point(), new Point(), new Point()},
            {new Point(), new Point(), new Point(), new Point()},
            {new Point(), new Point(), new Point(), new Point()},
            {new Point(), new Point(), new Point(), new Point()},
        },
        //O piece
        {
            {new Point(), new Point(), new Point(), new Point()},
            {new Point(), new Point(), new Point(), new Point()},
            {new Point(), new Point(), new Point(), new Point()},
            {new Point(), new Point(), new Point(), new Point()},
>>>>>>> 8eb68d7eb9ac6114f8b3b51516f5bef94f536134
        },
			
        // S-Piece
        {
            { new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) },
            { new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) },
            { new Point(1, 0), new Point(2, 0), new Point(0, 1), new Point(1, 1) },
            { new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) }
        },
			
        // T-Piece
        {
            { new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(2, 1) },
            { new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2) },
            { new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(1, 2) },
            { new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2) }
        },
			
        // Z-Piece
        {
            { new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) },
            { new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) },
            { new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1) },
            { new Point(1, 0), new Point(0, 1), new Point(1, 1), new Point(0, 2) }
        }
    };
    public Color[] ShapeColor = {cyan, blue, orange,yellow, green, pink,red};
}

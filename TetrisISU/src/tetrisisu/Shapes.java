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
            {new Point(), new Point(), new Point(), new Point()},
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
        },
        //S piece
        {
            {new Point(), new Point(), new Point(), new Point()},
            {new Point(), new Point(), new Point(), new Point()},
            {new Point(), new Point(), new Point(), new Point()},
            {new Point(), new Point(), new Point(), new Point()},
        },
        //T piece
        {
            {new Point(), new Point(), new Point(), new Point()},
            {new Point(), new Point(), new Point(), new Point()},
            {new Point(), new Point(), new Point(), new Point()},
            {new Point(), new Point(), new Point(), new Point()},
        },
        //Z piece
        {
            {new Point(), new Point(), new Point(), new Point()},
            {new Point(), new Point(), new Point(), new Point()},
            {new Point(), new Point(), new Point(), new Point()},
            {new Point(), new Point(), new Point(), new Point()},
        },
    };
    public Color[] ShapeColor = {cyan, blue, orange,yellow, green, pink,red};
}

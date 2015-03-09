package au.net.hal9000.heisenberg.jbox2ddemo;

import org.jbox2d.common.Color3f;

import au.net.hal9000.heisenberg.units.Position;

public class MyCircle {

    public Position position;
    public Color3f color;

    public MyCircle(Position position, Color3f color) {
        this.position = position;
        this.color = color;
    }

}
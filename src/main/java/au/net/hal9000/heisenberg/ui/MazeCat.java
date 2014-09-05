package au.net.hal9000.heisenberg.ui;

import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.framework.TestbedSettings;
import org.jbox2d.testbed.framework.TestbedTest;

public class MazeCat extends TestbedTest {
    private static final long CAT_TAG = 100l;
    private static final long RAT_TAG = 101l;
    private Body cat;
    private Body rat;


    @Override
    public synchronized void step(TestbedSettings settings) {
        float cat_max_normal_impulse = 0.002f;
        super.step(settings);
        Vec2 impulse = rat.getPosition().sub(cat.getPosition());
        if (impulse.length() > cat_max_normal_impulse){
            impulse.normalize();
            impulse.mulLocal(cat_max_normal_impulse);
        }
        cat.applyLinearImpulse(impulse, cat.getPosition());
        setCamera(cat.getPosition());
    }
    
    
    @Override
    public void initTest(boolean deserialized) {
        if (deserialized) {
            return;
        }
        World world = getWorld();

        // Cancel gravity because we are looking from above.
        world.setGravity(new Vec2(0, 0));

        // Boundary Walls
        {

            float x = 10;
            float y = 15;
            Vec2[] vs = new Vec2[5];

            vs[0] = new Vec2(-x, -y);
            vs[1] = new Vec2(-x, +y);
            vs[2] = new Vec2(+x, +y);
            vs[3] = new Vec2(+x, -y);
            vs[4] = new Vec2(-x, -y);
            ChainShape shape = new ChainShape();
            shape.createChain(vs, 5);

            FixtureDef fd = new FixtureDef();
            fd.shape = shape;
            fd.density = 0.5f;
            fd.friction = 0.3f;
            fd.restitution = 0.2f;

            // body definition
            BodyDef bd = new BodyDef();
            bd.position.set(5, 5);
            bd.type = BodyType.STATIC;
            Body body = world.createBody(bd);

            body.createFixture(fd);
        }

        // Internal Wall
        {

            float x = 5;
            float y = 5;
            Vec2[] vs = new Vec2[4];

            vs[0] = new Vec2(-x, -y);
            vs[1] = new Vec2(-x, 0);
            vs[2] = new Vec2(+x, 0);
            vs[3] = new Vec2(+x, -y);
            ChainShape shape = new ChainShape();
            shape.createChain(vs, 4);

            FixtureDef fd = new FixtureDef();
            fd.shape = shape;
            fd.density = 0.5f;
            fd.friction = 0.3f;
            fd.restitution = 0.2f;

            // body definition
            BodyDef bd = new BodyDef();
            bd.position.set(5, 5);
            bd.type = BodyType.STATIC;
            Body body = world.createBody(bd);

            body.createFixture(fd);
        }

        // Cat
        {
            // PolygonShape shape = new PolygonShape();
            // Vec2 vertices[] = new Vec2[8];
            // vertices[0] = new Vec2(-1.5f, -0.5f);
            // vertices[1] = new Vec2(1.5f, -0.5f);
            // vertices[2] = new Vec2(1.5f, 0.0f);
            // vertices[3] = new Vec2(0.0f, 0.9f);
            // vertices[4] = new Vec2(-1.15f, 0.9f);
            // vertices[5] = new Vec2(-1.5f, 0.2f);
            // shape.set(vertices, 6);

            CircleShape shape = new CircleShape();
            shape.m_radius = 0.15f;

            FixtureDef fd = new FixtureDef();
            fd.shape = shape;
            fd.density = 0.5f;
            fd.friction = 0.3f;
            fd.restitution = 0.2f;

            // body definition
            BodyDef bd = new BodyDef();
            bd.type = BodyType.DYNAMIC;
            bd.position.set(5.0f, -5.0f);
            cat = world.createBody(bd);
            cat.createFixture(fd);
        }

        // Rat
        {

            CircleShape shape = new CircleShape();
            shape.m_radius = 0.05f;

            FixtureDef fd = new FixtureDef();
            fd.shape = shape;
            fd.density = 0.5f;
            fd.friction = 0.3f;
            fd.restitution = 0.2f;

            // body definition
            BodyDef bd = new BodyDef();
            bd.type = BodyType.DYNAMIC;
            bd.position.set(14.0f, 0.0f);
            rat = world.createBody(bd);
            rat.createFixture(fd);
        }
    }

    @Override
    public String getTestName() {
        return "Maze Cat";
    }

    @Override
    public float getDefaultCameraScale() {
        return 35;
    }

    @Override
    public Long getTag(Body body) {
        if (body == cat) {
            return CAT_TAG;
        }
        if (body == rat) {
            return RAT_TAG;
        }
        return super.getTag(body);
    }

    @Override
    public void processBody(Body body, Long tag) {
        if (tag == CAT_TAG) {
            cat = body;
        } else if (tag == RAT_TAG) {
            rat = body;
        } else {
            super.processBody(body, tag);
        }
    }

    @Override
    public boolean isSaveLoadEnabled() {
        return true;
    }

}

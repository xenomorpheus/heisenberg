package au.net.hal9000.heisenberg.ui;

import org.jbox2d.callbacks.RayCastCallback;
import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Color3f;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.jbox2d.testbed.framework.TestbedSettings;
import org.jbox2d.testbed.framework.TestbedTest;

public class MazeCat extends TestbedTest {
    private static final long CAT_TAG = 100l;
    private static final long RAT_TAG = 101l;
    private static final long OUTER_WALL_TAG = 102l;
    private static final long BARRIER_TAG = 103l;
    private Body cat;
    private Body rat;

    @Override
    public synchronized void step(TestbedSettings settings) {

        float cat_max_normal_impulse = 0.002f;
        super.step(settings);

        Vec2 impulse = rat.getPosition().sub(cat.getPosition());
        if (impulse.length() > cat_max_normal_impulse) {
            impulse.normalize();
            impulse.mulLocal(cat_max_normal_impulse);
        }
        vision();
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
            bd.userData = OUTER_WALL_TAG;
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
            bd.userData = BARRIER_TAG;
            Body body = world.createBody(bd);

            body.createFixture(fd);
        }

        // Cat
        {
            CircleShape shape = new CircleShape();
            shape.m_radius = 0.2f;

            FixtureDef fd = new FixtureDef();
            fd.shape = shape;
            fd.density = 0.5f;
            fd.friction = 0.3f;
            fd.restitution = 0.2f;

            // body definition
            BodyDef bd = new BodyDef();
            bd.type = BodyType.DYNAMIC;
            bd.position.set(5.0f, -5.0f);
            bd.userData = CAT_TAG;
            cat = world.createBody(bd);
            cat.createFixture(fd);
        }

        // Rat
        {

            CircleShape shape = new CircleShape();
            shape.m_radius = 0.1f;

            FixtureDef fd = new FixtureDef();
            fd.shape = shape;
            fd.density = 0.5f;
            fd.friction = 0.3f;
            fd.restitution = 0.2f;

            // body definition
            BodyDef bd = new BodyDef();
            bd.type = BodyType.DYNAMIC;
            bd.position.set(5.0f, 5.0f);
            bd.userData = RAT_TAG;
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

    // look for obstacles from Cat to Rat
    private void vision() {
        RayCastMultipleCallback mcallback = new RayCastMultipleCallback();
        mcallback.init();
        Vec2 catPos = cat.getPosition();
        Vec2 ratPos = rat.getPosition();

        getWorld().raycast(mcallback, catPos, ratPos);

        getDebugDraw().drawSegment(catPos, ratPos,
                new Color3f(0.8f, 0.8f, 0.8f));

        for (int i = 0; i < mcallback.m_count; ++i) {
            Vec2 point = mcallback.m_points[i];
            // Vec2 normal = mcallback.m_normals[i];
            Long tag = (Long)mcallback.m_user_data[i];
            System.out.println("Saw tag="+tag+", at pos="+point);
        }

    }

    // This ray cast collects multiple hits along the ray. Polygon 0 is
    // filtered.
    private class RayCastMultipleCallback implements RayCastCallback {
        static final float RAYCAST_TERMINATE = 0f;
        // static final float RAYCAST_FILTER = -1f;
        static final float RAYCAST_CONTINUE = 1f;

        static final int E_MAX_COUNT = 30;
        private Vec2 m_points[] = new Vec2[E_MAX_COUNT];
        private Vec2 m_normals[] = new Vec2[E_MAX_COUNT];
        private Object m_user_data[] = new Object[E_MAX_COUNT];
        private int m_count;

        public void init() {
            for (int i = 0; i < E_MAX_COUNT; i++) {
                m_points[i] = new Vec2();
                m_normals[i] = new Vec2();
                m_user_data[i] = null;
            }
            m_count = 0;
        }

        @Override
        public float reportFixture(Fixture fixture, Vec2 point, Vec2 normal,
                float fraction) {
            Body body = fixture.getBody();

            m_points[m_count].set(point);
            m_normals[m_count].set(normal);
            m_user_data[m_count] = body.getUserData();
            ++m_count;

            if (m_count == E_MAX_COUNT) {
                return RAYCAST_TERMINATE;
            }

            return RAYCAST_CONTINUE;
        };

    }
};

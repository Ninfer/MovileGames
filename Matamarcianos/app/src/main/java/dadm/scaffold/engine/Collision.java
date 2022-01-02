package dadm.scaffold.engine;

import java.util.ArrayList;
import java.util.List;

public class Collision {

    private static List<Collision> collisionPool = new ArrayList<Collision>();

    public ScreenGameObject objectA;
    public ScreenGameObject objectB;

    public static Collision init(ScreenGameObject objectA, ScreenGameObject objectB) {
        if (collisionPool.isEmpty()) {
            return new Collision(objectA, objectB);
        }
        return collisionPool.remove(0);
    }

    public static void release(Collision c) {
        collisionPool.add(c);
    }

    public Collision(ScreenGameObject objectA, ScreenGameObject objectB) {
        this.objectA = objectA;
        this.objectB = objectB;
    }

    public boolean equals (Collision c) {
        return (objectA == c.objectA && objectB == c.objectB)
                || (objectA == c.objectB && objectB == c.objectA);
    }
}

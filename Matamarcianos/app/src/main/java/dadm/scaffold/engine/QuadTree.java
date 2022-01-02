package dadm.scaffold.engine;

import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

public class QuadTree {

    private static final int MAX_QUADTREES = 12;
    private static int MAX_OBJECTS_TO_CHECK = 2;

    private List<ScreenGameObject> gameObjects = new ArrayList<ScreenGameObject>();
    private Rect area = new Rect();

    private Rect tmpRect = new Rect();

    private QuadTree[] children = new QuadTree[4];

    private static List<QuadTree> quadTreePool = new ArrayList<QuadTree>();

    public static void init() {
        quadTreePool.clear();
        for (int i = 0; i < MAX_QUADTREES; i++) {
            quadTreePool.add(new QuadTree());
        }
    }

    public void setArea(Rect area) {
        this.area.set(area);
    }

    public void checkObjects(List<ScreenGameObject> gameObjects) {
        this.gameObjects.clear();
        int numObjects = gameObjects.size();
        for (int i = 0; i < numObjects; i++) {
            ScreenGameObject current = gameObjects.get(i);
            Rect boundingRect = current.mBoundingRect;
            if (Rect.intersects(boundingRect, area)) {
                this.gameObjects.add(current);
            }
        }
    }

    public void checkCollisions(GameEngine gameEngine, List<Collision> detectedCollisions) {
        int numObjects = gameObjects.size();
        if (numObjects > MAX_OBJECTS_TO_CHECK && quadTreePool.size() >= 4) {
            // Split this area in 4
            splitAndCheck(gameEngine, detectedCollisions);
        }
        else {
            for (int i = 0; i < numObjects; i++) {
                ScreenGameObject objectA = gameObjects.get(i);
                for (int j = i + 1; j < numObjects; j++) {
                    ScreenGameObject objectB = gameObjects.get(j);
                    if (objectA.checkCollision(objectB)) {
                        Collision c = Collision.init(objectA, objectB);
                        if (!hasBeenDetected(detectedCollisions, c)) {
                            detectedCollisions.add(c);
                            objectA.onCollision(gameEngine, objectB);
                            objectB.onCollision(gameEngine, objectA);
                        }
                    }
                }
            }
        }
    }

    private boolean hasBeenDetected(List<Collision> detectedCollisions, Collision c) {
        int numCollisions = detectedCollisions.size();
        for (int i=0; i<numCollisions; i++) {
            if (detectedCollisions.get(i).equals(c)) {
                return true;
            }
        }
        return false;
    }

    private void splitAndCheck(GameEngine gameEngine, List<Collision> detectedCollisions) {
        for (int i=0 ; i<4; i++) {
            children[i] = quadTreePool.remove(0);
        }
        for (int i=0 ; i<4; i++) {
            children[i].setArea(getArea(i));
            children[i].checkObjects(gameObjects);
            children[i].checkCollisions(gameEngine, detectedCollisions);
            // Clear and return to the pool
            children[i].gameObjects.clear();
            quadTreePool.add(children[i]);
        }
    }

    private Rect getArea(int quadrant) {
        int startX = area.left;
        int startY = area.top;
        int width = area.width();
        int height = area.height();
        switch (quadrant) {
            case 0:
                tmpRect.set(startX, startY, startX + width / 2, startY + height / 2);
                break;
            case 1:
                tmpRect.set(startX + width / 2, startY, startX + width, startY + height / 2);
                break;
            case 2:
                tmpRect.set(startX, startY + height / 2, startX + width / 2, startY + height);
                break;
            case 3:
                tmpRect.set(startX + width / 2, startY + height / 2, startX + width, startY + height);
                break;
        }
        return tmpRect;
    }

    public void addGameObject(ScreenGameObject sgo) {
        gameObjects.add(sgo);
    }

    public void removeGameObject(ScreenGameObject objectToRemove) {
        gameObjects.remove(objectToRemove);
    }
}

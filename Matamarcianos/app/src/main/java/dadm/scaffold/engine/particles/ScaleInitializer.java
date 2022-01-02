package dadm.scaffold.engine.particles;

import java.util.Random;

public class ScaleInitializer  implements ParticleInitializer {

    private double scale;

    public ScaleInitializer(double s) {
        scale = s;
    }

    @Override
    public void initParticle(Particle p, Random r) {
        double rd=(double)r.nextInt(1000)/1000.0;
        double s=scale-rd*scale*0.5;
        p.mScale = s;
    }
}


package blob;

public class Blob {
    public float x,y,z;
    public float vX = 0,vY = 0,vZ = 0;
    public float strength;

    public Blob(float px,float py, float pz,float pstrength) {
        x = px;
        y = py;
        z = pz;
        strength = pstrength;
    }

    public float getStrengthAtPoint(float px,float py,float pz) {
        float distX = (x - px);
        float distY = (y - py);
        float distZ = (z - pz);
        return strength / (distX*distX+distY*distY+distZ*distZ);
    }

    public void update(int width,int height,int depth) {
        if(x <= 0 || x >= width)
            vX *= -1;
        if(y <= 0 || y >= height)
            vY *= -1;
        if(z <= 0 || z >= depth)
            vZ *= -1;

        x += vX;
        y += vY;
        z += vZ;
    }
}
